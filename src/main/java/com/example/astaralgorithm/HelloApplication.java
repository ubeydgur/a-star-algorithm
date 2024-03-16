package com.example.astaralgorithm;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloApplication extends Application {
    Screen mainScreen = new Screen();

    @Override
    public void start(Stage stage) throws IOException {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            tick();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        stage.setTitle("AStar");
        stage.setScene(mainScreen.getScene());
        stage.show();
    }
    public void tick() {
        mainScreen.search();
    }
    public static void main(String[] args) {
        launch();
    }
}