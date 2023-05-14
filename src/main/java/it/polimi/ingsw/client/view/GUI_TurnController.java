package it.polimi.ingsw.client.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GUI_TurnController {

    public static Scene showStateGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-game.fxml"));
        Pane root = fxmlLoader.load();
        Scene scene = new Scene(root, 1000, 600);
        ImageView imageView = new ImageView(PicturesLoad.getPlankImg());
        imageView.setFitHeight(600);
        imageView.setFitWidth(600);
        imageView.setX(0);
        imageView.setY(0);
        root.getChildren().add(imageView);

        return scene;
    }

}
