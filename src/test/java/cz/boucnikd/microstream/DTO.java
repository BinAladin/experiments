package cz.boucnikd.microstream;

import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
public class DTO implements Serializable {
    private final List<Serializable> data;
}
