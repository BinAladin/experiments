package cz.boucnikd.sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MergesortTest {

    private Mergesort mergesort;

    @BeforeEach
    void setUp() {
        mergesort = new Mergesort();
    }

    @Test
    void sort() {
        var input = new int[]{6, 10, 1, 5};

        mergesort.sort(input);

        assertArrayEquals(new int[]{1, 5, 6, 10}, input);
    }

    @Test
    void sortTwo() {
        var input = IntStream.range(0, 1000).mapToObj(o -> o).collect(Collectors.toList());

        Collections.shuffle(input);

        var inputArray = input.stream().mapToInt(n -> n).toArray();
        var expected = Arrays.copyOf(inputArray, inputArray.length);

        Arrays.sort(expected);

        mergesort.sort(inputArray);

        assertArrayEquals(expected, inputArray);
    }
}
