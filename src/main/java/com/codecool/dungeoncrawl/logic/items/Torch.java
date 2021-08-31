package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class Torch extends Item {
    private final int extraVision = 6;

    public Torch() {
        super("Torch", ItemType.VISION);
    }

    public String getTileName() {
        return "torch";
    }

    public int getExtraVision() {
        return extraVision;
    }

    public void startAction(Player player) {
        player.setVision(player.getVision() + extraVision);
    }
}
