package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    DOOR("door"),
    OPEN_DOOR("open door"),
    FENCE("fence"),
    GRASS("grass"),
    WALLFENCE("wall fence"),
    WATER("water"),
    DIRT("dirt"),
    STAIRS("stairs");
 /*   WATERCORNER("water corner"),
    WATERRIGHT("water right"),
    WATERLEFT("water left"),*/


    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
