package cz.boucnikd.microstream;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class BinaryFileStorage implements Storage {

    private ObjectOutputStream file;

    @Override
    public void init() throws Exception {
        file = new ObjectOutputStream(new FileOutputStream("data.dat"));
    }

    @Override
    public void save(DTO dto) throws Exception {
        file.writeObject(dto);
    }
}
