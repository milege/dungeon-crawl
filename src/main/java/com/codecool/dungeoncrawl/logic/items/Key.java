package com.codecool.dungeoncrawl.logic.items;

public class Key extends Item {
    public Key() {
        super("Key", ItemType.KEY);
    }

    public String getTileName() {
        return "key";
    }
}
