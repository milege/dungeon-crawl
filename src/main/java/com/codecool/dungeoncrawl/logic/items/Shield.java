package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class Shield extends Item {

    private final int additionalDefenseStrength = 2;

    public Shield() {
        super("Shield", ItemType.SHIELD);
    }

    public String getTileName() {
        return "shield";
    }

    public int getAdditionalAttackStrength() {
        return additionalDefenseStrength;
    }

    public void startAction(Player player) {
        player.setDefenseStrength(player.getDefenseStrength() + additionalDefenseStrength);
    }

}