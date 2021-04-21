package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;

public abstract class Shield extends Item {

    private int additionalDefenseStrength = 0;

    public Shield(String name, ItemType type) {
        super("Shield", ItemType.SHIELD);
    }

    public int getAdditionalDefenseStrength() {
        return additionalDefenseStrength;
    }

    public void setAdditionalDefenseStrength(int additionalDefenseStrength) {
        this.additionalDefenseStrength = additionalDefenseStrength;
    }

    public void startAction(Player player) {
        player.setDefenseStrength(player.getDefenseStrength() + additionalDefenseStrength);
    }

}