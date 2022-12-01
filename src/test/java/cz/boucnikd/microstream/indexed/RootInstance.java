package cz.boucnikd.microstream.indexed;

import cz.boucnikd.microstream.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record RootInstance(String name, List<DTO> dtos, Map<Long, DTO> dtosById) {

    public RootInstance(String name) {
        this(name, new ArrayList<>(), new HashMap<>());
    }
}
