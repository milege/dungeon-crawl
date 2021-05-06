package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Zombie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ZombieTest {
    GameMap gameMap = new GameMap(5, 5, CellType.FLOOR);
    Player player = Player.getInstance(gameMap.getCell(1, 1));

    @Test
    void zombiesOnlyMoveOnceEveryTwoRounds() {
        Zombie zombie = new Zombie(gameMap.getCell(2, 1));
        zombie.move(1, 0);
        zombie.move(1, 0);

        assertEquals(3, zombie.getX());
        assertEquals(1, zombie.getY());
    }

    @Test
    void zombiesMoveTowardPlayer() {
        Zombie zombie = new Zombie(gameMap.getCell(3, 1));
        int[] coordinates = zombie.getNewDirection(gameMap.getWidth(), gameMap.getHeight(), player);
        zombie.move(coordinates[0], coordinates[1]);

        assertEquals(2, zombie.getX());
        assertEquals(1, zombie.getY());
    }
}
