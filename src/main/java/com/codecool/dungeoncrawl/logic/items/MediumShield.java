package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class MediumShield extends Shield {

    public MediumShield() {
        super("Medium shield", ItemType.SHIELD);
        this.setAdditionalDefenseStrength(5);
    }

    public String getTileName() {
        return "medium shield";
    }

}