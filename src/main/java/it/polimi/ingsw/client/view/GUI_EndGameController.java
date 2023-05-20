package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.PersonalButton;
import it.polimi.ingsw.server.model.user.User;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUI_EndGameController {
    public static void showEndGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-endgame.fxml"));
        Pane root = fxmlLoader.load();
        double resolution = GUI.getResolution();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        Scene scene = new Scene(root, bounds.getWidth()*resolution, bounds.getHeight()*resolution);
        BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getEndBackground(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);
        root.setBackground(new Background(backgroundImage));

        VBox ranking = new VBox();
        ranking.setAlignment(Pos.CENTER);
        ranking.setSpacing(20*resolution);

        ArrayList<User> users = GUI.getClient().getModel().getPlayers();

        for(int i = 0; i < users.size() - 1; i++){
            for(int j = i+1; j < users.size(); j++){
                if(users.get(j).getPoints() > users.get(i).getPoints()){
                    User temp = users.get(i).getUserClone();
                    users.set(i,users.get(j));
                    users.set(j,temp);
                }
            }
        }

        for(User user: users){
            PersonalButton button = new PersonalButton(350*resolution, 170*resolution);
            button.setText(user.getName() + " " + user.getPoints());
            ImageView img = new ImageView(PicturesLoad.getBookshelfImg());
            button.setGraphic(img);
            ranking.getChildren().add(button);
        }

        root.getChildren().add(ranking);

    }
}
