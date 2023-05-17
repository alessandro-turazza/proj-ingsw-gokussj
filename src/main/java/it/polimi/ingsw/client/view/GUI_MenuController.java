package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.PersonalButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Screen;

import java.io.IOException;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUI_MenuController {

    public static void onCreateGameClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-wait.fxml"));
        Pane root = fxmlLoader.load();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        double resolution = GUI.getResolution();

        Scene scene = new Scene(root, bounds.getWidth()*resolution, bounds.getHeight()*resolution);
        GUI.getStage().setScene(scene);

        BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getBackgroundStart(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);

        root.setBackground(new Background(backgroundImage));

        TextField username = new TextField();
        username.setPromptText("Username");
        username.setMaxSize(0.3*resolution*bounds.getWidth(), 0.3*resolution* bounds.getHeight());

        TextField numPlayers = new TextField();
        numPlayers.setPromptText("Numero Giocatori");
        numPlayers.setMaxSize(0.3*resolution*bounds.getWidth(), 0.3*resolution* bounds.getHeight());

        PersonalButton confirm = new PersonalButton();
        confirm.setText("Conferma");

        root.getChildren().add(username);
        root.getChildren().add(numPlayers);
        root.getChildren().add(confirm);

        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GUI_CreateController.onConfirmCreateClick(username, numPlayers);
            }
        });

        PersonalButton resizeWindow = new PersonalButton();
        resizeWindow.setPrefSize(0.05*resolution*bounds.getHeight(), 0.05*resolution* bounds.getHeight());
        root.getChildren().add(resizeWindow);
        if(GUI.getResolution() == GUI.HALF_SCREEN)
            resizeWindow.setText("↗");
        else resizeWindow.setText("↙");

        resizeWindow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GUI.getStage().setResizable(true);
                GUI_ResizeController.resize();
                try {
                    onCreateGameClick();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                GUI.getStage().setResizable(false);
            }
        });

    }

    public static void onJoinGameClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-join.fxml"));
        Pane root = fxmlLoader.load();

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        double resolution = GUI.getResolution();

        Scene scene = new Scene(root, bounds.getWidth()*resolution, bounds.getHeight()*resolution);
        GUI.getStage().setScene(scene);

        BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getBackgroundStart(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);

        root.setBackground(new Background(backgroundImage));

        TextField username = new TextField();
        username.setPromptText("Username");
        username.setMaxSize(0.3*resolution*bounds.getWidth(), 0.3*resolution* bounds.getHeight());

        TextField idGame = new TextField();
        idGame.setPromptText("Id partita");
        idGame.setMaxSize(0.3*resolution*bounds.getWidth(), 0.3*resolution* bounds.getHeight());

        PersonalButton confirm = new PersonalButton();
        confirm.setText("Conferma");

        root.getChildren().add(username);
        root.getChildren().add(idGame);
        root.getChildren().add(confirm);

        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GUI_JoinController.onConfirmEnterClick(username, idGame);
            }
        });


        PersonalButton resizeWindow = new PersonalButton();
        resizeWindow.setPrefSize(0.05*resolution*bounds.getHeight(), 0.05*resolution* bounds.getHeight());
        root.getChildren().add(resizeWindow);
        if(GUI.getResolution() == GUI.HALF_SCREEN)
            resizeWindow.setText("↗");
        else resizeWindow.setText("↙");


        resizeWindow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GUI.getStage().setResizable(true);
                GUI_ResizeController.resize();
                try {
                    onJoinGameClick();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                GUI.getStage().setResizable(false);
            }
        });

    }

}