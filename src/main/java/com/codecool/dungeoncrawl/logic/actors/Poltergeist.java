package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Poltergeist extends DeadSpirit {
    public Poltergeist(Cell cell) {
        super(cell);
        this.setAttackStrength(6);
        this.setDefenseStrength(4);
        this.setHealth(18);
    }

    @Override
    public String getTileName() { return "poltergeist"; }

}
