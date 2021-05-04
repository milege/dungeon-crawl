package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.ArrayList;
import java.util.List;

public class MonstersModel {

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

    private final List<MonsterPosition> monsters = new ArrayList<>();

    public MonstersModel(GameMap map) {
        Cell[][] cells = map.getCells();
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (cells[i][j].getActor() != null && cells[i][j].getActor() != map.getPlayer()){
                    String name = cells[i][j].getActor().getTileName();
                    int x = cells[i][j].getActor().getX();
                    int y = cells[i][j].getActor().getY();
                    monsters.add(new MonsterPosition(name, x, y));
                }
            }
        }
    }

    public List<MonsterPosition> getMonsters() {
        return monsters;
    }
}
