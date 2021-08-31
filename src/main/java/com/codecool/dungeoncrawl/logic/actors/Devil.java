package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Devil extends Boss {
    public Devil(Cell cell) {
        super(cell);
        this.setAttackStrength(30);
        this.setDefenseStrength(10);
        this.setHealth(60);
    }

    @Override
    public String getTileName() { return "devil"; }

}
