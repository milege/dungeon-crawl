package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;

public abstract class Health extends Item {

    private int additionalHealth = 0;
    public Health(String name, ItemType type) {
        super(name, type);
    }

    public int getAdditionalHealth() {
        return additionalHealth;
    }

    public void setAdditionalHealth(int additionalHealth) {
        this.additionalHealth = additionalHealth;
    }

    public void startAction(Player player) {
        player.setHealth(player.getHealth() + additionalHealth);
    }

}