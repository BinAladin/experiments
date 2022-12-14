package cz.boucnikd.masterjavamultithreadingprogramming;

import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.IntStream;

public class ConcurrentSkipListMapExample {

    public static void main(String[] args) {
        var numbersCount = 5000;
        var random = new Random(1);
        var input = IntStream.generate(() -> random.nextInt(20)).limit(numbersCount).boxed().toList();



        var map = new ConcurrentSkipListMap<Integer, Integer>();

    }
}
