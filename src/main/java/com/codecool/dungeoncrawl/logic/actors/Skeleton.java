package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends DeadCorpse {

    public Skeleton(Cell cell) {
        super(cell);
        this.setAttackStrength(2);
        this.setDefenseStrength(1);
        this.setHealth(10);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

}
