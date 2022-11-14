package cz.boucnikd.microstream;

import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PerformanceTest {

    private final Random random = new Random(0);

    private Storage storage;


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

    private void test() throws Exception{
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
        long start = LocalDateTime.now().getNano();

        storage.save(toStore);

        System.out.println(LocalDateTime.now().getNano() - start);
    }

    private DTO newDto(int integer) {
        return new DTO(IntStream.range(0, 30)
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
}
