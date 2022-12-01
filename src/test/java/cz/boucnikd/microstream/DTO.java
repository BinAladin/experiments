package cz.boucnikd.microstream;

import java.io.Serializable;
import java.util.List;


public record DTO(Long id, List<Serializable> data) implements Serializable {

}
