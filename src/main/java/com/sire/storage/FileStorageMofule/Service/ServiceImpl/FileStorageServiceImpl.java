package com.sire.storage.FileStorageMofule.Service.ServiceImpl;

import com.alibaba.fastjson.JSON;
import com.sire.storage.FileStorageMofule.FileUtils;
import com.sire.storage.FileStorageMofule.Service.FileStorageService;
import com.sire.storage.ModuleEnvironment.Http.JsonResponse;
import com.sire.storage.ModuleEnvironment.Utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.sire.storage.ModuleEnvironment.Http.HttpBusinessCode.OK;
import static com.sire.storage.ModuleEnvironment.Http.HttpBusinessCode.RESOURCE_NOT_EXIST;

/**
 * ==================================================
 * All Right Reserved
 * Date:2017/12/27
 * Author:Sire
 * Description:
 * ==================================================
 */
@Service("FileStorageService")
public class FileStorageServiceImpl implements FileStorageService {

    public static final String DIR_NOT_EXIST = "目录不存在或已经被删除";
    public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=utf-8";
    public static final String FILE_NOT_EXIST = "文件不存在或已删除";

    @Value(value = "${storage.root.dir}")
    private String storageRootDir;



    @Override
    public JsonResponse uploadFile(MultipartFile file, String fileSize, String fileDir, HttpServletRequest req) throws IOException {
        String localFileName = saveFile(file, fileDir);
        String fileRequestUrl = getFileUrl(fileDir, localFileName,req);
        return JsonResponse.createMessageResponse(OK, fileRequestUrl);
    }

    private File getStorageRootDir() {
        File rootDir = new File(storageRootDir);
        if (!rootDir.exists()) {
            rootDir.mkdirs();
        }
        return rootDir;
    }

    @Override
    public JsonResponse uploadImage(MultipartFile image, String width, String height, String fileDir, HttpServletRequest req) throws IOException {
        String localFileName = saveFile(image, fileDir);
        String fileRequestUrl = getFileUrl(fileDir, localFileName,req) + "&width=" +(width == null?"":width) + "&height=" + (height==null?"":height);
        return JsonResponse.createMessageResponse(OK, fileRequestUrl);
    }

    @Override
    public void downloadFile(String fileDir, String fileName, HttpServletResponse res) throws IOException {
        File storageRootDir = getStorageRootDir();
        File subStorageRootDir = new File(storageRootDir, fileDir);

        if (!subStorageRootDir.exists()) {
            resourceNotExist(fileDir, DIR_NOT_EXIST, res);
            return;
        }
        File destinationFile = new File(subStorageRootDir, fileName);
        if (!destinationFile.exists()) {
            resourceNotExist(fileDir, FILE_NOT_EXIST, res);
            return;
        }
        FileUtils.writeFile2Response(destinationFile, res);
    }

    private void resourceNotExist(String fileStr, String errorMessage, HttpServletResponse res) throws IOException {
        JsonResponse messageResponse = JsonResponse.createMessageResponse(RESOURCE_NOT_EXIST, fileStr + errorMessage);
        res.setContentType(APPLICATION_JSON_CHARSET_UTF_8);
        PrintWriter writer = res.getWriter();
        writer.write(JSON.toJSONString(messageResponse));
        writer.flush();
        writer.close();
    }

    private String getFileUrl(String fileDir, String localFileName,HttpServletRequest request)  {
        return request.getScheme() + "://"+ request.getServerName()+":"+request.getServerPort() + "/storage/downloadfile/" + fileDir + "?filename=" + localFileName;
    }




    private String saveFile(MultipartFile file, String fileDir) throws IOException {
        File storageRootDir = getStorageRootDir();
        File subStorageRootDir = new File(storageRootDir, fileDir);
        if (!subStorageRootDir.exists()) {
            subStorageRootDir.mkdirs();
        }
        String imageName = file.getOriginalFilename();
        String suffix = imageName.substring(imageName.lastIndexOf(".") + 1);
        String singleId = UUIDUtils.generateSingleId();
        //新的图片名
        String localSavedFileName = singleId + "." + suffix;
        FileCopyUtils.copy(file.getBytes(), new File(subStorageRootDir.toString(), localSavedFileName));
        return localSavedFileName;
    }
}
