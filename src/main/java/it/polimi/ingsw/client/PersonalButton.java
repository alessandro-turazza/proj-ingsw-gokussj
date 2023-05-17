package it.polimi.ingsw.client;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class PersonalButton extends Button {

    public  PersonalButton(){
        setBackground(new Background(new BackgroundFill(Color.rgb(96, 64, 32), CornerRadii.EMPTY, Insets.EMPTY)));
        setTextFill(Color.rgb(204, 153, 102));
        setFont(new Font("Times New Roman", 20));
    }
}
