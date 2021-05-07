package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.MonstersModel;

public interface MonstersDao {
    void add(MonstersModel monsters, int gameId);
    void update(MonstersModel monsters, int gameId);
    MonstersModel get(int id);
}
