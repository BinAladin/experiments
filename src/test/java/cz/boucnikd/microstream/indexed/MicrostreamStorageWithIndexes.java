package cz.boucnikd.microstream.indexed;

import cz.boucnikd.microstream.DTO;
import cz.boucnikd.microstream.Storage;
import cz.boucnikd.microstream.StorageManager;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

public class MicrostreamStorageWithIndexes implements Storage {

    private EmbeddedStorageManager storageManager;

    @Override
    public void init() {
        storageManager = StorageManager.getStorageManager();
        storageManager.setRoot(new RootInstance("baeldung-demo2"));
        storageManager.storeRoot();
    }

    @Override
    public void save(DTO serializable) {
        var root = ((RootInstance) storageManager.root());

        root.dtos().add(serializable);
        storageManager.store(root.dtos());

        root.dtosById().put(serializable.id(), serializable);
        storageManager.store(root.dtosById());
    }

    public void save(Collection<DTO> serializable) {
        var root = ((RootInstance) storageManager.root());

        root.dtos().addAll(serializable);

        root.dtosById().putAll(serializable.stream().collect(Collectors.toMap(DTO::id, d -> d)));

        storageManager.storeAll(root.dtosById(), root.dtos());
    }

    @Override
    public DTO findById(Long id) throws Exception {
        return ((RootInstance) storageManager.root()).dtosById().get(id);
    }

    @Override
    public void shutdown() throws Exception {
        storageManager.shutdown();
    }
}
