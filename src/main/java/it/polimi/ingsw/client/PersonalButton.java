package it.polimi.ingsw.client;

import it.polimi.ingsw.client.view.GUI;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PersonalButton extends Button {

    private double resolution;
    private double buttonWidth;
    private double buttonHeight;

    public  PersonalButton(Double width,Double height){
        buttonWidth = width;
        buttonHeight = height;
        resolution = GUI.getResolution();
        setStyle("-fx-effect: dropshadow( one-pass-box , #332200 , 0 , 0.0 ,"+ 8*resolution +" ," + 7*resolution +")");
        setBackground(new Background(new BackgroundFill(Color.rgb(115,77,38),new CornerRadii(10*resolution),new Insets(2*resolution))));
        setPrefSize(buttonWidth*resolution,buttonHeight*resolution);
        setTextFill(Color.rgb(204, 153, 102));
        setFont(new Font("Comic Sans MS", 20* GUI.getResolution()));
        setBorder(new Border(new BorderStroke(Color.rgb(204, 153, 102),  BorderStrokeStyle.SOLID, new CornerRadii(10*resolution), new BorderWidths(5*resolution))));
    }

    private void enterAnimation(){ //provide the animation when the mouse enter in the button
        setStyle("-fx-effect: dropshadow( one-pass-box , #332200 , 0 , 0.0 ,"+ 5*resolution +" ," + 4*resolution +")");
        setBackground(new Background(new BackgroundFill(Color.rgb(77,51,25),new CornerRadii(10*resolution),new Insets(2*resolution))));
        setPrefSize((buttonWidth-1)*resolution,(buttonHeight-1)*resolution);
        setBorder(new Border(new BorderStroke(Color.rgb(191, 128, 64),  BorderStrokeStyle.SOLID, new CornerRadii(10*resolution), new BorderWidths(4.5*resolution))));
        setTextFill(Color.rgb(191, 128, 64));
    }

    private void exitAnimation(){ //provide the animation when the mouse exit in the button
        setStyle("-fx-effect: dropshadow( one-pass-box , #332200 , 0 , 0.0 ,"+ 8*resolution +" ," + 7*resolution +")");
        setBackground(new Background(new BackgroundFill(Color.rgb(115,77,38),new CornerRadii(10*resolution),new Insets(2*resolution))));        setPrefSize(buttonWidth*resolution,buttonHeight*resolution);
        setTextFill(Color.rgb(204, 153, 102));
        setFont(new Font("Comic Sans MS", 20* GUI.getResolution()));
        setBorder(new Border(new BorderStroke(Color.rgb(204, 153, 102),  BorderStrokeStyle.SOLID, new CornerRadii(10*resolution), new BorderWidths(5*resolution))));
    }

    public void animation(){//set the animations
        this.setOnMouseEntered(mouseEvent -> enterAnimation());

        this.setOnMouseExited(mouseEvent -> exitAnimation());
    }
}
