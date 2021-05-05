package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;

public class InventoryModel extends BaseModel {
    private List<String> items = new ArrayList<>();

    public InventoryModel(Inventory inventory) {
        for (Item item : inventory.getItems()) {
            items.add(item.getName());
        }
    }

    public InventoryModel(List<String> items) {
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }
}
