package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
public class PlayerTest {
    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);

    Player player;

    @Before
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = Player.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }
}
