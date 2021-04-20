package com.codecool.dungeoncrawl.logic.items;

import java.util.ArrayList;

public class Inventory {
    private final ArrayList<Item> items;

    public Inventory() {
        this.items = new ArrayList<Item>();
    }

    public void addToInventory(Item item) {
        items.add(item);
    }

    public String toString() {
        StringBuilder contents = new StringBuilder();
        contents.append("Inventory:\n");
        for (Item item : items) {
            contents.append(String.format("%s \n", item.getTileName()));
        }
        return String.valueOf(contents);
    }
}
