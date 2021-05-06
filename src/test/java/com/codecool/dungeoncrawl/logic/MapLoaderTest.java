package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.items.MediumShield;
import com.codecool.dungeoncrawl.logic.items.SmallShield;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapLoaderTest {

    @Test
    void UnknownCharacterOnMapThrowsException() {
        assertThrows(RuntimeException.class, () -> MapLoader.loadMap("/test.txt", CellType.FLOOR));
    }

    @Test
    void loadMapPlacesCellsOnMap() {
        GameMap gameMap = MapLoader.loadMap("/map2.txt", CellType.FLOOR);
        MediumShield mediumShield = new MediumShield();
        String expected = mediumShield.getTileName();

        assertEquals(CellType.WALLFENCE, gameMap.getCell(0, 0).getType());
        assertEquals(CellType.EMPTY, gameMap.getCell(14, 0).getType());
        assertEquals(CellType.FLOOR, gameMap.getCell(2, 2).getType());
        assertEquals(expected, gameMap.getCell(4, 22).getItem().getTileName());
    }
}
