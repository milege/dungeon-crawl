package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.InventoryModel;

import java.util.List;

public interface InventoryDao {
    void add(InventoryModel inventory, int playerId);
    void update(InventoryModel inventory, int playerId);
    List<String> get(int playerId);
}
