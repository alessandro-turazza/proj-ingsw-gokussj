package it.polimi.ingsw.client;

import it.polimi.ingsw.client.view.GUI;
import it.polimi.ingsw.client.view.GUIController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class PersonalButton extends Button {

    public  PersonalButton(Double width,Double height){
        setStyle("-fx-effect: dropshadow( one-pass-box , #332200 , 0 , 0.0 , 6 , 5 ); -fx-background-color: #734d26; -fx-background-radius: 15px");
        setPrefSize(width*GUI.getResolution(),height*GUI.getResolution());
        setTextFill(Color.rgb(204, 153, 102));
        setFont(new Font("Comic Sans MS", 20* GUI.getResolution()));
        setBorder(new Border(new BorderStroke(Color.rgb(204, 153, 102),  BorderStrokeStyle.SOLID, new CornerRadii(10*GUI.getResolution()), new BorderWidths(5*GUI.getResolution()))));
    }
}
