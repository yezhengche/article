package cn.kaixuan.data;

import static cn.kaixuan.data.DownloadUtil.download;

public class DownloadTask implements Runnable{
    /**
     * 下载百度检索的网页 task
     */
    private String downloadUrl;
    private String saveDir;
    private String saveName;

    public DownloadTask(String downloadUrl, String saveDir, String saveName) {
        this.downloadUrl = downloadUrl;
        this.saveDir = saveDir;
        this.saveName = saveName;
    }

    @Override
    public void run() {
        download(downloadUrl, saveDir, saveName);
    }

}
