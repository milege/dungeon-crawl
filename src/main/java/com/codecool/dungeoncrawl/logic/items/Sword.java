package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class Sword extends Item {

    private final int additionalAttackStrength = 10;

    public Sword() {
        super("Sword", ItemType.WEAPON);
    }

    public String getTileName() {
        return "sword";
    }

    public int getAdditionalAttackStrength() {
        return additionalAttackStrength;
    }

    public void startAction(Player player) {
        player.setAttackStrength(player.getAttackStrength() + additionalAttackStrength);
    }

}