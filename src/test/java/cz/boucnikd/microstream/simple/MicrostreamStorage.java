package cz.boucnikd.microstream.simple;

import cz.boucnikd.microstream.DTO;
import cz.boucnikd.microstream.Storage;
import cz.boucnikd.microstream.StorageManager;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.Random;

public class MicrostreamStorage implements Storage {

    private EmbeddedStorageManager storageManager;

    @Override
    public void init() {
        storageManager = StorageManager.getStorageManager();
        storageManager.setRoot(new RootInstance("baeldung-demo3"));
        storageManager.storeRoot();
    }

    @Override
    public void save(DTO serializable) {
        var dtos = ((RootInstance) storageManager.root()).getDtos();
        dtos.add(serializable);
        storageManager.store(dtos);
        //storageManager.shutdown();
    }

    public void save(Collection<DTO> serializable) {
        var dtos = ((RootInstance) storageManager.root()).getDtos();
        dtos.addAll(serializable);
        storageManager.store(dtos);
        //storageManager.shutdown();
    }

    @Override
    public DTO findById(Long id) throws Exception {
        return ((RootInstance) storageManager.root()).getDtos().stream()
                .filter(d -> d.id() == (long)id)
                .findFirst().orElse(null);
    }

    @Override
    public void shutdown() throws Exception {
        storageManager.shutdown();
    }
}
