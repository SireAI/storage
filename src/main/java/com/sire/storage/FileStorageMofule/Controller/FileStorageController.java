package com.sire.storage.FileStorageMofule.Controller;

import com.sire.storage.FileStorageMofule.Service.FileStorageService;
import com.sire.storage.ModuleEnvironment.Http.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ==================================================
 * All Right Reserved
 * Date:2017/12/26
 * Author:Sire
 * Description:
 * ==================================================
 */
@Controller
public class FileStorageController {
    @Autowired
    private FileStorageService fileStorageService;

    /**
     * 文件上传
     * @param file  文件key
     * @param fileSize 文件大小
     * @param fileDir  文件存储目录,相当于分文件夹存储文件
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadfile/{dir}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse uploadFile(@RequestParam(value = "file") MultipartFile file, @RequestParam(required = false) String fileSize, @PathVariable("dir") String fileDir, HttpServletRequest req) throws  IOException {

        return fileStorageService.uploadFile(file, fileSize,fileDir,req);
    }

    /**
     * 图片上传
     * @param image  图片
     * @param width  图片宽
     * @param height 图片高
     * @param fileDir 图片存储目录
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadimage/{dir}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse uploadImage(@RequestParam(value = "image") MultipartFile image,  @RequestParam(required = false)String width,@RequestParam(required = false)String height,@PathVariable("dir") String fileDir, HttpServletRequest req) throws  IOException {

        return fileStorageService.uploadImage(image, width,height,fileDir,req);
    }


    @RequestMapping(value = "/downloadfile/{dir}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("dir") String fileDir,@RequestParam("filename") String fileName,HttpServletResponse res) throws  IOException {
         fileStorageService.downloadFile(fileDir,fileName,res);
    }

}
