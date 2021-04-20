package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.sql.SQLOutput;

public class Player extends Actor {

    private final Inventory inventory = new Inventory();

    public Player(Cell cell) {
        super(cell);
        this.setAttackStrength(5);
        this.setHealth(50);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        if((nextCell.getTileName().equals("floor") && nextCell.getActor() == null) || (nextCell.getTileName().equals("door") && inventory.isInInventory("key"))) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.getActor() != null) {
//            nextCell.setMonsterHealthLabel("" + cell.getNeighbor(dx, dy));
            attackMonster(nextCell.getActor(), nextCell);
        }
    }

    public void attackMonster(Actor monster, Cell nextCell) {
        this.isInBattle = true;
        monster.setInBattle(true);
        while (this.isAlive && monster.isAlive()) {
            monster.setHealth(monster.getHealth() - this.attackStrength);
            if (monster.getHealth() <= 0) {
                monster.setAlive(false);
                cell.setActor(null);
                nextCell.setActor(this);
                this.isInBattle = false;
                cell = nextCell;
            } else {
                this.health = this.health - monster.getAttackStrength();
            }
            if (this.health <= 0) {
                this.isAlive = false;
                monster.isInBattle = false;
                cell.setActor(null);
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
}
