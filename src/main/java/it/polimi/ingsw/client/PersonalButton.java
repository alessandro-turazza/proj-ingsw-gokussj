package it.polimi.ingsw.client;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class PersonalButton extends Button {

    public  PersonalButton(){
        setStyle("-fx-background-color: #734d26; -fx-background-radius: 15px");
        setTextFill(Color.rgb(204, 153, 102));
        setFont(new Font("Comic Sans MS", 20));
        setBorder(new Border(new BorderStroke(Color.rgb(204, 153, 102),  BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(5))));
    }
}
