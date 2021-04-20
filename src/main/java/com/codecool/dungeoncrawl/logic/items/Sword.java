package com.codecool.dungeoncrawl.logic.items;

public class Sword extends Item {
    public Sword() {
        super("Sword", ItemType.WEAPON);
    }

    public String getTileName() {
        return "sword";
    }
}