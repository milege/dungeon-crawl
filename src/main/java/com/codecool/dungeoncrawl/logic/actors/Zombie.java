package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zombie extends DeadCorpse {
    private final List<String> stepOptions = new ArrayList<>(Arrays.asList("floor", "dirt", "grass"));
    private boolean zombiesTurn = true;

    public Zombie(Cell cell) {
        super(cell);
        this.setAttackStrength(4);
        this.setDefenseStrength(2);
        this.setHealth(16);
    }

    @Override
    public String getTileName() {
        return "zombie";
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (zombiesTurn && !isInBattle && nextCell.getActor() == null) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
        }
        zombiesTurn = !zombiesTurn;
    }

    @Override
    public int[] getNewDirection(int width, int height, Player player) {
        int[] direction = new int[]{0, 0};
        Cell playerCell = player.getCell();
        if (playerCell.getX() > this.getX() && isValidMove(1, 0, width, height))
            direction = new int[]{1, 0};
        else if (playerCell.getX() < this.getX() && isValidMove(-1, 0, width, height))
            direction = new int[]{-1, 0};
        else if (playerCell.getY() < this.getY() && isValidMove(0, -1, width, height))
            direction = new int[]{0, -1};
        else if (playerCell.getY() > this.getY() && isValidMove(0, 1, width, height))
            direction = new int[]{0, 1};
        return direction;
    }

    private boolean isValidMove(int x, int y, int width, int height) {
        return this.getX()+x >= 0 && this.getX()+x < width && this.getY()+y >= 0 && this.getY()+y < height && stepOptions.contains(cell.getNeighbor(x, y).getTileName());
    }

}
