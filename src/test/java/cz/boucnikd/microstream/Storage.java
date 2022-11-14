package cz.boucnikd.microstream;

public interface Storage {
    void init() throws Exception;

    void save(DTO dto) throws Exception;
}
