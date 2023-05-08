package it.polimi.ingsw;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraphicsProva extends Application {

    ///home/alessandro/.jdks/openjdk-19.0.2
    @Override
    public void start(Stage stage) throws Exception {
        VBox vb = new VBox(20);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(20, 20, 20,20));

        Label l1 = new Label("OK");
        Label l2 = new Label("CIAO");

        vb.getChildren().add(l1);
        vb.getChildren().add(l2);

        Scene scene = new Scene(vb, 320, 240);
        stage.setTitle("Server");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args){
        launch();
    }
}
