package com.pant.loki.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileCopyUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件处理工具
 * Created by yugj on 17/7/20.
 */
public class FileUtil {

    public static void recurDelete(File f) {
        try {
            for (File fi : f.listFiles()) {
                if (fi.isDirectory()) {
                    recurDelete(fi);
                } else {
                    fi.delete();
                }
            }
            f.delete();
        } catch (NullPointerException n) {
            n.printStackTrace();
        }
    }

    public static void delete(String fileName) {

        File file = new File(fileName);

        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 文件下载
     *
     * @param remoteUrl 远程地址
     * @param localPath 本地路径
     */
    public static void download(String remoteUrl, String localPath) throws IOException {

        download(remoteUrl, localPath, false);

    }

    /**
     * 文件下载
     *
     * @param remoteUrl    远程地址
     * @param localPath    本地路径
     * @param turnIntranet 是否转内网
     */
    public static void download(String remoteUrl, String localPath, boolean turnIntranet) throws IOException {

        String targetUrl = remoteUrl;

        if (StringUtils.isNoneBlank(CommonConfig.resVisitHost)) {
            String contentPath = getFileContentPath(remoteUrl);
            targetUrl = CommonConfig.resVisitHost + contentPath;
        }


        URL url = new URL(targetUrl);

        URLConnection connection = url.openConnection();
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        connection.setConnectTimeout(30000);
        connection.setReadTimeout(30000);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Boolean flag = true;
        int num = 0;

        while (flag) {
            try {
                if (num > 3) {
                    flag = false;
                }

                connection.connect();
                flag = false;
            } catch (IOException var28) {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ++num;
            }
        }

        BufferedInputStream fStream = new BufferedInputStream(connection.getInputStream());
        int b;
        File file = new File(localPath);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        while ((b = fStream.read(buffer)) != -1) {
            fos.write(buffer, 0, b);
        }
        fos.flush();
        fos.close();
        fStream.close();
    }

    //去除主机本分
    private static String getFileContentPath(String url) {

        // 效验数据可以为空
        if (StringUtils.isBlank(url)) {
            return "";
        }
        // 获取效验的长度
        int maxLength = 4098;

        // 对长度进行效验
        url = url.trim();
        if (url.length() > maxLength) {
            return "";
        }

        // 效验http,https,ftp开头
        String headReg = "^(http|ftp|https)://";
        Pattern p1 = Pattern.compile(headReg);
        Matcher m1 = p1.matcher(url);
        String head = null;
        if (!m1.find()) {
            return url;
        }
        head = m1.group();

        url = url.replace(head, "");
        url = url.substring(url.indexOf("/"));
        return url;
    }

    /**
     * @description 文件移动，兼容不同存储之间的移动。
     * @params [srcFile, destFile]
     * @returnType boolean
     * @author pantao
     * @date 2018/7/18 12:02
     */
    public static boolean moveFile(File srcFile, File destFile) {
        if (srcFile == null || destFile == null || !srcFile.isFile()) {
            return false;
        }

        try {
            int copy = FileCopyUtils.copy(srcFile, destFile);
            if (copy < 0) {
                return false;
            }
            if (srcFile.delete()) {
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
