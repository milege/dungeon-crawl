package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Devil;
import com.codecool.dungeoncrawl.logic.actors.Faceless;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BossTest {
    GameMap gameMap = new GameMap(5, 5, CellType.FLOOR);

    @Test
    void FacelessDoesntMove() {
        Faceless faceless = new Faceless(gameMap.getCell(2, 1));
        faceless.move(1, 0);

        assertNull(gameMap.getCell(3, 1).getActor());
    }

    @Test
    void DevilDoesntMove() {
        Devil devil = new Devil(gameMap.getCell(2, 1));
        devil.move(1, 0);

        assertNull(gameMap.getCell(3, 1).getActor());
    }

}
