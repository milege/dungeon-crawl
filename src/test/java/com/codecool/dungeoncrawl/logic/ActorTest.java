package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
    Player player = Player.getInstance(gameMap.getCell(1, 1));

    @Test
    void moveUpdatesCells() {
        Player player = Player.getInstance(gameMap.getCell(1, 1));
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
        assertEquals(null, gameMap.getCell(1, 1).getActor());
        assertEquals(player, gameMap.getCell(2, 1).getActor());
    }

    @Test
    void cannotMoveIntoWall() {
        gameMap.getCell(2, 1).setType(CellType.WALL);
        Player player = Player.getInstance(gameMap.getCell(1, 1));
        player.move(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void cannotMoveOutOfMap() {
        Player player = Player.getInstance(gameMap.getCell(2, 1));
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void cannotMoveIntoAnotherActor() {
        Player player = Player.getInstance(gameMap.getCell(1, 1));
        Skeleton skeleton = new Skeleton(gameMap.getCell(2, 1));
        player.move(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
        assertEquals(2, skeleton.getX());
        assertEquals(1, skeleton.getY());
        assertEquals(skeleton, gameMap.getCell(2, 1).getActor());
    }

    @Test
    void getNewDirectionProvidesRandomDirectionWithinMapRange() {
        Skeleton skeleton = new Skeleton(gameMap.getCell(2, 1));
        int[] direction = skeleton.getNewDirection(gameMap.getWidth(), gameMap.getHeight(), player);
        int newXCoordinate = skeleton.getX() + direction[0];
        int newYCoordinate = skeleton.getY() + direction[1];

        assertTrue(newXCoordinate < gameMap.getWidth() && newXCoordinate >= 0 && newYCoordinate < gameMap.getHeight() && newYCoordinate >= 0);
    }
}