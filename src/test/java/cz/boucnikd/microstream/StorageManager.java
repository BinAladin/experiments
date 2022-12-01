package cz.boucnikd.microstream;

import lombok.Getter;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;

import java.nio.file.Paths;

public class StorageManager {
    @Getter
    private static final EmbeddedStorageManager storageManager = EmbeddedStorage.start(Paths.get("."));;
}
