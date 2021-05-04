package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;

public class InventoryModel {
    private ArrayList<String> items = new ArrayList<>();

    public InventoryModel(Inventory inventory) {
        for (Item item : inventory.getItems()) {
            items.add(item.getName());
        }
    }

    public ArrayList<String> getItems() {
        return items;
    }
}
