package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.PersonalButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUI_MenuController {
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;

    public static void onCreateGameClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-wait.fxml"));
        Pane root = fxmlLoader.load();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        double resolution = GUI.getResolution();

        HBox hBoxResize = new HBox();
        hBoxResize.setAlignment(Pos.TOP_RIGHT);
        hBoxResize.setPrefHeight(resolution*bounds.getHeight()/2);

        HBox hBoxBack = new HBox();
        hBoxBack.setAlignment(Pos.BOTTOM_LEFT);
        hBoxBack.setPrefHeight(resolution*bounds.getHeight()/2);

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

        PersonalButton confirm = new PersonalButton(300.0,70.0);
        confirm.setText("Conferma");
        confirm.animation();

        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Integer n=Integer.parseInt(numPlayers.getText());
                    if(n<MIN_PLAYERS || n>MAX_PLAYERS)throw new Exception();
                    GUI_CreateController.onConfirmCreateClick(username, numPlayers);
                }
                catch (Exception e){
                    numPlayers.setStyle("-fx-text-fill: red");
                }
            }
        });

        PersonalButton resizeWindow = new PersonalButton(70.0,70.0);
        //resizeWindow.setPrefSize(0.05*resolution*bounds.getHeight(), 0.05*resolution* bounds.getHeight());
        hBoxResize.getChildren().add(resizeWindow);
        if(GUI.getResolution() == GUI.HALF_SCREEN)
            resizeWindow.setText("↗");
        else resizeWindow.setText("↙");
        resizeWindow.animation();

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

        PersonalButton back = new PersonalButton(300.0,70.0);
        back.setText("←Indietro");

        back.animation();
        hBoxBack.getChildren().add(back);

        root.getChildren().add(hBoxResize);
        root.getChildren().add(username);
        root.getChildren().add(numPlayers);
        root.getChildren().add(confirm);
        root.getChildren().add(hBoxBack);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    GUI.showStart();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }

    public static void onJoinGameClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-join.fxml"));
        Pane root = fxmlLoader.load();

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        double resolution = GUI.getResolution();

        HBox hBoxResize = new HBox();
        hBoxResize.setAlignment(Pos.TOP_RIGHT);
        hBoxResize.setPrefHeight(resolution*bounds.getHeight()/2);

        HBox hBoxBack = new HBox();
        hBoxBack.setAlignment(Pos.BOTTOM_LEFT);
        hBoxBack.setPrefHeight(resolution*bounds.getHeight()/2);

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

        PersonalButton confirm = new PersonalButton(300.0,70.0);
        confirm.setText("Conferma");
        confirm.animation();

        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Integer.parseInt(idGame.getText());
                    GUI_JoinController.onConfirmEnterClick(username, idGame);
                }
                catch (Exception e){
                    idGame.setStyle("-fx-text-fill: red");
                }

            }
        });


        PersonalButton resizeWindow = new PersonalButton(70.0,70.0);
        //resizeWindow.setPrefSize(0.05*resolution*bounds.getHeight(), 0.05*resolution* bounds.getHeight());
        hBoxResize.getChildren().add(resizeWindow);
        if(GUI.getResolution() == GUI.HALF_SCREEN)
            resizeWindow.setText("↗");
        else resizeWindow.setText("↙");

        resizeWindow.animation();

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

        PersonalButton back = new PersonalButton(300.0,70.0);
        back.setText("←Indietro");

        back.animation();

        hBoxBack.getChildren().add(back);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    GUI.showStart();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        root.getChildren().add(hBoxResize);
        root.getChildren().add(username);
        root.getChildren().add(idGame);
        root.getChildren().add(confirm);
        root.getChildren().add(hBoxBack);

    }

}