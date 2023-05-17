package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
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
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);

        Button create = new Button();
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

        Button join = new Button();
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


        root.getChildren().add(hBox);

        Button resizeWindow = new Button();
        resizeWindow.setPrefSize(0.05*resolution*bounds.getHeight(), 0.05*resolution* bounds.getHeight());
        root.getChildren().add(resizeWindow);

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


    @Override
    public void start(Stage stage) throws Exception {
        resolution=HALF_SCREEN;
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        GUI.stage=stage;
        GUI.stage.setX((bounds.getWidth()/2)*HALF_SCREEN);
        GUI.stage.setY((bounds.getHeight()/2)*HALF_SCREEN);

        stage.setResizable(false);
        stage.setTitle("MyShelfie");
        this.client = GUIController.getClient();
        PicturesLoad.loadImages();
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

        Scene scene = new Scene(root, bounds.getWidth()*resolution, bounds.getHeight()*resolution);
        Platform.runLater(()->{
            waitLabel = new Label();
            waitLabel.setAlignment(Pos.CENTER);
            waitLabel.setBackground(new Background(new BackgroundFill(Color.rgb(200,200,200), CornerRadii.EMPTY, Insets.EMPTY)));
            waitLabel.setPrefSize(bounds.getWidth()*resolution*0.6, bounds.getHeight()*resolution*0.2);
            waitLabel.setText("Sei stato aggiunto correttamente alla partita " + idGame +".\nIn attesa deli altri giocatori...");
            waitLabel.setTextFill(Color.rgb(0,0,255));
            root.getChildren().add(waitLabel);

            Button resizeWindow = new Button();
            resizeWindow.setPrefSize(0.05*resolution*bounds.getHeight(), 0.05*resolution* bounds.getHeight());
            root.getChildren().add(resizeWindow);

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

    }

    public static void showKoConnection() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("startmenu.fxml"));
        Pane root = fxmlLoader.load();
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getBackgroundStart(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);
        root.setBackground(new Background(backgroundImage));

        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Impossibile connettersi alla partita.\nProva a cambiare username o id partita.");
            try {
                showStart();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            alert.show();
        });
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

}
