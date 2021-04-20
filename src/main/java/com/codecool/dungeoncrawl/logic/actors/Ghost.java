package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Actor {
    public Ghost(Cell cell) {
        super(cell);
        this.setAttackStrength(3);
        this.setHealth(12);
    }

    @Override
    public String getTileName() { return "ghost"; }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        if((nextCell.getTileName().equals("floor") || nextCell.getTileName().equals("wall")) && nextCell.getActor() == null){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

}
