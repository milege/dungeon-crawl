package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ItemsModel;


public interface ItemsDao {
    void add(ItemsModel items, int gameId);
    void update(ItemsModel items, int gameId);
    ItemsModel get(int id);
}
