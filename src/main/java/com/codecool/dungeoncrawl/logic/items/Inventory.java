package com.codecool.dungeoncrawl.logic.items;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;

    public Inventory() {
        this.items = new ArrayList<Item>();
    }

    public void addToInventory(Item item) {
        items.add(item);
    }
}
