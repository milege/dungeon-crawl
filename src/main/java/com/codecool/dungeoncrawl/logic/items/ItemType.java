package com.codecool.dungeoncrawl.logic.items;

public enum ItemType {
    KEY("key"),
    WEAPON("weapon"),
    VISION("vision"),
    SHIELD("shield"),
    HEALTH("health");

    private final String itemType;

    ItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getTypeName() {
        return itemType;
    }
}
