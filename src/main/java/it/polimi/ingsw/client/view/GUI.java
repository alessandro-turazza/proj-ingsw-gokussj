package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.PersonalButton;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUI extends Application{
    private static final int MAX_CELLS_DROP=3;
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;
    public static final double FULL_SCREEN=1;
    public static final double HALF_SCREEN=0.5;

    private static double resolution;
    //private GUIController controller;
    private static Client client;
    private static Stage stage;
    private static Label waitLabel;
    public static Stage getStage() {
        return stage;
    }
    public static void setResolution(double resolution) {
        GUI.resolution = resolution;
    }

    public static double getResolution(){
        return resolution;
    }

    public static void showStart() throws IOException, ParseException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("startmenu.fxml"));
        Pane root = fxmlLoader.load();

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getBackgroundStart(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);

        root.setBackground(new Background(backgroundImage));
        Scene startScene = new Scene(root,bounds.getWidth()*resolution,bounds.getHeight()*resolution);

        HBox hBox = new HBox();
        HBox hBoxResize = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefHeight(resolution*bounds.getHeight()+20);
        hBoxResize.setAlignment(Pos.TOP_RIGHT);
        hBoxResize.setPrefHeight(resolution*bounds.getHeight()/4);

        PersonalButton create = new PersonalButton(300.0,70.0);
        create.setText("Nuova partita");
        hBox.getChildren().add(create);

        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    GUI_MenuController.onCreateGameClick();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        create.animation();

        PersonalButton join = new PersonalButton(300.0,70.0);
        join.setText("Unisciti a partita");
        hBox.getChildren().add(join);
        join.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    GUI_MenuController.onJoinGameClick();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        join.animation();

        PersonalButton resizeWindow = new PersonalButton(70.0,70.0);
        hBoxResize.getChildren().add(resizeWindow);

        root.getChildren().add(hBoxResize);
        root.getChildren().add(hBox);
        if(GUI.getResolution() == GUI.HALF_SCREEN)
            resizeWindow.setText("↗");
        else resizeWindow.setText("↙");

        resizeWindow.animation();
        resizeWindow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setResizable(true);
                GUI_ResizeController.resize();
                try {
                    showStart();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                stage.setResizable(false);
            }
        });

        stage.setScene(startScene);
    }

    public static void setClient(Client client) {
        GUI.client = client;
    }


    @Override
    public void start(Stage stage) throws Exception {
        resolution=FULL_SCREEN;
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        GUI.stage=stage;
        GUI.stage.setX((bounds.getWidth()/2)*HALF_SCREEN);
        GUI.stage.setY((bounds.getHeight()/2)*HALF_SCREEN);



        stage.setResizable(false);
        stage.setTitle("MyShelfie");
        this.client = GUIController.getClient();
        PicturesLoad.loadImages();
        stage.getIcons().add(PicturesLoad.getIcon());
        showStart();
        stage.show();
    }

    public static Client getClient() {
        return client;
    }

    public static void showOkConnection( int idGame) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-wait.fxml"));
        Pane root = fxmlLoader.load();

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getParquet(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);

        root.setBackground(new Background(backgroundImage));
        Scene scene = new Scene(root, bounds.getWidth()*resolution, bounds.getHeight()*resolution);


        HBox hBoxResize = new HBox();
        hBoxResize.setAlignment(Pos.TOP_RIGHT);
        hBoxResize.setPrefHeight(resolution*bounds.getHeight()/5);
        HBox hBoxLabel = new HBox();
        hBoxLabel.setAlignment(Pos.CENTER);
        hBoxLabel.setPrefHeight(resolution*bounds.getHeight());

        Platform.runLater(()->{
            waitLabel = new Label();
            waitLabel.setAlignment(Pos.CENTER);
            waitLabel.setBackground(new Background(new BackgroundFill(null, CornerRadii.EMPTY, Insets.EMPTY)));
            waitLabel.setPrefSize(bounds.getWidth()*resolution*0.6, bounds.getHeight()*resolution*0.2);
            waitLabel.setText("Sei stato aggiunto correttamente alla partita " + idGame +".\nIn attesa degli altri giocatori...");
            waitLabel.setTextFill(Color.rgb(204, 153, 102));
            waitLabel.setFont(new Font("Comic Sans MS", 30*resolution));
            hBoxLabel.getChildren().add(waitLabel);

            PersonalButton resizeWindow = new PersonalButton(70.0,70.0);
            hBoxResize.getChildren().add(resizeWindow);
            if(GUI.getResolution() == GUI.HALF_SCREEN)
                resizeWindow.setText("↗");
            else resizeWindow.setText("↙");

            root.getChildren().add(hBoxResize);
            root.getChildren().add(hBoxLabel);
            resizeWindow.animation();
            resizeWindow.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    stage.setResizable(true);
                    GUI_ResizeController.resize();
                    try {
                        showOkConnection(idGame);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.setResizable(false);
                }
            });

            stage.setScene(scene);

        });
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

    }

    public static void showKoConnection() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("startmenu.fxml"));
        Pane root = fxmlLoader.load();
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getBackgroundStart(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);
        root.setBackground(new Background(backgroundImage));
    }

    public static void showStateGame(){
        Platform.runLater(()->{
            try {
                GUI_TurnController.showStateGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void showChatMessage(String message){
        Platform.runLater(()->{
            GUI_TurnController.showChatMessage(message);
        });
    }

    public static void showEndGame(){
        Platform.runLater(()->{
            try {
                GUI_EndGameController.showEndGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public static void showErrorMessage(String message){
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(message);
            alert.show();
        });
    }
}
