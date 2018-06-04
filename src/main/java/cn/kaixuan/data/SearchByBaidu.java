package cn.kaixuan.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchByBaidu {
    static String SEARCH_URL = "https://www.baidu.com/s?ie=UTF-8&&wd=";   // baidu 搜索接口
    static String[] names = {"张伟", "李伟",
                            "李娜", "王芳",
                            "王秀英", "李敏",
                            "李强", "李军",
                            "刘洋", "张勇"};    // 选取10个人名作为检测结果
    static int THREAD_HOLD = 10;
    static String DIR = "C:\\Users\\kaixuan\\IdeaProjects\\article\\src\\main\\resources\\data";  //保存数据

    public static void main(String[] args) {
        searchByBaidu();
    }


    public static void searchByBaidu() {
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_HOLD);
        for (String name : names) {
            List<String> urls = new ArrayList<>();
            for (int i = 0; i < 200; i += 10){
                List<String> res = search(name, i);
                if (res != null){
                    urls.addAll(res);
                }
            }
            File dir = new File(DIR, name);
            if (!dir.exists()) {
                dir.mkdir();
            }
            for (String url: urls){
                Runnable task = new DownloadTask(
                        url, dir.getAbsolutePath(), getMD5(url + name) + ".html"
                );
                pool.submit(task);
            }
        }
        pool.shutdown();
    }

    /**
     * 根据关键词搜索单页
     * @param query
     * @return
     * @throws IOException
     */
    private static List<String> search(String query, int pn){
        if (query == null) {
            return null;
        }
        String url;
        if (pn < 10){
            url = SEARCH_URL + query;
        } else {
            url = SEARCH_URL + query + "&pn=" + String.valueOf(pn);
        }
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements pages = doc.select("h3.t"); // 内容部分
            List<String> pageUrls = new ArrayList<>();
            for (Element ele : pages) {
                String pageUrl = ele.child(0).attr("href");
                System.out.println(pageUrl);
                pageUrls.add(pageUrl);
            }
            return pageUrls;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String getMD5(String message) {
        if (message == null) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytesMessage = message.getBytes("utf-8");
            byte[] digest = md.digest(bytesMessage);
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
