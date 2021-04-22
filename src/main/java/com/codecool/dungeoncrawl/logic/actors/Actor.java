package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.Random;

public abstract class Actor implements Drawable {
    protected Cell cell;
    protected int health;
    protected int attackStrength;
    protected int defenseStrength;
    protected boolean isAlive = true;
    protected boolean isInBattle = false;
    protected int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public abstract void move(int dx, int dy);

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackStrength() {
        return attackStrength;
    }

    public void setAttackStrength(int attackStrength) {
        this.attackStrength = attackStrength;
    }

    public int getDefenseStrength() {
        return defenseStrength;
    }

    public void setDefenseStrength(int defenseStrength) {
        this.defenseStrength = defenseStrength;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setCell(Cell cell) {
            this.cell = cell;
        }

    public void setInBattle(boolean inBattle) {
        isInBattle = inBattle;
    }

    public int[] getNewDirection(int width, int height, Player player) {
        Random random = new Random();
        int[] direction;
        do {
            direction = directions[random.nextInt(directions.length)];
        } while (this.getX()+direction[0] < 0 || this.getX()+direction[0] >= width || this.getY()+direction[1] < 0 || this.getY()+direction[1] >= height);
        return direction;
    }
}