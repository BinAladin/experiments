package cz.boucnikd.streams;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamsTest {
    private final String input = """
            [Enter GRUMIO.]
            GRUMIO.
            Fie, fie on all tired jades, on all mad masters, and all
            foul ways! Was ever man so beaten? Was ever man so ray'd? Was
            ever man so weary? I am sent before to make a fire, and they are
            coming after to warm them. Now, were not I a little pot and soon
            hot, my very lips might freeze to my teeth, my tongue to the roof
            of my mouth, my heart in my belly, ere I should come by a fire to
            thaw me. But I with blowing the fire shall warm myself; for,
            considering the weather, a taller man than I will take cold.
            Holla, ho! Curtis!
            [Enter CURTIS.]
            CURTIS.
            Who is that calls so coldly?
            GRUMIO.
            A piece of ice: if thou doubt it, thou mayst slide from my
            shoulder to my heel with no greater a run but my head and my
            neck. A fire, good Curtis.
            CURTIS.
            Is my master and his wife coming, Grumio?
            GRUMIO.
            O, ay! Curtis, ay; and therefore fire, fire; cast on no
            water.
            CURTIS.
            Is she so hot a shrew as she's reported?
            GRUMIO.
            She was, good Curtis, before this frost; but thou knowest
            winter tames man, woman, and beast; for it hath tamed my old
            master, and my new mistress, and myself, fellow Curtis.
            CURTIS.
            Away, you three-inch fool! I am no beast.
            GRUMIO.
            Am I but three inches? Why, thy horn is a foot; and so long
            am I at the least. But wilt thou make a fire, or shall I complain
            on thee to our mistress, whose hand,--she being now at hand,--
            thou shalt soon feel, to thy cold comfort, for being slow in thy
            hot office?
            CURTIS.
            I prithee, good Grumio, tell me, how goes the world?
            """;

    @Test
    void wordsCount() {
        // convert to char stream
        // get only upper, lower, empty spaces
        // make it string again -- or reduce just by using reducer!
        // count word ocurances
        // print top10!
        var words = new ArrayList<String>();

        var builder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            var currentChar = input.charAt(i);
            if (builder == null) {
                builder = new StringBuilder();
            }
            if (Character.isUpperCase(currentChar) || Character.isLowerCase(currentChar)) {
                builder.append(Character.toLowerCase(currentChar));
            } else if (!builder.isEmpty()) {
                words.add(builder.toString());
                builder = null;
            }
        }

        words.stream()
                .collect(Collectors.groupingBy(s -> s))
                .entrySet().stream()
                .collect(getEntryTreeMapCollector())
                .entrySet().stream()
                .limit(5)
                .forEach(System.out::println);


    }

    private Collector<Entry<String, List<String>>, ?, TreeMap<Integer, String>> getEntryTreeMapCollector() {
        var binaryOperator = new BinaryOperator<String>() {
            @Override
            public String apply(String a, String b) {
                return a + "," + b;
            }
        };

        var collectionSupplier = new Supplier<TreeMap<Integer, String>>() {
            @Override
            public TreeMap<Integer, String> get() {
                return new TreeMap<>(Comparator.<Integer>comparingInt(d -> d).reversed());
            }
        };

        return Collectors.toMap(e -> e.getValue().size(), Entry::getKey, binaryOperator, collectionSupplier);
    }
}
