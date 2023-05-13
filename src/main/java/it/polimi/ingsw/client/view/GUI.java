package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUI extends Application{
    private static final int MAX_CELLS_DROP=3;
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;

    private GUIController controller;



    @Override
    public void start(Stage stage) throws Exception {
        controller= (GUIController) Client.getViewController();
        PicturesLoad.loadImages();
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("startmenu.fxml"));
        Pane root = fxmlLoader.load();
        GUI_MenuController ctrl=new GUI_MenuController();
        GUI_MenuController.setStage(stage);
        ctrl.setClient(controller.getClient());


        stage.setTitle("MyShelfie");

        BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getBackgroundStart(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);

        root.setBackground(new Background(backgroundImage));
        Scene startScene = new Scene(root, 1000,600);

        stage.setScene(startScene);
        stage.show();
    }

    public GUIController getController() {
        return controller;
    }
}
