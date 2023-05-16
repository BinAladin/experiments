package cz.boucnikd.math;

import one.microstream.collections.interfaces._doubleCollecting;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OuterProductTest {

    @Test
    void execute() {
        var vectorA = new double[]{7,2,3,1};
        var vectorB = new double[]{3,2,1};

        var expecteResult = new double[][]{
                {21,14,7},
                {6,4,2},
                {9, 6, 3},
                {3,2,1}
        };

        assertArrayEquals(expecteResult, OuterProduct.execute(vectorA, vectorB));
    }
}
