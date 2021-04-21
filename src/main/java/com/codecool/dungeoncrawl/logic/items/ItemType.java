package com.codecool.dungeoncrawl.logic.items;

public enum ItemType {
    KEY("key"),
    WEAPON("weapon"),
    ARMOR("armor"),
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
