package it.polimi.ingsw.client.view;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class GUI_ResizeController {
    public static void resize(){
        double resolution = GUI.getResolution();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        if(resolution == GUI.FULL_SCREEN){
            GUI.setResolution(GUI.HALF_SCREEN);
            GUI.getStage().setX((bounds.getWidth()/2)*GUI.HALF_SCREEN);
            GUI.getStage().setY((bounds.getHeight()/2)*GUI.HALF_SCREEN);
        }else{
            GUI.setResolution(GUI.FULL_SCREEN);
            GUI.getStage().setX(0);
            GUI.getStage().setY(0);
        }

    }
}
