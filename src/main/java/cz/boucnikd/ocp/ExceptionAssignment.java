package cz.boucnikd.ocp;

import java.io.FileReader;
import java.io.IOException;

public class ExceptionAssignment {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("c:\\data\\input - text.txt");
            int data = fileReader.read();
        } catch (IOException | IllegalStateException ex) {

        }
    }
}
