package cz.boucnikd.math;

import one.microstream.collections.interfaces._doubleCollecting;

public class OuterProduct {
    public static double[][] execute(double[] vectorA, double[] vectorB){
        var result = new double[vectorA.length][vectorB.length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = vectorA[i] * vectorB[j];
            }
        }

        return result;
    }
}
