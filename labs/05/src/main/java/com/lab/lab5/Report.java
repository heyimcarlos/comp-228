package com.lab.lab5;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Report {
    private final IntegerProperty playerId;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty gameTitle;
    private final StringProperty score;

    public Report(int playerId, String firstName, String lastName, String gameTitle, String score) {
        this.playerId = new SimpleIntegerProperty(playerId);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.gameTitle = new SimpleStringProperty(gameTitle);
        this.score = new SimpleStringProperty(score);
    }

    public IntegerProperty getPlayerId() {
        return playerId;
    }

    public StringProperty getFirstName() {
        return firstName;
    }

    public StringProperty getLastName() {
        return lastName;
    }

    public StringProperty getGameTitle() {
        return gameTitle;
    }

    public StringProperty getScore() {
        return score;
    }
}
