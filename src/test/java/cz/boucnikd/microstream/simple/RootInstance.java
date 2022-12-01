package cz.boucnikd.microstream.simple;

import cz.boucnikd.microstream.DTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Getter
public class RootInstance {

    private final String name;
    private final List<DTO> dtos;

    public RootInstance(String name) {
        this.name = name;
        dtos = new ArrayList<>();
    }

    // standard getters, hashcode and equals
}
