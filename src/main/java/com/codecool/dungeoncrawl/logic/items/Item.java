package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.actors.Player;

public abstract class Item implements Drawable {
    private final String name;
    private final ItemType type;

    public Item(String name, ItemType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type.getTypeName();
    }

    public abstract void startAction(Player player);
}
