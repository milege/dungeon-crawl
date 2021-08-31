package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.ArrayList;
import java.util.List;

public class MonstersModel extends BaseModel {

    public class MonsterPosition {
        private final String name;
        private final int x;
        private final int y;

        public MonsterPosition(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        public String getName() {
            return name;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private List<MonsterPosition> monsters = new ArrayList<>();

    public MonstersModel() {}

    public MonstersModel(GameMap map) {
        Cell[][] cells = map.getCells();
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (cells[i][j].getActor() != null && cells[i][j].getActor() != map.getPlayer()){
                    String name = cells[i][j].getActor().getTileName();
                    monsters.add(new MonsterPosition(name, i, j));
                }
            }
        }
    }

    public List<MonsterPosition> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<MonsterPosition> monsters) {
        this.monsters = monsters;
    }
}
