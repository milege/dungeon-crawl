package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player extends Actor {

    private final Inventory inventory = new Inventory();

    public Player(Cell cell) {
        super(cell);
        this.setAttackStrength(5);
        this.setDefenseStrength(0);
        this.setHealth(50);
    }

    @Override
    public void move(int dx, int dy) {
        List<String> stepOptions = new ArrayList<>(Arrays.asList("floor", "grass", "open door"));
        if (isAlive) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if ((nextCell.getTileName().equals("floor") && nextCell.getActor() == null) || nextCell.getTileName().equals("open door")) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            } else if (nextCell.getActor() != null) {
                attackMonster(nextCell.getActor(), nextCell);
            } else if (nextCell.getTileName().equals("fence")) {
                health --;
                if (this.health <= 0) {
                    manageFuneral();
                }
            }
        }
    }

    public void attackMonster(Actor monster, Cell nextCell) {
        this.isInBattle = true;
        monster.setInBattle(true);
        while (this.isAlive && monster.isAlive()) {
            if (monster.getDefenseStrength() < this.attackStrength) {
                monster.setHealth(monster.getHealth() + monster.getDefenseStrength() - this.attackStrength);
            }
            if (monster.getHealth() <= 0) {
                monster.setAlive(false);
                cell.setActor(null);
                nextCell.setActor(this);
                this.isInBattle = false;
                cell = nextCell;
            } else {
                if (this.defenseStrength < monster.getAttackStrength()) {
                    this.health = this.health + this.defenseStrength - monster.getAttackStrength();
                }
            }
            if (this.health <= 0) {
                manageFuneral();
                monster.isInBattle = false;
            }
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getTileName() {
        return "player";
    }

    public void pickUpItem() {
        Item pickedItem = cell.getItem();
        if (pickedItem != null) {
            cell.setItem(null);
            inventory.addToInventory(pickedItem);
            pickedItem.startAction(this);
        }
    }

    private void manageFuneral() {
        isAlive = false;
        cell.setActor(new Corpse(cell));
        System.out.println("Game over! Rest in peace!");
    }
}
