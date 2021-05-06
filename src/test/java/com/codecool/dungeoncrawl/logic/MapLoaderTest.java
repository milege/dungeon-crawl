package com.codecool.dungeoncrawl.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapLoaderTest {

    @Test
    void UnknownCharacterOnMapThrowsException() {
        assertThrows(RuntimeException.class, () -> MapLoader.loadMap("/test.txt", CellType.FLOOR));
    }
}
