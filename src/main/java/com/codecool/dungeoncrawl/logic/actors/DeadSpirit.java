package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DeadSpirit extends Actor {
    public DeadSpirit(Cell cell) {
        super(cell);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        List<String> stepOptions = new ArrayList<>(Arrays.asList("floor", "grass", "wall", "wall fence"));

        if(!isInBattle && (stepOptions.contains(nextCell.getTileName())) &&
                nextCell.getActor() == null){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

}
