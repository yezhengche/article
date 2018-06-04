package cn.kaixuan.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadUtil {
    private static final int BUFFER_SIZE = 4096;

    /**
     * @param downloadUrl 下载地址
     * @param saveDir 保存目录
     * @param fileName 保存文件名
     * @return 下载成功返回true,失败返回false

     */
    public static void download(String downloadUrl, String saveDir, String fileName) {
        if (downloadUrl == null || saveDir == null || fileName == null) {
            return;
        }
        InputStream in = null;
        FileOutputStream out = null;
        try {
            URL url = new URL(downloadUrl);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
                String saveFilePath = saveDir + File.separator + fileName;
                out = new FileOutputStream(saveFilePath);
                int byteRead = -1;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((byteRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, byteRead);
                }
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
