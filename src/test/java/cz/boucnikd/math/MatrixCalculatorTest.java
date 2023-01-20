package cz.boucnikd.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.*;

class MatrixCalculatorTest {

    private final List<List<Integer>> matrix = of(of(1,0), of(-1,-3), of(2,1));

    private final List<Integer> vector = of(2, 1, 0);
    private final List<Integer> expectedResult = of(1, -3);
    private MatrixCalculator matrixCalculator;


    @BeforeEach
    void beforeEach() {
        matrixCalculator = new MatrixCalculator();
    }

    @Test
    void multiplyMatrixByVector() {
        var result = matrixCalculator.multiplyMatrixByVector(matrix, vector, new ArrayList<>(), 0, matrix.size(), (a, b) -> a * b);

        assertEquals(expectedResult, result);
    }

    @Test
    void multiplyMatrixByVectorParallel() {
        var result = matrixCalculator.multiplyMatrixByVectorParallel(matrix, vector, new ArrayList<>(), 0, matrix.size(), (a, b) -> a * b);

        assertEquals(expectedResult, result);
    }
}
