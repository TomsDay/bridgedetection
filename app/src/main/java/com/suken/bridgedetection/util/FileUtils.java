package com.suken.bridgedetection.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/6/22.
 */
public class FileUtils {
    public static boolean moveFileTo(File srcFile, File destFile) throws IOException {

        boolean iscopy = copyFileTo(srcFile, destFile);

        if (!iscopy)
            return false;
        delFile(srcFile);
        return true;

    }
    public static boolean copyFileTo(File srcFile, File destFile) throws IOException {
        if (srcFile.isDirectory() || destFile.isDirectory())
            return false;//判断是否是文件
        FileInputStream fis = new FileInputStream(srcFile);

        FileOutputStream fos = new FileOutputStream(destFile);

        int readLen = 0;

        byte[] buf = new byte[1024];

        while ((readLen = fis.read(buf)) != -1) {
            fos.write(buf, 0, readLen);

        }
        fos.flush();

        fos.close();

        fis.close();

        return true;

    }

    public static boolean delFile(File file) {
        if(file.isDirectory())
            return false;

        return file.delete();
    }
}
