package cz.boucnikd.microstream;

import cz.boucnikd.microstream.indexed.MicrostreamStorageWithIndexes;
import cz.boucnikd.microstream.simple.MicrostreamStorage;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PerformanceTest {

    private final Random random = new Random(0);

    private Storage storage;

    private long idCounter = 0;


    @Test
    void testMicrostream() throws Exception {
        storage = new MicrostreamStorage();
        test();
    }

    @Test
    void testFile() throws Exception {
        storage = new BinaryFileStorage();
        test();
    }

    @Test
    void testMicrostreamLookups() throws Exception {
        var msStorage = new MicrostreamStorage();
        msStorage.init();

        var dtos = IntStream.range(0, 1000).mapToObj(this::newDto).collect(Collectors.toList());
        trackNanos(() -> msStorage.save(dtos), "save 1000 dtos");

        trackNanos(() -> msStorage.findById(500L).id(), "find by id");
    }

    @Test
    void testMicrostreamLookupsWithIndexes() throws Exception {
        var msStorage = new MicrostreamStorageWithIndexes();
        msStorage.init();

        var dtos = IntStream.range(0, 1000).mapToObj(this::newDto).collect(Collectors.toList());
        trackNanos(() -> msStorage.save(dtos), "save 1000 dtos");

        trackNanos(() -> msStorage.findById(500L).id(), "find by id");
    }

    private void test() throws Exception {
        storage.init();

        Storage storage1 = storage;
        for (int i = 0; i < 100; i++) {
            DTO dto = newDto(i);
            storage1.save(dto);
        }

        var toStore = newDto(1);

        trackNanos(toStore);

        toStore = newDto(1);

        trackNanos(toStore);

        toStore = newDto(1);

        trackNanos(toStore);

        toStore = newDto(1);

        trackNanos(toStore);

        toStore = newDto(1);

        trackNanos(toStore);
    }

    private void trackNanos(DTO toStore) throws Exception {
        trackNanos(() -> storage.save(toStore), "Saving dto");
    }

    private void trackNanos(Action action, String label) throws Exception {
        long start = LocalDateTime.now().getNano();

        action.execute();

        System.out.println(label + " took ns:" + (LocalDateTime.now().getNano() - start));
    }

    private DTO newDto(int integer) {
        return new DTO(idCounter++, IntStream.range(0, 30)
                .mapToObj(this::createDataItem)
                .collect(Collectors.toList()));
    }

    private Serializable createDataItem(Integer integer) {
        Double value = random.nextDouble();
        if (random.nextBoolean()) {
            if (random.nextBoolean()) {
                return value;
            } else {
                return value.longValue();
            }
        } else {
            return String.valueOf(value);
        }
    }

    interface Action {
        void execute() throws Exception;
    }
}
