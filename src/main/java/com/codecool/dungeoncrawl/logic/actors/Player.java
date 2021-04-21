package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;


import java.util.*;

import java.sql.SQLOutput;
import java.util.Arrays;


public class Player extends Actor {

    private static Player instance;
    private final Inventory inventory = new Inventory();
    private int vision;

    private String name;

    private Player(Cell cell) {
        super(cell);
        this.setAttackStrength(5);
        this.setDefenseStrength(0);
        this.setHealth(50);
        this.vision = 5;
    }

    public static Player getInstance(Cell cell){
        if(instance == null){
            instance = new Player(cell);
        }
        return instance;
    }

    @Override
    public void move(int dx, int dy) {
        List<String> stepOptions = new ArrayList<>(Arrays.asList("floor", "grass", "open door", "stairs"));
        List<String> wallTypes = new ArrayList<>(Arrays.asList("wall", "wall fence"));
        if (isAlive) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if (stepOptions.contains(nextCell.getTileName()) && nextCell.getActor() == null) {
                manageMovementVisually(nextCell);
            } else if (nextCell.getActor() != null) {
                attackMonster(nextCell.getActor(), nextCell);
            } else if (nextCell.getTileName().equals("fence")) {
                health--;
                if (this.health <= 0) {
                    manageFuneral();
                }
            } else if (wallTypes.contains(nextCell.getTileName()) && name != null) {
                for (Developer developer : Developer.values()) {
                    if (name.equals(developer.getDeveloperName())) {
                        manageMovementVisually(nextCell);
                    }
                }
            }
        }
    }

    private void manageMovementVisually(Cell nextCell) {
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
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
                manageMovementVisually(nextCell);
                this.isInBattle = false;
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
  
    public int getVision(){
        return vision;
    }

    public void setVision(int vision) {
        this.vision = vision;
    }
  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
