package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Faceless extends Boss {
    public Faceless(Cell cell) {
        super(cell);
        this.setAttackStrength(10);
        this.setDefenseStrength(5);
        this.setHealth(50);
    }

    @Override
    public String getTileName() { return "faceless"; }

}
