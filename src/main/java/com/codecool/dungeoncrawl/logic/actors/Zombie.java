package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Zombie extends DeadCorpse {

    public Zombie(Cell cell) {
        super(cell);
        this.setAttackStrength(4);
        this.setDefenseStrength(2);
        this.setHealth(16);
    }

    @Override
    public String getTileName() {
        return "zombie";
    }

}
