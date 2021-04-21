package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends DeadSpirit {
    public Ghost(Cell cell) {
        super(cell);
        this.setAttackStrength(3);
        this.setDefenseStrength(2);
        this.setHealth(12);
    }

    @Override
    public String getTileName() { return "ghost"; }

}
