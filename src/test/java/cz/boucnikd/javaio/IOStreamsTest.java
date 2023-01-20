package cz.boucnikd.javaio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

import static org.junit.jupiter.api.Assertions.*;

class IOStreamsTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void banana() throws Exception {
        var fileReader = new FileReader("./file.txt");
        var fileWriter = new FileWriter("./file.txt");

        //var writer = new Writer();
    }
}
