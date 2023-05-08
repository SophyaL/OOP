package ru.nsu.fit.lylova.javafxsnake.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ru.nsu.fit.lylova.javafxsnake.SnakeApplication;
import ru.nsu.fit.lylova.model.Direction;

import java.io.IOException;

public class StartScreenController {

    private SnakeFieldScreenController fieldController;

    public void switchToGameField(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(
                SnakeApplication.class.getResource("screens/snake_field.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        fieldController = fxmlLoader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(new KeyHandler());

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchToSettingsScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                SnakeApplication.class.getResource("screens/settings_screen.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private class KeyHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case UP:
                case W:
                    fieldController.setSnakeDirection(Direction.UP);
                    break;
                case RIGHT:
                case D:
                    fieldController.setSnakeDirection(Direction.RIGHT);
                    break;
                case DOWN:
                case S:
                    fieldController.setSnakeDirection(Direction.DOWN);
                    break;
                case LEFT:
                case A:
                    fieldController.setSnakeDirection(Direction.LEFT);
                    break;
                case ENTER:
                case SPACE:
                    fieldController.startGame();
                    break;
                case ESCAPE:
                    fieldController.stopGame();
            }
        }
    }
}
