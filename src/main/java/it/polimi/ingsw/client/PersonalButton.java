package it.polimi.ingsw.client;

import it.polimi.ingsw.client.view.GUI;
import javafx.event.EventHandler;
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
        setStyle("-fx-effect: dropshadow( one-pass-box , #332200 , 0 , 0.0 ,"+ 8*resolution +" ," + 7*resolution +"); -fx-background-color: #734d26; -fx-background-radius: "+ 10*resolution + "px");
        setPrefSize(buttonWidth*resolution,buttonHeight*resolution);
        setTextFill(Color.rgb(204, 153, 102));
        setFont(new Font("Comic Sans MS", 20* GUI.getResolution()));
        setBorder(new Border(new BorderStroke(Color.rgb(204, 153, 102),  BorderStrokeStyle.SOLID, new CornerRadii(10*resolution), new BorderWidths(5*resolution))));
    }

    private void enterAnimation(){
        setStyle("-fx-effect: dropshadow( one-pass-box , #332200 , 0 , 0.0 ,"+ 5*resolution +" ," + 4*resolution +"); -fx-background-color: #4d3319; -fx-background-radius: "+ 10*resolution + "px");
        setPrefSize((buttonWidth-1)*resolution,(buttonHeight-1)*resolution);
        setBorder(new Border(new BorderStroke(Color.rgb(191, 128, 64),  BorderStrokeStyle.SOLID, new CornerRadii(10*resolution), new BorderWidths(4.5*resolution))));
        setTextFill(Color.rgb(191, 128, 64));
    }

    private void exitAnimation(){
        setStyle("-fx-effect: dropshadow( one-pass-box , #332200 , 0 , 0.0 ,"+ 8*resolution +" ," + 7*resolution +"); -fx-background-color: #734d26; -fx-background-radius: "+ 10*resolution + "px");
        setPrefSize(buttonWidth*resolution,buttonHeight*resolution);
        setTextFill(Color.rgb(204, 153, 102));
        setFont(new Font("Comic Sans MS", 20* GUI.getResolution()));
        setBorder(new Border(new BorderStroke(Color.rgb(204, 153, 102),  BorderStrokeStyle.SOLID, new CornerRadii(10*resolution), new BorderWidths(5*resolution))));
    }

    public void animation(){
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                enterAnimation();
            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitAnimation();
            }
        });
    }
}
