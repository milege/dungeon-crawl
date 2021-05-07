package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private final int width;
    private final int height;
    private final Cell[][] cells;


    private Player player;

    private Cell door;

    private final List<Actor> monsters = new ArrayList<>();

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setDoor(Cell doorCell) { this.door = doorCell; }

    public void addMonster(Actor monster) {
        monsters.add(monster);
    }

    public void moveMonsters() {
        for (Actor monster : monsters) {
            int[] coordinates = monster.getNewDirection(width, height, this.getPlayer());
            monster.move(coordinates[0], coordinates[1]);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void openDoor() {
        door.setType(CellType.OPEN_DOOR);
    }

    public Cell[][] getCells() {
        return cells;
    }
}
