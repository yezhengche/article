package cn.kaixuan.data;

import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;

public class ParseInfo {
    /**
     * 从网页获取人物信息
     */

    public static void main(String[] args) {
        String html = "C:\\Users\\kaixuan\\IdeaProjects\\article\\src\\main\\resources\\data\\刘洋\\0A5B699EBD719D2C372AE222588E3E20.html";
        System.out.println(htmlToText(html));
    }

    private static String htmlToText(String html) {
        if (html == null) {
            return null;
        }
        File file = new File(html);

        try {
            return Jsoup.parse(file, "UTF-8").text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
