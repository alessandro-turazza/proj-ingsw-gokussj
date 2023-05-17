package it.polimi.ingsw.client;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class PersonalButton extends Button {

    public  PersonalButton(){
        setBackground(new Background(new BackgroundFill(Color.rgb(96, 64, 32), CornerRadii.EMPTY, Insets.EMPTY)));
        setTextFill(Color.rgb(204, 153, 102));
        setFont(new Font("Times New Roman", 20));
        setBorder(new Border(new BorderStroke(Color.rgb(179, 119, 0),  BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }
}
