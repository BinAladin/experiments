package cz.boucnikd.math;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

public class MatrixCalculator {
    public <T> List<T> multiplyMatrixByVector(List<List<T>> matrix, List<T> vector, List<T> output, int i, int j, BiFunction<T, T, T> multiplier) {

        if (i == j) {
            for (int k = 0; k < matrix.get(i).size(); k++) {
                output.add(k, multiplier.apply(matrix.get(i).get(k), vector.get(k)));
            }
        } else {
            var mid = (i + j) / 2;
            multiplyMatrixByVector(matrix, vector, output, i, mid, multiplier);
            multiplyMatrixByVector(matrix, vector, output, mid + 1, j, multiplier);
        }
        return output;
    }

    public <T> List<T> multiplyMatrixByVectorParallel(List<List<T>> matrix, List<T> vector, List<T> output, int i, int j, BiFunction<T, T, T> multiplier) {
        return Collections.emptyList();
    }
}
