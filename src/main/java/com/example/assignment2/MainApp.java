package com.example.assignment2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Country Information");

        TextField countryInput = new TextField();
        countryInput.setPromptText("Enter country name");

        Button fetchButton = new Button("Get Information");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        VBox vbox = new VBox(10, countryInput, fetchButton, resultArea);
        Scene scene = new Scene(vbox, 400, 300);

        fetchButton.setOnAction(e -> {
            String country = countryInput.getText();
            if (!country.isEmpty()) {
                String countryData = fetchCountryData(country);
                resultArea.setText(countryData);
            } else {
                resultArea.setText("Please enter a country name.");
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String fetchCountryData(String country) {
        return CountryService.fetchCountryData(country);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

