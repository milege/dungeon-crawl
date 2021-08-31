package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.InventoryModel;


public interface InventoryDao {
    void add(InventoryModel inventory, int playerId);
    void update(InventoryModel inventory, int playerId);
    InventoryModel get(int playerId);
}
