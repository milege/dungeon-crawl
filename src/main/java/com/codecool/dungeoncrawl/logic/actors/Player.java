package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Player extends Actor {

    private final Inventory inventory = new Inventory();

    public Player(Cell cell) {
        super(cell);
        this.setAttackStrength(5);
    }

    public Inventory getInventory(){
        return inventory;
    }

    public String getTileName() {
        return "player";
    }

    public void pickUpItem(){
        Item pickedItem = getCell().getItem();
        if(pickedItem != null) {
            getCell().setItem(null);
            inventory.addToInventory(pickedItem);
        }
    }
}
