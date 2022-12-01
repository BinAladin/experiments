package cz.boucnikd.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class QuicksortTest {

    @Test
    void sort() {
        var input = IntStream.range(0, 1000).mapToObj(o -> o).collect(Collectors.toList());

        Collections.shuffle(input);

        var inputArrayOne = input.toArray(new Integer[0]);
        var inputArrayTwo = input.toArray(new Integer[0]);
        var inputArrayThree = input.toArray(new Integer[0]);

        Arrays.sort(inputArrayOne);
        Quicksort.sort(inputArrayTwo, Comparable::compareTo);
        Quicksort.sort(inputArrayThree);

        assertArrayEquals(inputArrayOne, inputArrayTwo);
        assertArrayEquals(inputArrayOne, inputArrayThree);
    }
}
