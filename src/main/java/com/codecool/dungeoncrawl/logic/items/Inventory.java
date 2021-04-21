package com.codecool.dungeoncrawl.logic.items;

import java.util.ArrayList;

public class Inventory {
    private final ArrayList<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addToInventory(Item item) {
        items.add(item);
    }

    @Override
    public String toString() {
        StringBuilder contents = new StringBuilder();
        contents.append("Inventory:\n");
        for (Item item : items) {
            contents.append(String.format("  %s (%s)\n", item.getName(), item.getType()));
        }
        return String.valueOf(contents);
    }

    public boolean isInInventory(String itemName){
        for (Item item : items){
            if (item.getName().equalsIgnoreCase(itemName)){
                return true;
            }
        }
        return false;
    }

    public void removeItem(String itemName){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(itemName)){
                items.remove(i);
                break;
            }
        }
    }
}
