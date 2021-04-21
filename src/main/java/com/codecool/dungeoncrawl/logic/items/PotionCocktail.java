package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class PotionCocktail extends Health {

    public PotionCocktail() {
        super("Potion cocktail", ItemType.HEALTH);
        this.setAdditionalHealth(15);
    }

    public String getTileName() {
        return "potion cocktail";
    }


}