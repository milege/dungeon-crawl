package com.codecool.dungeoncrawl.logic.items;


public class MediumShield extends Shield {

    public MediumShield() {
        super("Medium shield", ItemType.SHIELD);
        this.setAdditionalDefenseStrength(5);
    }

    public String getTileName() {
        return "medium shield";
    }

}