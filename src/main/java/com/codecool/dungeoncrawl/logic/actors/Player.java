package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Player extends Actor {
    private Inventory inventory = new Inventory();

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    public void pickUpItem(){
        Item pickedItem = getCell().getItem();
        getCell().setItem(null);
        // pickedItem -> inventory
    }
}
