package cz.boucnikd.microbenchmarkharness;

import org.junit.jupiter.api.Test;

public class BenchmarkRunner {

    @Test
    public void main() throws Exception {
        String[] args = new String[0];
        org.openjdk.jmh.Main.main(args);
    }
}
