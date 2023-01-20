package cz.boucnikd.ocp;

import java.io.IOException;

public class ExceptionHandlingOne {
    public static void main(String[] args) throws Exception {
        class myResource implements AutoCloseable {

            public AutoCloseable open() throws IOException {
                throw new IOException("open");
            }

            @Override
            public void close() throws Exception {
                throw new ArithmeticException("close");
            }
        }

        try (var rsrc = new myResource()) {
            rsrc.open();
            throw new NullPointerException("try");
        }
    }
}
