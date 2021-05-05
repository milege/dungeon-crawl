package com.codecool.dungeoncrawl.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameState extends BaseModel {
    private LocalDateTime savedAt;
    private String currentMap;
    private List<String> discoveredMaps = new ArrayList<>();
    private int playerId;

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

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }

    public String getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(String currentMap) {
        this.currentMap = currentMap;
    }

    public List<String> getDiscoveredMaps() {
        return discoveredMaps;
    }

    public void addDiscoveredMap(String map) {
        this.discoveredMaps.add(map);
    }

}
