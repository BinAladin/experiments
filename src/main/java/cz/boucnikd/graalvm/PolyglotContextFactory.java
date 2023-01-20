package cz.boucnikd.graalvm;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;

public class PolyglotContextFactory {

    static Engine createEngine() {
        return Engine.newBuilder().build();
    }

    public static Context createContext(Engine engine) {
        return Context.newBuilder("python")
                .allowAllAccess(true)
                .engine(engine)
                .build();
    }
}
