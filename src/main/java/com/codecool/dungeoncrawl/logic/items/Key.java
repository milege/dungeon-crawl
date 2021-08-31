package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class Key extends Item {
    public Key() {
        super("Key", ItemType.KEY);
    }

    public String getTileName() {
        return "key";
    }

    public void startAction(Player player) {
        player.getCell().getGameMap().openDoor();
    }
}
