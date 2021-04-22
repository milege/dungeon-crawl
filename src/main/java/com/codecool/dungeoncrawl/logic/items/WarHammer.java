package com.codecool.dungeoncrawl.logic.items;


public class WarHammer extends Weapon {

    public WarHammer() {
        super("War hammer", ItemType.WEAPON);
        this.setAdditionalAttackStrength(20);
    }

    public String getTileName() {
        return "war hammer";
    }

}