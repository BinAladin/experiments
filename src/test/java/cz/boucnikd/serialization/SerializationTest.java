package cz.boucnikd.serialization;

import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SerializationTest {

    private final String data = IntStream.range(0, 10000)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining(""));

    @Test
    void serialization() throws Exception {
        var baos = new ByteArrayOutputStream();
        try (var zip = new GZIPOutputStream(baos)) {
            try (var bos = new BufferedOutputStream(zip)) {
                try (var oos = new ObjectOutputStream(bos)) {
                    oos.writeObject(data);
                    oos.close();
                    zip.close();
                    var zipped = baos.toByteArray();

                    System.out.println("Original size:" + data.length());
                    System.out.println("Zipped size:" + zipped.length);
                    assertNotEquals(data.length(), zipped.length);

                    try (var bais = new ByteArrayInputStream(zipped)) {
                        try (var zipi = new GZIPInputStream(bais)) {
                            try (var bis = new BufferedInputStream(zipi)) {
                                try (var ois = new ObjectInputStream(bis)) {
                                    var deserialized = (String) ois.readObject();
                                    assertEquals(data, deserialized);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    void someTest(){

    }
}
