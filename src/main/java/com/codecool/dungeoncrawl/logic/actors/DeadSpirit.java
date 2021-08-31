package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class DeadSpirit extends Actor {
    List<String> stepOptions = new ArrayList<>(Arrays.asList("floor", "grass", "wall", "wall fence"));
    Random random = new Random();
    private int teleportCountdown = random.nextInt(8);

    public DeadSpirit(Cell cell) {
        super(cell);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        if(!isInBattle && stepOptions.contains(nextCell.getTileName()) && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            teleportCountdown--;
        }
    }

    @Override
    public int[] getNewDirection(int width, int height, Player player) {
        int[] direction;
        if (teleportCountdown < 0) {
            Cell[][] cells = player.getCell().getGameMap().getCells();
            List<Cell> availableCells = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (stepOptions.contains(cells[x][y].getTileName()) && cells[x][y].getActor() == null) {
                        availableCells.add(cells[x][y]);
                    }
                }
            }
            teleportCountdown = random.nextInt(8);
            Cell randomCell = availableCells.get(random.nextInt(availableCells.size()));
            direction = new int[]{randomCell.getX() - this.getX(), randomCell.getY() - this.getY()};

        } else {
            do {
                direction = directions[random.nextInt(directions.length)];
            } while (this.getX()+direction[0] < 0 || this.getX()+direction[0] >= width || this.getY()+direction[1] < 0 || this.getY()+direction[1] >= height);
        }
        return direction;
    }

}
