package com.sire.storage.FileStorageMofule;

import org.springframework.util.FileCopyUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * ==================================================
 * All Right Reserved
 * Date:2017/12/27
 * Author:Sire
 * Description:
 * ==================================================
 */
public class FileUtils {
    /**
     * 下载文件
     * @param sourceFile  要下载的文件
     * @param res  响应流
     * @throws IOException
     */
    public static void writeFile2Response(File  sourceFile, HttpServletResponse res) throws IOException {
        res.setContentType("application/octet-stream");
        res.addHeader("Content-Disposition", "attachment;filename=" + sourceFile.getName());
        ServletOutputStream outputStream = res.getOutputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(sourceFile));
        FileCopyUtils.copy(bufferedInputStream, outputStream);
        outputStream.flush();
        outputStream.close();
        bufferedInputStream.close();
    }
}
