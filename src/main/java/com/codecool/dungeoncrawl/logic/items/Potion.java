package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class Potion extends Health {

    public Potion() {
        super("Potion", ItemType.HEALTH);
        this.setAdditionalHealth(5);
    }

    public String getTileName() {
        return "potion";
    }

}