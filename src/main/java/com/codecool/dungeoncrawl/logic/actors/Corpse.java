package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Corpse extends Actor {
    public Corpse(Cell cell) {
        super(cell);
        this.setAttackStrength(0);
        this.setDefenseStrength(0);
        this.setHealth(0);
    }

    @Override
    public String getTileName() { return "corpse"; }

    @Override
    public void move(int dx, int dy) {
    }

}
