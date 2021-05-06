package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.ArrayList;
import java.util.List;

public class ItemsModel extends BaseModel {
    public class ItemPosition {
        private final String name;
        private final int x;
        private final int y;

        public ItemPosition(String name, int x, int y) {
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

    private List<ItemPosition> items = new ArrayList<>();

    public ItemsModel() {}

    public ItemsModel(GameMap map) {
        Cell[][] cells = map.getCells();
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (cells[i][j].getItem() != null){
                    String name = cells[i][j].getItem().getTileName();
                    items.add(new ItemPosition(name, i, j));
                }
            }
        }
    }

    public List<ItemPosition> getItems() {
        return items;
    }

    public void setItems(List<ItemPosition> items) {
        this.items = items;
    }

}
