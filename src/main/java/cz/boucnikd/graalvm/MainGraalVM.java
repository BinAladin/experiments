package cz.boucnikd.graalvm;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

public class MainGraalVM {
    static MessageHandler  createPythonHandler(Context context) throws IOException {
        var path = Paths.get("./src/main/python/script.py");

        System.out.println(path.toAbsolutePath().toString());

        Source source = Source.newBuilder("python", path.toFile()).build();
        context.eval(source);
        return context.getBindings("python").getMember("ChatMessageHandler").as(MessageHandler.class);
    }

    public static void main(String[] args) throws Exception {
        var handler = createPythonHandler(PolyglotContextFactory.createContext(PolyglotContextFactory.createEngine()));

        var msg = handler.createMessage("David", "Hello World!");

        System.out.println(msg);
    }
}
