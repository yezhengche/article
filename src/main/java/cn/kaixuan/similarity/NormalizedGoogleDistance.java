package cn.kaixuan.similarity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 计算归一化谷歌距离
 */
public class NormalizedGoogleDistance {
    private static String REGEX = "百度为您找到相关结果约(.*)个";
    private static Pattern PATTERN = Pattern.compile(REGEX);
    private static String SEARCH_URL = "http://www.baidu.com/s?wd=";


    private static Double searchResult(String query){
        try {
            URL baiduURL = new URL(SEARCH_URL +query);
            URLConnection connection = baiduURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String html = in.readLine();
            while(html!=null){
                Matcher matcher = PATTERN.matcher(html);
                while(matcher.find()){
                    System.out.println(query.replaceAll("%20", " ")+": "+matcher.group(1));
                    String temp = matcher.group(1);
                    return Double.parseDouble(temp.replaceAll(",", ""));
                }
                html = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public static Double NGD(String wordOne,String wordTwo){
        wordOne=wordOne.replaceAll(" ", "");
        wordTwo=wordTwo.replaceAll(" ", "");

        Double numA,numB,numC;
        numA=1.0;numB=1.0;numC=1.0;
        try {
            numA = searchResult(wordOne);
            numB = searchResult(wordTwo);
            numC = searchResult(wordOne+"%20"+wordTwo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回Calculate函数的计算结果
        return Calculate(numA, numB, numC);
    }


    private static Double Calculate(Double numA, Double numB, Double numC){
        Double lnx = Math.log(numA);
        Double lny = Math.log(numB);
        Double lnSum = Math.log(25270000000.0);
        Double lnxy = Math.log(numC);
        if (lnx>lny) {
            return (lnx-lnxy)/(lnSum-lny);
        }else {
            return (lny-lnxy)/(lnSum-lnx);
        }
    }

}

