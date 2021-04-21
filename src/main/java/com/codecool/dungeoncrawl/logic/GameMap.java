package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;

    private Cell door;

    private List<Actor> monsters = new ArrayList<>();

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
            int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            Random random = new Random();
            int[] direction;
            do {
                direction = directions[random.nextInt(directions.length)];
            } while (monster.getX()+direction[0] < 0 || monster.getX()+direction[0] >= width || monster.getY()+direction[1] < 0 || monster.getY()+direction[1] >= height) ;
            monster.move(direction[0], direction[1]);
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
}
