package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    private int attackStrength;
    private boolean isAlive = true;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        if (nextCell.getTileName().equals("floor") && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.getActor() != null) {
//            Main.setMonsterHealthLabel("" + cell.getNeighbor(dx, dy));
            attackMonster(nextCell.getActor(), nextCell);
        }
    }

    public void attackMonster(Actor monster, Cell nextCell) {
        while (this.isAlive && monster.isAlive()) {
            monster.setHealth(monster.getHealth() - this.attackStrength);
            if (monster.getHealth() == 0) {
                monster.setAlive(false);
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            } else {
                this.health = this.health - monster.getAttackStrength();
            }
            if (this.health == 0) {
                this.isAlive = false;
                cell.setActor(null);
            }
        }
    }

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

    public void setCell(Cell cell) {
        this.cell = cell;

    }
}
