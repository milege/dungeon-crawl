package com.codecool.dungeoncrawl.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StateLoaderTest {

    @Test
    void UnknownCharacterOnMapThrowsException() {
        assertThrows(RuntimeException.class, () -> StateLoader.loadMapState("/test.txt"));
    }

    @Test
    void loadMapPlacesCellsOnMap() {
        GameMap gameMap = StateLoader.loadMapState("/map2.txt");

        assertEquals(CellType.WALLFENCE, gameMap.getCell(0, 0).getType());
        assertEquals(CellType.EMPTY, gameMap.getCell(14, 0).getType());
        assertEquals(CellType.FLOOR, gameMap.getCell(2, 2).getType());
    }

}
