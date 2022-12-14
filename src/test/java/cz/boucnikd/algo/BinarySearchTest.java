package cz.boucnikd.algo;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    private BinarySearch binarySearch = new BinarySearch();

    @Test
    void search() {
        var input = IntStream.range(0,1000).toArray();

        for (int i = 0; i < input.length; i++) {
            assertEquals(i, binarySearch.search(input, input[i]));
        }
    }
}
