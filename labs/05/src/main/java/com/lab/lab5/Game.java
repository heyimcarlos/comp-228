package com.lab.lab5;

public class Game {
    private final int gameId;
    private final String title;

    public Game(int gameId, String title) {
        this.gameId = gameId;
        this.title = title;
    }

    public int getGameId() {
        return gameId;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return title;
    }
}
