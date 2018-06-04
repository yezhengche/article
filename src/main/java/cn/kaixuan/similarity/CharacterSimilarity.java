package cn.kaixuan.similarity;

import cn.kaixuan.data.Character;

/**
 * 计算人物特征相似度
 */
public class CharacterSimilarity {

    /**
     * 计算人物相似度
     * @param characterOne 第一个目标人物
     * @param characterTwo 第二个目标人物
     * @return 人物特征之间的cos余弦值
     */
    public static double similary(Character characterOne, Character characterTwo) {
        if (characterOne == null || characterTwo == null) {
            return 0;   //表示独立, 不相同
        }
        // 出生日期不同，直接认定不同
        if (!characterOne.getBirth().equals(characterTwo.getBirth())) {
            return 0;
        }
        // email地址相同，直接认定相同
        if (characterOne.getEmail().equals(characterTwo.getEmail())) {
            return 1;
        }
        // 电话相同，直接认定相同
        if (!characterOne.getTel().equals(characterTwo.getTel())) {
            return 1;
        }

        // 计算位置, 职业信息，以及组织机构的词距
        double locationSimilarity = NormalizedGoogleDistance.NGD(
                characterOne.getLocation(), characterTwo.getLocation()
        );

        double occuSimilarity = NormalizedGoogleDistance.NGD(
                characterOne.getOccu(), characterTwo.getOccu()
        );
        double orgSimilarity = NormalizedGoogleDistance.NGD(
                characterOne.getOccu(), characterTwo.getOccu()
        );

        // 人物特征向量 [birth, location, occu, org, email, tel]
        double[] characterVectorA = {
                1, locationSimilarity, occuSimilarity, orgSimilarity, 0, 0
        };
        double[] characterVectorB = {
                1, locationSimilarity, occuSimilarity, orgSimilarity, 0, 0
        };

        // 计算夹角余弦值
        return getCosDistance(characterVectorA, characterVectorB);

    }

    /**
     * 计算向量之间的cos值
     * @param vectorA 向量a
     * @param vectorB 向量b
     * @return  夹角余弦值
     */
    private static double getCosDistance(double[] vectorA, double[] vectorB) {
        if (vectorA == null || vectorB == null) {
            return 0.0;
        }
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }

        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
        
    }
}
