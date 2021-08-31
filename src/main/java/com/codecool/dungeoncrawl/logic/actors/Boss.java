package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class Boss extends Actor {
    public Boss(Cell cell) {
        super(cell);
    }

    @Override
    public void move(int dx, int dy) {
    }
}
