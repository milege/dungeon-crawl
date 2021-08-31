package com.codecool.dungeoncrawl.logic.items;


public class PotionCocktail extends Health {

    public PotionCocktail() {
        super("Potion cocktail", ItemType.HEALTH);
        this.setAdditionalHealth(15);
    }

    public String getTileName() {
        return "potion cocktail";
    }


}