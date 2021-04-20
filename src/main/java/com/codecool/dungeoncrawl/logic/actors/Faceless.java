package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Faceless extends Actor {
    public Faceless(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() { return "faceless"; }

    @Override
    public void move(int dx, int dy) {
    }
}
