package com.codecool.dungeoncrawl.model;

public class InventoryModel extends BaseModel {
    private String itemName;
    private int playerId;


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

}
