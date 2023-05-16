package cz.boucnikd.multithreadingconcurrencyperformance.hackers;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var vault = new Vault(1000);

        var threads = List.of(new AscendingHacker(vault), new DescendingHacker(vault), new RandomHacker(vault), new PoliceThread());

        threads.forEach(Thread::start);

    }
}
