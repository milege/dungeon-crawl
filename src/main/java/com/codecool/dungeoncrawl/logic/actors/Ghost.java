package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ghost extends Actor {
    public Ghost(Cell cell) {
        super(cell);
        this.setAttackStrength(3);
        this.setDefenseStrength(2);
        this.setHealth(12);
    }

    @Override
    public String getTileName() { return "ghost"; }

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
