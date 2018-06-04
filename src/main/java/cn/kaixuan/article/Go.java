package cn.kaixuan.article;

import cn.kaixuan.clustering.AverageLinkageStrategy;
import cn.kaixuan.clustering.Cluster;
import cn.kaixuan.clustering.ClusteringAlgorithm;
import cn.kaixuan.clustering.DefaultClusteringAlgorithm;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

public class Go {
    public static void main(String[] args) {
        clusteringTest();
    }

    private static void caseTest() {
        String[] sts = new String[] {
                "签约仪式前，秦光荣、李纪恒、仇和等一同会见了参加签约的企业家。",
                "王国强、高峰、汪洋、张朝阳光着头、韩寒、小四",
                "张浩和胡健康复员回家了",
                "王总和小丽结婚了",
                "编剧邵钧林和稽道青说",
                "这里有关天培的有关事迹",
                "龚学平等领导,邓颖超生前",
        };
        Segment segment = HanLP.newSegment().enableNameRecognize(true);
        for (String s : sts) {
            List<Term> terms = segment.seg(s);
            System.out.println(terms);
        }
    }

    private static void clusteringTest() {
        String[] names = new String[] { "O1", "O2", "O3", "O4", "O5", "O6" };
        double[][] distances = new double[][] {
                { 0, 1, 9, 7, 11, 14 },
                { 1, 0, 4, 3, 8, 10 },
                { 9, 4, 0, 9, 2, 8 },
                { 7, 3, 9, 0, 6, 13 },
                { 11, 8, 2, 6, 0, 10 },
                { 14, 10, 8, 13, 10, 0 }};
        ClusteringAlgorithm alg = new DefaultClusteringAlgorithm();
        Cluster cluster = alg.performClustering(distances, names,
                new AverageLinkageStrategy());
        cluster.toConsole(5);
    }
}
