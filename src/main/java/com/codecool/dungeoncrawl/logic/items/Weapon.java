package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;

public abstract class Weapon extends Item {

    private int additionalAttackStrength;

    public Weapon(String name, ItemType type) {
        super("Weapon", ItemType.WEAPON);
    }

    public int getAdditionalAttackStrength() {
        return additionalAttackStrength;
    }

    public void startAction(Player player) {
        player.setAttackStrength(player.getAttackStrength() + additionalAttackStrength);
    }

    public void setAdditionalAttackStrength(int additionalAttackStrength) {
        this.additionalAttackStrength = additionalAttackStrength;
    }
}