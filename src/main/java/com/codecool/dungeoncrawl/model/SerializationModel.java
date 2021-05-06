package com.codecool.dungeoncrawl.model;

public class SerializationModel {
    private final String currentMap;
    private final  PlayerModel playerModel;
    private final  InventoryModel inventoryModel;
    private final MonstersModel monstersModel;
    private final ItemsModel itemsModel;

    public SerializationModel(String currentMap, PlayerModel playerModel, InventoryModel inventoryModel, MonstersModel monstersModel, ItemsModel itemsModel) {
        this.currentMap = currentMap;
        this.playerModel = playerModel;
        this.inventoryModel = inventoryModel;
        this.monstersModel = monstersModel;
        this.itemsModel = itemsModel;
    }

    public String getCurrentMap() {
        return currentMap;
    }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    public InventoryModel getInventoryModel() {
        return inventoryModel;
    }

    public MonstersModel getMonstersModel() {
        return monstersModel;
    }

    public ItemsModel getItemsModel() {
        return itemsModel;
    }
}
