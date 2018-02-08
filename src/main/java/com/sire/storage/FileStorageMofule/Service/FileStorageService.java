package com.sire.storage.FileStorageMofule.Service;

import com.sire.storage.ModuleEnvironment.Http.JsonResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ==================================================
 * All Right Reserved
 * Date:2017/12/27
 * Author:Sire
 * Description:
 * ==================================================
 */
public interface FileStorageService {
    JsonResponse uploadFile(MultipartFile file, String fileSize, String fileDir, HttpServletRequest req) throws IOException;

    JsonResponse uploadImage(MultipartFile image, String width, String height, String fileDir, HttpServletRequest req) throws IOException;

    void  downloadFile(String fileDir, String fileName, HttpServletResponse res) throws IOException;
}
