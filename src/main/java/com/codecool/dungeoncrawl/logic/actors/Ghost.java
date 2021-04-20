package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Actor {
    public Ghost(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() { return "ghost"; }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);

        if((nextCell.getTileName().equals("floor") || nextCell.getTileName().equals("wall")) && nextCell.getActor() == null){
            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
        }
    }

}
