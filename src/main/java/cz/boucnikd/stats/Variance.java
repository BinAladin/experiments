package cz.boucnikd.stats;

import java.util.Collection;
import java.util.stream.DoubleStream;

public class Variance {
    public static double varianceA(Collection<Number> input) {
        var avg = input.stream().mapToDouble(Number::doubleValue).average().getAsDouble();

        return
                avg(input.stream()
                        .mapToDouble(Number::doubleValue)
                        .map(d -> Math.pow(avg - d, 2)), input.size());
    }

    public static double varianceB(Collection<Number> input) {
        // avg(power(x-avg))

        var avg = avg(input);

        var powerAvg = avg(input.stream().mapToDouble(Number::doubleValue)
                .map(d -> Math.pow(d, 2)), input.size());

        return Math.pow(avg, 2) - powerAvg;
    }

    private static double avg(Collection<Number> input) {
        return avg(input.stream().mapToDouble(Number::doubleValue), input.size());
    }

    private static double avg(DoubleStream input, int count) {
        return input.sum() / (count - 1);
    }
}
