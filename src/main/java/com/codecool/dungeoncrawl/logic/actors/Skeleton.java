package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {

    public Skeleton(Cell cell) {
        super(cell);
        this.setAttackStrength(2);
        this.setHealth(10);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public void move(int dx, int dy) {
    }
}
