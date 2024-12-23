package com.lab.lab5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.control.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GamePlayerApp extends Application {
    // players
    ListView<Player> players = new ListView<>();

    @Override
    public void start(Stage stage) {
        TabPane root = new TabPane();

        // Tab 1: Insertion
        Tab insertionTab = new Tab("New Player & Score");
        insertionTab.setContent(getInsertPane());
        insertionTab.setClosable(false);

        // Tab 2: Update
        Tab updateTab = new Tab("Update Player");
        updateTab.setContent(getUpdatePane());
        updateTab.setClosable(false);

        // Tab 3: Report
        Tab reportsTab = new Tab("Report");
        reportsTab.setContent(getReportPane());
        reportsTab.setClosable(false);

        root.getTabs().addAll(insertionTab, updateTab, reportsTab);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Game Player App");
        stage.setScene(scene);
        stage.show();
    }

    public Pane getReportPane() {
        BorderPane root = new BorderPane();

        // Player section
        ComboBox<Player> playerComboBox = new ComboBox<>();
        playerComboBox.getItems().addAll(fetchPlayers());
        playerComboBox.setPromptText("Select Player");

        // TableView for reports
        TableView<Report> reportsTable = new TableView<>();
        TableColumn<Report, Integer> playerIdColumn = new TableColumn<>("Player ID");
        playerIdColumn.setCellValueFactory(cellData -> cellData.getValue().getPlayerId().asObject());

        TableColumn<Report, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());

        TableColumn<Report, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastName());

        TableColumn<Report, String> gameTitleColumn = new TableColumn<>("Game Title");
        gameTitleColumn.setCellValueFactory(cellData -> cellData.getValue().getGameTitle());

        TableColumn<Report, String> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().getScore());

        reportsTable.getColumns().addAll(playerIdColumn, firstNameColumn, lastNameColumn, gameTitleColumn, scoreColumn);

        reportsTable.getItems().addAll(fetchReports(null));

        playerComboBox.setOnAction(e -> {
            Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();
            if (selectedPlayer != null) {
                reportsTable.getItems().setAll(fetchReports(selectedPlayer.getId()));
            } else {
                reportsTable.getItems().setAll(fetchReports(null));
            }
        });

        VBox filterBox = new VBox(10);
        filterBox.setPadding(new Insets(10));
        filterBox.getChildren().addAll(new Label("Filter by Player:"), playerComboBox);

        root.setTop(filterBox);
        root.setCenter(reportsTable);

        return root;

    }

    // Fetch reports
    private ArrayList<Report> fetchReports(Integer playerId) {
        ArrayList<Report> reports = new ArrayList<>();
        String sql = """
                SELECT p.player_id, p.first_name, last_name, g.game_title, pg.score
                FROM carlos_dlc_player p
                JOIN carlos_dlc_player_game pg ON p.player_id = pg.player_id
                JOIN carlos_dlc_game g ON pg.game_id = g.game_id
                """;

        if (playerId != null) {
            sql += "WHERE p.player_id = ?";
        }

        System.out.println(sql);
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);) {
            if (playerId != null) {
                statement.setInt(1, playerId);
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Report report = new Report(rs.getInt("player_id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("game_title"), rs.getString("score"));
                reports.add(report);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return reports;
    }

    public Pane getUpdatePane() {
        BorderPane root = new BorderPane();

        players.getItems().addAll(fetchPlayers());

        VBox playersBox = new VBox();
        playersBox.setMaxHeight(200);
        Label selectPlayerLabel = new Label("Players");
        selectPlayerLabel.setPadding(new Insets(10));
        playersBox.getChildren().addAll(selectPlayerLabel, players);
        root.setLeft(playersBox);

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setMaxWidth(300);

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        TextField addressField = new TextField();
        addressField.setPromptText("Address");

        TextField postalCodeField = new TextField();
        postalCodeField.setPromptText("Postal Code");

        TextField provinceField = new TextField();
        provinceField.setPromptText("Province");

        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Phone Number");

        // populate fields with selected player
        players.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                firstNameField.setText(newVal.getFirstName());
                lastNameField.setText(newVal.getLastName());
                addressField.setText(newVal.getAddress());
                postalCodeField.setText(newVal.getPostalCode());
                provinceField.setText(newVal.getProvince());
                phoneNumberField.setText(newVal.getPhoneNumber());
            }
        });

        Button updateButton = new Button("Update Player");
        updateButton.setOnAction(e -> {
            Player selectedPlayer = players.getSelectionModel().getSelectedItem();
            if (selectedPlayer == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a player");
                alert.showAndWait();
                return;
            }
            updatePlayer(selectedPlayer.getId(), firstNameField.getText(), lastNameField.getText(),
                    addressField.getText(), postalCodeField.getText(), provinceField.getText(),
                    phoneNumberField.getText());
        });

        vbox.getChildren().addAll(new Label("Update Player"), firstNameField, lastNameField, addressField,
                postalCodeField,
                provinceField, phoneNumberField, updateButton);
        root.setCenter(vbox);

        return root;
    }

    public Pane getInsertPane() {
        BorderPane root = new BorderPane();

        ListView<Game> games = new ListView<>();
        games.getItems().addAll(fetchGames());

        VBox gamesBox = new VBox();
        gamesBox.setMaxHeight(200);
        Label selectGameLabel = new Label("Games");
        selectGameLabel.setPadding(new Insets(10));
        gamesBox.getChildren().addAll(selectGameLabel, games);

        root.setLeft(gamesBox);

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setMaxWidth(300);

        // ComboBox for existing players
        ComboBox<Player> playerComboBox = new ComboBox<>();
        playerComboBox.getItems().addAll(fetchPlayers());
        playerComboBox.setPromptText("Select Existing Player");

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        TextField addressField = new TextField();
        addressField.setPromptText("Address");

        TextField postalCodeField = new TextField();
        postalCodeField.setPromptText("Postal Code");

        TextField provinceField = new TextField();
        provinceField.setPromptText("Province");

        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Phone Number");

        TextField playerScoreField = new TextField();
        playerScoreField.setPromptText("Score");

        // populate fields with selected player
        playerComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                firstNameField.setText(newVal.getFirstName());
                lastNameField.setText(newVal.getLastName());
                addressField.setText(newVal.getAddress());
                postalCodeField.setText(newVal.getPostalCode());
                provinceField.setText(newVal.getProvince());
                phoneNumberField.setText(newVal.getPhoneNumber());
            }
        });

        Button addScoreButton = new Button("Add Score");
        addScoreButton.setOnAction(e -> {
            Game selectedGame = games.getSelectionModel().getSelectedItem();
            if (selectedGame == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a game");
                alert.showAndWait();
                return;
            }

            Player selectedPlayer = playerComboBox.getValue();
            if (selectedPlayer != null) {

                // Add a new score for an existing player
                insertPlayerGame(selectedPlayer.getId(), selectedPlayer.getFirstName(), selectedPlayer.getLastName(),
                        selectedPlayer.getAddress(), selectedPlayer.getPostalCode(), selectedPlayer.getProvince(),
                        selectedPlayer.getPhoneNumber(), playerScoreField.getText(), selectedGame.getGameId());
            } else if (!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty()) {
                // Add a new player and score
                insertPlayerGame(0, firstNameField.getText(), lastNameField.getText(), addressField.getText(),
                        postalCodeField.getText(), provinceField.getText(), phoneNumberField.getText(),
                        playerScoreField.getText(), selectedGame.getGameId());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Please select an existing player or fill in all fields for a new player.");
                alert.showAndWait();
            }
        });

        // Layout components
        vbox.getChildren().addAll(
                new Label("Add Score"),
                new Label("Select Existing Player"),
                playerComboBox,
                new Label("Or Add New Player"),
                firstNameField, lastNameField, addressField, postalCodeField, provinceField, phoneNumberField,
                new Label("Score"),
                playerScoreField,
                addScoreButton);

        root.setCenter(vbox);

        return root;
    }

    private void updatePlayer(int playerId, String firstName, String lastName, String address, String postalCode,
            String province,
            String phoneNumber) {
        String updatePlayerSQL = """
                UPDATE carlos_dlc_player
                SET first_name = ?,
                    last_name = ?,
                    address = ?,
                    postal_code = ?,
                    province = ?,
                    phone_number = ?
                WHERE player_id = ?
                """;
        try (Connection conn = DatabaseConnection.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(updatePlayerSQL);) {
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, address);
                statement.setString(4, postalCode);
                statement.setString(5, province);
                statement.setString(6, phoneNumber);
                statement.setInt(7, playerId);
                statement.executeUpdate();

                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Player updated successfully.");
                    players.getItems().clear();
                    players.getItems().addAll(fetchPlayers());
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Player not found.");
                    alert.showAndWait();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.showAndWait();
        }
    }

    private void insertPlayerGame(int playerId, String firstName, String lastName, String address, String postalCode,
            String province,
            String phoneNumber, String score, int gameId) {

        String insertPlayerSQL = "INSERT INTO carlos_dlc_player (player_id, first_name, last_name, address, postal_code, province, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (playerId == 0) {
                playerId = ThreadLocalRandom.current().nextInt(1, 10000 + 1);
                // Insert player
                try (PreparedStatement statement = conn.prepareStatement(insertPlayerSQL);) {
                    statement.setInt(1, playerId);
                    statement.setString(2, firstName);
                    statement.setString(3, lastName);
                    statement.setString(4, address);
                    statement.setString(5, postalCode);
                    statement.setString(6, province);
                    statement.setString(7, phoneNumber);
                    statement.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Player added successfully");
                    alert.showAndWait();
                }
            }

            // Insert player game
            String insertPlayerGameSQL = "INSERT INTO carlos_dlc_player_game (game_id, player_id, playing_date, score) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(insertPlayerGameSQL);) {
                statement.setInt(1, gameId);
                statement.setInt(2, playerId);
                statement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
                statement.setString(4, score);
                statement.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Player game added successfully");
                alert.showAndWait();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.showAndWait();
        }
    }

    private ArrayList<Player> fetchPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM carlos_dlc_player";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
                ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                players.add(new Player(rs.getInt("player_id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("address"), rs.getString("postal_code"), rs.getString("province"),
                        rs.getString("phone_number")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return players;
    }

    private ArrayList<Game> fetchGames() {
        ArrayList<Game> games = new ArrayList<>();
        String sql = "SELECT game_id, game_title FROM carlos_dlc_game";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
                ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                games.add(new Game(rs.getInt("game_id"), rs.getString("game_title")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return games;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
