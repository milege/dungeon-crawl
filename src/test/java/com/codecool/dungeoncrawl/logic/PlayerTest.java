package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Devil;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Sword;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
public class PlayerTest {
    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
    Inventory inventory;
    Sword sword = new Sword();

    @Before
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = Player.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    void attackMonsterIsMonsterAliveReturnsFalse() throws NoSuchFieldException, IllegalAccessException {
        resetSingleton();
        Player player = Player.getInstance(gameMap.getCell(1, 1));
        Skeleton skeleton = new Skeleton(gameMap.getCell(2, 1));
        Cell cell = player.getCell();
        Cell nextCell = cell.getNeighbor(1, 1);
        player.attackMonster(skeleton, nextCell);

        boolean alive = skeleton.isAlive();
        boolean expected = false;

        assertEquals(expected, alive);
    }

    @Test
    void attackMonsterIsMonsterAliveReturnsTrue() throws NoSuchFieldException, IllegalAccessException {
        resetSingleton();
        Player player = Player.getInstance(gameMap.getCell(1, 1));
        Devil devil = new Devil(gameMap.getCell(2, 1));
        Cell cell = player.getCell();
        Cell nextCell = cell.getNeighbor(1, 1);
        player.attackMonster(devil, nextCell);

        boolean alive = devil.isAlive();
        boolean expected = true;

        assertEquals(expected, alive);
    }


    @Test
    void attackMonsterIsPlayerAliveReturnsTrue() throws NoSuchFieldException, IllegalAccessException {
        resetSingleton();
        Player player = Player.getInstance(gameMap.getCell(1, 1));
        Skeleton skeleton = new Skeleton(gameMap.getCell(2, 1));
        Cell cell = player.getCell();
        Cell nextCell = cell.getNeighbor(1, 1);
        player.attackMonster(skeleton, nextCell);

        boolean dead = player.isAlive();
        boolean expected = true;

        assertEquals(expected, dead);
    }

    @Test
    void attackMonsterIsPlayerAliveReturnsFalse() throws NoSuchFieldException, IllegalAccessException {
        resetSingleton();
        Player player = Player.getInstance(gameMap.getCell(1, 1));
        Devil devil = new Devil(gameMap.getCell(2, 1));
        Cell cell = player.getCell();
        Cell nextCell = cell.getNeighbor(1, 1);
        player.attackMonster(devil, nextCell);

        boolean dead = player.isAlive();
        boolean expected = false;

        assertEquals(expected, dead);
    }

    /*@Test
    void pickUpItemIsPickedUp() throws NoSuchFieldException, IllegalAccessException {
        resetSingleton();
        Player player = Player.getInstance(gameMap.getCell(1,1));
        inventory = new Inventory();
        player.pickUpItem();

        Item expected = sword;
        assertEquals(expected, inventory.getItem("sword"));


    }*/
}

