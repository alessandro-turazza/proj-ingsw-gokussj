package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.PersonalButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI_TurnController {

    public static void showStateGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-game.fxml"));
        Pane root = fxmlLoader.load();
        double resolution = GUI.getResolution();

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Scene scene = new Scene(root, bounds.getWidth()*resolution, bounds.getHeight()*resolution);
        Stage stage = GUI.getStage();
        stage.setScene(scene);
        root.setPrefSize(bounds.getWidth()*resolution, bounds.getHeight()*resolution);

        HBox hBox = new HBox();
        root.getChildren().add(hBox);

        Label l1 = new Label();
        l1.setPrefSize(((bounds.getWidth()-bounds.getHeight())/2)*resolution,bounds.getHeight()*resolution);

        hBox.getChildren().add(l1);

        ImageView imageView = new ImageView(PicturesLoad.getPlankImg());
        imageView.setY(0);
        imageView.setX((bounds.getWidth()/2)*resolution);
        imageView.setFitHeight(bounds.getHeight()*resolution);
        imageView.setFitWidth(bounds.getHeight()*resolution);

        hBox.getChildren().add(imageView);

        Pane l2 = new Pane();
        l2.setPrefSize(((bounds.getWidth()-bounds.getHeight())/2)*resolution,bounds.getHeight()*resolution);

        hBox.getChildren().add(l2);

        PersonalButton resizeWindow = new PersonalButton();
        resizeWindow.setPrefSize(0.05*resolution*bounds.getHeight(), 0.05*resolution* bounds.getHeight());
        l2.getChildren().add(resizeWindow);

        resizeWindow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setResizable(true);
                GUI_ResizeController.resize();
                try {
                    showStateGame();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setResizable(false);
            }
        });

    }

}
