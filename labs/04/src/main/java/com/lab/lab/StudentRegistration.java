package com.lab.lab;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentRegistration extends Application {
    private TextField name, address, city, province, postalCode, phone, email;
    private RadioButton compSciBtn, businessBtn;
    private ComboBox<String> courseComboBox;
    private ListView<String> courseList;
    private TextArea text;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Student Registration Form");

        // Layouts
        BorderPane mainLayout = new BorderPane();

        // Panes
        mainLayout.setLeft(createLeftPane());
        mainLayout.setCenter(createCenterPane());
        mainLayout.setRight(createRightPane());

        // Display Button and Text Area for Output
        Button displayButton = new Button("Display");
        displayButton.setOnAction(e -> displayInformation());

        text = new TextArea();

        VBox bottomPane = new VBox(10, displayButton, text);
        bottomPane.setPadding(new Insets(10));

        mainLayout.setBottom(bottomPane);

        // Scene
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }

    private void displayInformation() {
        // INFO: Display information in text area
        System.out.println("Displaying information...");

        StringBuilder sb = new StringBuilder();
        sb.append(name.getText()).append(", ");
        sb.append(address.getText()).append(", ");
        sb.append(city.getText()).append(", ");
        sb.append(province.getText()).append(", ");
        sb.append(postalCode.getText()).append(", ");
        sb.append(phone.getText()).append(", ");
        sb.append(email.getText());

        sb.append("\n");
        if (compSciBtn.isSelected()) {
            sb.append("Major: Computer Science\n");
        } else if (businessBtn.isSelected()) {
            sb.append("Major: Business\n");
        }

        sb.append("Courses\n");
        for (String course : courseList.getItems()) {
            sb.append(course).append("\n");
        }

        text.setText(sb.toString());
    }

    private GridPane createLeftPane() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);

        Label nameLabel = new Label("Name");
        name = new TextField();

        Label addressLabel = new Label("Address");
        address = new TextField();

        Label provinceLabel = new Label("Province");
        province = new TextField();

        Label cityLabel = new Label("City");
        city = new TextField();

        Label postalCodeLabel = new Label("Postal Code");
        postalCode = new TextField();

        Label phoneLabel = new Label("Phone");
        phone = new TextField();

        Label emailLabel = new Label("Email");
        email = new TextField();

        pane.add(nameLabel, 0, 0);
        pane.add(name, 1, 0);
        pane.add(addressLabel, 0, 1);
        pane.add(address, 1, 1);
        pane.add(provinceLabel, 0, 2);
        pane.add(province, 1, 2);
        pane.add(cityLabel, 0, 3);
        pane.add(city, 1, 3);
        pane.add(postalCodeLabel, 0, 4);
        pane.add(postalCode, 1, 4);
        pane.add(phoneLabel, 0, 5);
        pane.add(phone, 1, 5);
        pane.add(emailLabel, 0, 6);
        pane.add(email, 1, 6);

        return pane;
    }

    private GridPane createCenterPane() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setHgap(10);
        pane.setVgap(120);
        pane.setAlignment(javafx.geometry.Pos.CENTER);

        CheckBox studentCouncilField = new CheckBox();
        Label studentCouncilLabel = new Label("Student Council");

        CheckBox volunteerWorkField = new CheckBox();
        Label volunteerWorkLabel = new Label("Volunteer Work");

        pane.add(studentCouncilLabel, 1, 0);
        pane.add(studentCouncilField, 0, 0);

        pane.add(volunteerWorkLabel, 1, 1);
        pane.add(volunteerWorkField, 0, 1);

        return pane;
    }

    private GridPane createRightPane() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);

        HBox radioBox = new HBox(10);
        compSciBtn = new RadioButton("Computer Science");
        businessBtn = new RadioButton("Business");

        ToggleGroup group = new ToggleGroup();
        compSciBtn.setToggleGroup(group);
        businessBtn.setToggleGroup(group);

        radioBox.getChildren().addAll(compSciBtn, businessBtn);

        // INFO: Create a dropdown and list for course management
        courseComboBox = new ComboBox<String>();
        courseComboBox.minWidthProperty().set(300);
        courseList = new ListView<String>();
        courseList.maxHeightProperty().set(100);

        // INFO: Add dropdown selection for computer science courseComboBox
        compSciBtn.setOnAction(e -> {
            courseComboBox.getItems().clear();
            courseComboBox.getItems().addAll("Java", "Python", "C++", "C#");
            courseList.getItems().clear();
        });

        // INFO: Add dropdown selection for business courseComboBox
        businessBtn.setOnAction(e -> {
            courseComboBox.getItems().clear();
            courseComboBox.getItems().addAll("Accounting", "Finance", "Marketing", "Management");
            courseList.getItems().clear();
        });

        // INFO: Add item to list
        courseComboBox.setOnAction(e -> {
            if (!courseList.getItems().contains(courseComboBox.getValue())) {
                courseList.getItems().add(courseComboBox.getValue());
            }
        });

        // INFO: Remove item from list
        courseList.setOnMouseClicked(e -> {
            courseList.getItems().remove(courseList.getSelectionModel().getSelectedItem());
        });

        pane.add(radioBox, 1, 0);
        pane.add(courseComboBox, 1, 2);
        pane.add(courseList, 1, 3);

        return pane;
    }

    public static void main(String[] args) {
        launch();
    }
}
