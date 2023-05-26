package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientMessager;
import it.polimi.ingsw.client.PersonalButton;
import it.polimi.ingsw.client.chat.Chat;
import it.polimi.ingsw.server.model.user.User;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUI_EndGameController { //setup and manage the endgame scene
    public static void showEndGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene.fxml"));
        Pane root = fxmlLoader.load();
        Stage stage = GUI.getStage();

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

        for(int i = 0; i < users.size(); i++){
            User user = users.get(i);
            HBox rank = new HBox();
            rank.setMaxSize(650*resolution, 170*resolution);
            rank.setSpacing(20*resolution);
            rank.setAlignment(Pos.CENTER_LEFT);
            rank.setPadding(new Insets(20*resolution));
            rank.setStyle("-fx-effect: dropshadow( one-pass-box , #332200 , 0 , 0.0 ,"+ 8*resolution +" ," + 7*resolution +"); -fx-background-color: #734d26; -fx-background-radius: "+ 10*resolution + "px");
            rank.setBorder(new Border(new BorderStroke(Color.rgb(204, 153, 102),  BorderStrokeStyle.SOLID, new CornerRadii(10*resolution), new BorderWidths(5*resolution))));


            ImageView img = new ImageView(PicturesLoad.getPlace(i+1));
            img.setFitWidth(84*resolution);
            img.setFitHeight(125*resolution);

            if(i == 3)
                img.setFitHeight(84*resolution);

            rank.getChildren().add(img);

            double sizeText = 30;

            if(user.getName().length() >= 10)
                sizeText = 22;

            Label username = new Label(user.getName());
            username.setTextFill(Color.rgb(204, 153, 102));
            username.setFont(new Font("Comic Sans MS", resolution*sizeText));
            rank.getChildren().add(username);


            Label points = new Label("Punti: " + user.getPoints());
            points.setTextFill(Color.rgb(204, 153, 102));
            points.setFont(new Font("Comic Sans MS", resolution*sizeText));
            rank.getChildren().add(points);
            rank.setOnMouseClicked(mouseEvent -> {
                try {
                    onEndBookshelfClick(user);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            ranking.getChildren().add(rank);
        }
        PersonalButton back=new PersonalButton(300.0,70.0);
        back.animation();
        back.setText("Menù Iniziale");
        back.setOnAction(actionEvent -> {
            try {
                Client client = GUI.getClient();
                client.setChat(new Chat());
                client.setMessager(new ClientMessager(client));
                client.startClient('G');
                GUI.showStart();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        ranking.getChildren().add(back);
        ranking.setPadding(new Insets(0,0,25*resolution,0));

        root.getChildren().add(ranking);
        stage.setScene(scene);

        PersonalButton resizeWindow = new PersonalButton(70.0, 70.0);
        if(GUI.getResolution() == GUI.HALF_SCREEN)
            resizeWindow.setText("↗");
        else resizeWindow.setText("↙");

        resizeWindow.animation();

        resizeWindow.setOnAction(actionEvent -> {
            GUI.getStage().setResizable(true);
            GUI_ResizeController.resize();
            try {
                    GUI_EndGameController.showEndGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            GUI.getStage().setResizable(false);
        });
    }

    public static void onEndBookshelfClick(User user) throws IOException {
        GUI_BookshelfController.showBookshelf(user, true);
    }
}
