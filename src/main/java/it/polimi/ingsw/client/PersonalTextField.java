package it.polimi.ingsw.client;

import it.polimi.ingsw.client.view.GUI;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PersonalTextField extends TextField {
    private double resolution;
    private double buttonWidth;
    private double buttonHeight;

    public  PersonalTextField(Double width,Double height){

        buttonWidth = width;
        buttonHeight = height;
        resolution = GUI.getResolution();
        setStyle("-fx-text-fill: rgb(204, 153, 102); -fx-background-color: #332200FF; -fx-background-radius: "+ 10*resolution + "px");
        setMaxSize(buttonWidth*resolution,buttonHeight*resolution);
        setFont(new Font("Comic Sans MS", 20* GUI.getResolution()));
        setBorder(new Border(new BorderStroke(Color.rgb(204, 153, 102),  BorderStrokeStyle.SOLID, new CornerRadii(10*resolution), new BorderWidths(2.5*resolution))));
    }

    public void errorTextField(){
        setStyle("-fx-text-fill: red; -fx-background-color: #332200FF; -fx-background-radius: "+ 10*resolution + "px");
        setMaxSize(buttonWidth*resolution,buttonHeight*resolution);
        setFont(new Font("Comic Sans MS", 20* GUI.getResolution()));
        setBorder(new Border(new BorderStroke(Color.rgb(255, 0, 0),  BorderStrokeStyle.SOLID, new CornerRadii(10*resolution), new BorderWidths(2.5*resolution))));
    }


}
