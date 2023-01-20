package cz.boucnikd.microbenchmarkharness;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class ExampleBenchmark {
    private static final int count = 10 * 1000;

    @Benchmark
    public void lambdas() {
        Supplier<IntSupplier> intSupplierOne = getSupplier(getSupplierAsLambda());
        Supplier<IntSupplier> intSupplierTwo = getSupplier(getSupplierAsLambda());
        Supplier<IntSupplier> intSupplierThree = getSupplier(getSupplierAsLambda());

        calculate(intSupplierOne, intSupplierTwo, intSupplierThree);
    }

    @Benchmark
    public void anonymousClasses() {
        Supplier<IntSupplier> intSupplierOne = getSupplier(getSupplierAsAnonymousClass());
        Supplier<IntSupplier> intSupplierTwo = getSupplier(getSupplierAsAnonymousClass());
        Supplier<IntSupplier> intSupplierThree = getSupplier(getSupplierAsAnonymousClass());

        calculate(intSupplierOne, intSupplierTwo, intSupplierThree);
    }

    private IntSupplier getSupplierAsLambda() {
        return () -> ExampleBenchmark.this.getNextInt();
    }

    private IntSupplier getSupplierAsAnonymousClass() {
        return new IntSupplier() {
            @Override
            public int getAsInt() {
                return ExampleBenchmark.this.getNextInt();
            }
        };
    }

    private static <T> Supplier<T> getSupplier(T supplier) {
        return new Supplier<T>() {
            @Override
            public T get() {
                return supplier;
            }
        };
    }

    private int getNextInt() {
        return (int) (Math.random() * 1000);
    }

    private void calculate(Supplier<IntSupplier> supplierA, Supplier<IntSupplier> supplierB, Supplier<IntSupplier> supplierC) {
        List<Integer> results = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            results.add(supplierA.get().getAsInt() + supplierB.get().getAsInt() + supplierC.get().getAsInt());
        }
    }
}
