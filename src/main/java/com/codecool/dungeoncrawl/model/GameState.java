package com.codecool.dungeoncrawl.model;

import java.time.LocalDateTime;

public class GameState extends BaseModel {
    private final LocalDateTime savedAt;
    private final String currentMap;
    private final int playerId;

    public GameState(String currentMap, LocalDateTime savedAt, int playerId) {
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public String getCurrentMap() {
        return currentMap;
    }

}
