package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class Sword extends Weapon {

    public Sword() {
        super("Sword", ItemType.WEAPON);
        this.setAdditionalAttackStrength(10);
    }

    public String getTileName() {
        return "sword";
    }

}