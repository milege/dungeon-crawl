package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class SmallShield extends Shield {

    public SmallShield() {
        super("Small shield", ItemType.SHIELD);
        this.setAdditionalDefenseStrength(2);
    }

    public String getTileName() {
        return "small shield";
    }

}