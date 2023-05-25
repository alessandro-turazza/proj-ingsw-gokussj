package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.PersonalButton;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUI extends Application{
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

        create.setOnAction(actionEvent -> {
            try {
                GUI_MenuController.onCreateGameClick();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        create.animation();

        PersonalButton join = new PersonalButton(300.0,70.0);
        join.setText("Unisciti a partita");
        hBox.getChildren().add(join);
        join.setOnAction(actionEvent -> {
            try {
                GUI_MenuController.onJoinGameClick();
            } catch (IOException e) {
                throw new RuntimeException(e);
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
        resizeWindow.setOnAction(actionEvent -> {
            stage.setResizable(true);
            GUI_ResizeController.resize();
            try {
                showStart();
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
            stage.setResizable(false);
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
        stage.setOnCloseRequest(windowEvent -> System.exit(0));


        stage.setResizable(false);
        stage.setTitle("MyShelfie");
        client = GUIController.getClient();
        PicturesLoad.loadImages();
        stage.getIcons().add(PicturesLoad.getIcon());
        //showStart();
        intro();
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
            resizeWindow.setOnAction(actionEvent -> {
                stage.setResizable(true);
                GUI_ResizeController.resize();
                try {
                    showOkConnection(idGame);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setResizable(false);
            });

            stage.setScene(scene);

        });

    }

    public static void showKoConnection() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("startmenu.fxml"));
        Pane root = fxmlLoader.load();
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

    public static void showChatMessage(String namePlayer,String message){
        Platform.runLater(()-> GUI_TurnController.updateChat(namePlayer, message));
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
            try {
                showStart();
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void showLightErrorMessage(String message){
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(message);
            alert.show();
            showStateGame();
        });
    }

    public static void intro() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("startmenu.fxml"));
        Pane root = fxmlLoader.load();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));

        Scene introScene = new Scene(root,bounds.getWidth()*resolution,bounds.getHeight()*resolution);

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        ImageView cranioLogo = new ImageView(PicturesLoad.getCranioLogo());
        cranioLogo.setOpacity(0);
        cranioLogo.setFitHeight(bounds.getHeight()*resolution/3);
        cranioLogo.setFitWidth(bounds.getHeight()*resolution/3);
        FadeTransition transition1 = new FadeTransition();
        transition1.setNode(cranioLogo);
        transition1.setDuration(Duration.millis(2500));
        transition1.setCycleCount(2);
        transition1.setInterpolator(Interpolator.EASE_IN);
        transition1.setFromValue(0);
        transition1.setToValue(1);
        transition1.setAutoReverse(true);
        transition1.setDelay(Duration.millis(1500));
        transition1.play();

        ImageView polimiLogo = new ImageView(PicturesLoad.getPolimiLogo());
        polimiLogo.setFitHeight(bounds.getHeight()*resolution/2);
        polimiLogo.setFitWidth((bounds.getHeight()*resolution/2)*1.3122);
        polimiLogo.setOpacity(0);
        FadeTransition transition2 = new FadeTransition();
        transition2.setNode(polimiLogo);
        transition2.setDuration(Duration.millis(2500));
        transition2.setCycleCount(2);
        transition2.setInterpolator(Interpolator.EASE_IN);
        transition2.setFromValue(0);
        transition2.setToValue(1);
        transition2.setAutoReverse(true);
        transition2.setDelay(new Duration(7000));
        transition2.play();

        Label credit = new Label("A game by:\n\nNicolò Giallongo\n\nAlessandro Turazza\n\nGiuseppe Vitello");
        credit.setTextFill(Color.rgb(255,255,255));
        credit.setFont(new Font("Comic Sans MS", 30* resolution));
        credit.setOpacity(0);
        FadeTransition transition3 = new FadeTransition();
        transition3.setNode(credit);
        transition3.setDuration(Duration.millis(2500));
        transition3.setCycleCount(2);
        transition3.setInterpolator(Interpolator.EASE_IN);
        transition3.setFromValue(0);
        transition3.setToValue(1);
        transition3.setAutoReverse(true);
        transition3.setDelay(new Duration(12500));
        transition3.play();

        ImageView title = new ImageView(PicturesLoad.getTitle());
        title.setFitHeight(bounds.getWidth()*resolution*0.309);
        title.setFitWidth((bounds.getWidth()*resolution));
        title.setOpacity(0);
        FadeTransition transition4 = new FadeTransition();
        transition4.setNode(title);
        transition4.setDuration(Duration.millis(1500));
        transition4.setCycleCount(1);
        transition4.setInterpolator(Interpolator.EASE_IN);
        transition4.setFromValue(0);
        transition4.setToValue(1);
        transition4.setAutoReverse(false);
        transition4.setDelay(new Duration(18000));
        transition4.play();

        Label command = new Label("Premi Invio per iniziare");
        command.setOpacity(0);
        command.setTextFill(Color.rgb(255,255,255));
        command.setFont(new Font("Comic Sans MS", 30* resolution));
        FadeTransition transition5 = new FadeTransition();
        transition5.setNode(command);
        transition5.setDuration(Duration.millis(1500));
        transition5.setCycleCount(Animation.INDEFINITE);
        transition5.setInterpolator(Interpolator.EASE_IN);
        transition5.setFromValue(0);
        transition5.setToValue(1);
        transition5.setAutoReverse(true);
        transition5.setDelay(Duration.millis(18000));
        transition5.play();
        vBox.getChildren().add(title);
        vBox.getChildren().add(command);
        stackPane.getChildren().add(cranioLogo);
        stackPane.getChildren().add(polimiLogo);
        stackPane.getChildren().add(credit);
        stackPane.getChildren().add(vBox);
        root.getChildren().add(stackPane);

        introScene.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()== KeyCode.ENTER){
                try {
                    showStart();
                } catch (IOException | ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        stage.setScene(introScene);

    }
}
