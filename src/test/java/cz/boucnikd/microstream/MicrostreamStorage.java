package cz.boucnikd.microstream;

import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;

import java.nio.file.Paths;

public class MicrostreamStorage implements Storage {

    private EmbeddedStorageManager storageManager;

    @Override
    public void init() {
        storageManager = EmbeddedStorage.start(Paths.get("."));
        storageManager.setRoot(new RootInstance("baeldung-demo"));
        storageManager.storeRoot();
    }

    @Override
    public void save(DTO serializable) {
        var dtos = ((RootInstance) storageManager.root()).getDtos();
        dtos.add(serializable);
        storageManager.store(dtos);
        //storageManager.shutdown();
    }
}
