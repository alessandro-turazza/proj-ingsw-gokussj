package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientMessager;
import it.polimi.ingsw.client.PersonalButton;
import it.polimi.ingsw.client.chat.Chat;
import it.polimi.ingsw.server.model.user.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

import static it.polimi.ingsw.client.view.GUI_BookshelfController.makeBookshelf;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUI_EndGameController {
    public static void showEndGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-endgame.fxml"));
        Pane root = fxmlLoader.load();
        Stage stage = GUI.getStage();

        double resolution = GUI.getResolution();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        Scene scene = new Scene(root, bounds.getWidth()*resolution, bounds.getHeight()*resolution);
        BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getEndBackground(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);
        root.setBackground(new Background(backgroundImage));
        //HBox container=new HBox();
        //container.setSpacing(10*resolution);
        //VBox vBoxButton=new VBox();
        //vBoxButton.setAlignment(Pos.BOTTOM_LEFT);
        //vBoxButton.setPrefSize(300*resolution,bounds.getHeight()*resolution);
        VBox ranking = new VBox();
        //vBoxButton.setPrefSize((bounds.getWidth()-300)*resolution,bounds.getHeight()*resolution);
        //container.getChildren().add(vBoxButton);
        ///container.getChildren().add(ranking);
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
            rank.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        onEndBookshelfClick(user);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            ranking.getChildren().add(rank);
        }
        PersonalButton back=new PersonalButton(300.0,70.0);
        back.animation();
        back.setText("Menù Iniziale");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Client client = GUI.getClient();
                    client.setChat(new Chat());
                    client.setMessager(new ClientMessager(client));
                    client.startClient('G');
                    GUI.showStart();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        ranking.getChildren().add(back);
        ranking.setPadding(new Insets(0,0,25*resolution,0));

        root.getChildren().add(ranking);
        stage.setScene(scene);
    }

    public static void onEndBookshelfClick(User user) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-bookshelf.fxml"));
        Pane root = fxmlLoader.load();
        double resolution = GUI.getResolution();


        BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getBookshelfbackgroundblury(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);
        root.setBackground(new Background(backgroundImage));

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Scene scene = new Scene(root, bounds.getWidth()*resolution, bounds.getHeight()*resolution);
        Stage stage = GUI.getStage();
        stage.setScene(scene);
        root.setPrefSize(bounds.getWidth()*resolution, bounds.getHeight()*resolution);

        StackPane bookshelfPane = makeBookshelf(user);
        HBox bookshelfBox = new HBox();

        PersonalButton back = new PersonalButton(300.0, 70.0);
        back.setText("←Indietro");

        back.animation();
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    showEndGame();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        PersonalButton resizeWindow = new PersonalButton(70.0, 70.0);
        if(GUI.getResolution() == GUI.HALF_SCREEN)
            resizeWindow.setText("↗");
        else resizeWindow.setText("↙");

        resizeWindow.animation();

        resizeWindow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GUI.getStage().setResizable(true);
                GUI_ResizeController.resize();
                try {
                    onEndBookshelfClick(user);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                GUI.getStage().setResizable(false);
            }
        });
        ImageView personalGoal = new ImageView(PicturesLoad.getPersonalGoalCardsImgs().get(user.getPersonalGoal().getId()-1));
        personalGoal.setFitWidth(resolution*137*2.2);
        personalGoal.setFitHeight(resolution*200*2.2);

        ImageView commonGoal1 = new ImageView(PicturesLoad.getCommonGoalsImgs().get(GUI.getClient().getModel().getCommonGoals().get(0).getId()-1));
        commonGoal1.setFitWidth(resolution*138*2.5);
        commonGoal1.setFitHeight(resolution*91*2.5);

        ImageView commonGoal2 = new ImageView(PicturesLoad.getCommonGoalsImgs().get(GUI.getClient().getModel().getCommonGoals().get(1).getId()-1));
        commonGoal2.setFitWidth(resolution*138*2.5);
        commonGoal2.setFitHeight(resolution*91*2.5);

        HBox resizeButtonBox = new HBox(resizeWindow);
        HBox backButtonBox = new HBox(back);

        VBox leftVBox = new VBox();
        leftVBox.setAlignment(Pos.BOTTOM_CENTER);
        leftVBox.setSpacing(20*resolution);
        leftVBox.setPadding(new Insets(0,0,25*resolution,0));

        VBox rightVBox = new VBox();
        rightVBox.setAlignment(Pos.TOP_CENTER);
        rightVBox.setSpacing(10*resolution);

        rightVBox.getChildren().add(resizeButtonBox);
            rightVBox.getChildren().add(commonGoal1);
            rightVBox.getChildren().add(commonGoal2);
            leftVBox.getChildren().add(personalGoal);
        leftVBox.getChildren().add(backButtonBox);

        resizeButtonBox.setAlignment(Pos.TOP_RIGHT);
        backButtonBox.setAlignment(Pos.BOTTOM_LEFT);
        leftVBox.setPrefWidth((bounds.getWidth()-bounds.getHeight())*resolution/2);
        rightVBox.setPrefWidth((bounds.getWidth()-bounds.getHeight())*resolution/2);

        bookshelfBox.getChildren().add(leftVBox);
        bookshelfBox.getChildren().add(bookshelfPane);
        bookshelfBox.getChildren().add(rightVBox);
        root.getChildren().add(bookshelfBox);


    }
}
