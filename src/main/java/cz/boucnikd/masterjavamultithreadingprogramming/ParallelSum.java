package cz.boucnikd.masterjavamultithreadingprogramming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelSum {
    public static void main(String[] args) throws Exception {

        var inputNumbers = IntStream.rangeClosed(0, 5000).boxed().toList();

        var expectedSum = inputNumbers.stream().mapToInt(n -> n).sum();
        var workerOneR = new Worker(inputNumbers.subList(0, inputNumbers.size() / 2));
        var workerTwoR = new Worker(inputNumbers.subList(inputNumbers.size() / 2, inputNumbers.size()));

        var workerOne = new Thread(workerOneR);
        var workerTwo = new Thread(workerTwoR);

        Stream.of(workerOne, workerTwo).forEach(Thread::start);

        workerOne.join();
        workerTwo.join();

        System.out.println("Sum:" + (workerOneR.getSum() + workerTwoR.getSum()) + " expected:" + expectedSum);
    }

    @Getter
    @RequiredArgsConstructor
    static class Worker implements Runnable {

        private int sum;
        private final List<Integer> input;

        @Override
        public void run() {
            sum = input.stream().mapToInt(m -> m).sum();
        }
    }
}

