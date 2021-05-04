package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.MonstersModel;

import java.util.List;

public interface MonstersDao {
    void add(MonstersModel monsters);
    void update(MonstersModel monsters);
    MonstersModel get(int id);
    List<MonstersModel> getAll();
}
