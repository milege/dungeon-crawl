package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class Potion extends Item {

    private final int additionalHealth = 5;
    public Potion() {
        super("Potion", ItemType.HEALTH);
    }

    public String getTileName() {
        return "potion";
    }

    public int getAdditionalHealth() {
        return additionalHealth;
    }

    public void startAction(Player player) {
        player.setHealth(player.getHealth() + additionalHealth);
    }

}