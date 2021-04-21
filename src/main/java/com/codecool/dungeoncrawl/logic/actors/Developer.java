package com.codecool.dungeoncrawl.logic.actors;

public enum Developer {
    ANITA("Anita"),
    GERI("Geri"),
    RICSI("Ricsi"),
    TAMÁS("Tamás");

    private final String developerName;

    Developer(String developerName) {
        this.developerName = developerName;
    }

    public String getDeveloperName() {
        return developerName;
    }
}
