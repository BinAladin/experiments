package cz.boucnikd.stats;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VarianceTest {

    private final Collection<Number> inputs = Collections.unmodifiableCollection(List.of(10, 5, 12, 42));

    @Test
    void varianceA() {
        var result = Variance.varianceA(inputs);

        assertEquals(280.91, result, 0.01);
    }

    @Test
    void varianceB() {
        var result = Variance.varianceB(inputs);

        assertEquals(280.91, result, 0.01);
    }
}
