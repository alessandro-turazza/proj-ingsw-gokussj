package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.PersonalButton;
import it.polimi.ingsw.server.model.user.User;
import it.polimi.ingsw.server.model.user.bookshelf.CellShelf;
import it.polimi.ingsw.server.model.user.personal_goal.Costraints;
import it.polimi.ingsw.server.state_game.CommonGoalClone;
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

/**
 * This class setup and manage the bookshelf scene
 */
public class GUI_BookshelfController { //

    public static void fillBookshelf(User user, StackPane pane, Rectangle2D bounds, boolean isColumn){
        double resolution = GUI.getResolution();
        pane.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(0,0,(bounds.getHeight()/17.5)*resolution,0));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(bounds.getHeight()*0.021*resolution);//19.5
        CellShelf[][] cells = user.getBookshelf().getBookshelf();
        ImageView[][] objectCards=new ImageView[user.getBookshelf().getNumRow()][user.getBookshelf().getNumColumn()];
        for(int row = 0; row<=user.getBookshelf().getNumRow()-1; row++){
            HBox rowbox = new HBox();
            rowbox.setAlignment(Pos.CENTER);
            rowbox.setSpacing(bounds.getWidth()*0.025*GUI.getResolution());//39
            for(int column = 0; column<=user.getBookshelf().getNumColumn()-1; column++){
                objectCards[row][column] = new ImageView();
                objectCards[row][column].setFitHeight((bounds.getHeight()*resolution/8)*0.94);
                objectCards[row][column].setFitWidth((bounds.getHeight()*resolution/8)*0.94);
                if(cells[row][column]!=null && cells[row][column].getObjectCard()!=null){
                    objectCards[row][column].setImage(PicturesLoad.getObjectCardImg(cells[row][column].getObjectCard().getColor(),cells[row][column].getObjectCard().getId()).getCardImg());
                }
                rowbox.getChildren().add(objectCards[row][column]);
            }

            vBox.getChildren().add(rowbox);
        }
        if(isColumn){
            ArrayList<Costraints> personalGoals=GUI.getClient().getModel().getUserByName(GUI.getClient().getModel().getMyName()).getPersonalGoal().getCostraints();
            for(Costraints personalGoal:personalGoals){
                if(objectCards[personalGoal.getRow()][personalGoal.getColumn()].getImage()==null){
                    objectCards[personalGoal.getRow()][personalGoal.getColumn()].setImage(PicturesLoad.getPersonalGoalObjectCard(personalGoal.getColor()));
                    objectCards[personalGoal.getRow()][personalGoal.getColumn()].setOpacity(0.7);
                }
            }
        }
        pane.getChildren().add(vBox);
    }

    public static void onBookshelfClick(User user) throws IOException {
        showBookshelf(user, false);
    }

    public static StackPane makeBookshelf(User user, boolean isColumn){
        StackPane stackPane = new StackPane();
        double resolution = GUI.getResolution();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stackPane.setPrefSize(bounds.getHeight()*resolution,bounds.getHeight()*resolution);

        ImageView bookshelf = new ImageView(PicturesLoad.getBookshelfImg());
        bookshelf.setFitWidth(bounds.getHeight()*resolution);
        bookshelf.setFitHeight(bounds.getHeight()*resolution);
        stackPane.getChildren().add(bookshelf);
        fillBookshelf(user, stackPane, bounds, isColumn);
        return stackPane;
    }
    public static void showBookshelf(User user, boolean endgame) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene.fxml"));
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

        StackPane bookshelfPane = GUI_BookshelfController.makeBookshelf(user, false);
        HBox bookshelfBox = new HBox();

        PersonalButton back = new PersonalButton(300.0, 70.0);
        back.setText("←Indietro");


        back.animation();
        back.setOnAction(actionEvent -> {
            try {
                if(!endgame)
                    GUI_TurnController.showStateGame();
                else GUI_EndGameController.showEndGame();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        PersonalButton resizeWindow = new PersonalButton(70.0, 70.0);
        if(GUI.getResolution() == GUI.HALF_SCREEN)
            resizeWindow.setText("↗");
        else resizeWindow.setText("↙");

        resizeWindow.animation();

        resizeWindow.setOnAction(actionEvent -> {
            GUI.getStage().setResizable(true);
            GUI_ResizeController.resize();
            try {
                if (!endgame)
                    GUI_BookshelfController.onBookshelfClick(user);
                else GUI_EndGameController.onEndBookshelfClick(user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            GUI.getStage().setResizable(false);
        });

        ImageView personalGoal = new ImageView(PicturesLoad.getPersonalGoalCardsImgs().get(user.getPersonalGoal().getId()-1));
        personalGoal.setFitWidth(((bounds.getWidth()-bounds.getHeight())/2)*resolution*0.90);
        personalGoal.setFitHeight(0.6*(bounds.getHeight()*resolution)*0.90);
        VBox personalGoalBox = new VBox();
        personalGoalBox.setPrefSize(((bounds.getWidth()-bounds.getHeight())/2)*resolution,bounds.getHeight()*resolution*0.6);
        Label pgLabel = new Label("Obiettivo personale");

        VBox pointBox = new VBox();
        pointBox.setAlignment(Pos.TOP_LEFT);
        BackgroundImage backgroundParquet = new BackgroundImage(PicturesLoad.getParquet(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);
        pointBox.setBackground(new Background(backgroundParquet));
        pointBox.setBorder(new Border(new BorderStroke(Color.rgb(204, 153, 102),  BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2*resolution))));
        pointBox.setPrefSize(((bounds.getWidth()-bounds.getHeight())/2)*resolution,bounds.getHeight()*resolution*0.2);

        HBox playerToken = new HBox();
        for (Integer point : user.getPointsToken()){
            ImageView token = new ImageView();
            if(point > 0){
                token.setImage(PicturesLoad.getToken(point));
                token.setFitWidth(80*resolution);
                token.setFitHeight(80*resolution);
                playerToken.getChildren().add(token);
            }
        }

        Label points = new Label("Punti giocatore: " + user.getPoints());
        points.setPrefSize(bounds.getWidth()*resolution*0.15, bounds.getHeight()*resolution*0.1);
        points.setAlignment(Pos.CENTER);
        points.setFont(new Font(20*resolution));
        points.setTextFill(Color.rgb(204, 153, 102));

        pointBox.getChildren().add(points);
        pointBox.getChildren().add(playerToken);

        VBox commonGoalBox = new VBox();
        commonGoalBox.setPrefWidth(((bounds.getWidth()-bounds.getHeight())/2)*resolution);
        commonGoalBox.setBackground(new Background(backgroundParquet));
        commonGoalBox.setBorder(new Border(new BorderStroke(Color.rgb(204, 153, 102),  BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10*resolution))));
        commonGoalBox.setSpacing(20*resolution);

        commonGoalBox.setPadding(new Insets(15*resolution,5*resolution,15*resolution,5*resolution));
        commonGoalBox.setAlignment(Pos.CENTER);


        Label commonGoalLabel = new Label("Obiettivi comuni");
        commonGoalLabel.setFont(new Font("Comic Sans MS", 30* resolution));
        commonGoalLabel.setTextFill(Color.rgb(204, 153, 102));
        commonGoalBox.getChildren().add(commonGoalLabel);
        for(CommonGoalClone commonGoal : GUI.getClient().getModel().getCommonGoals()){
            StackPane cg = new StackPane();
            ImageView commonGoalImage = new ImageView(PicturesLoad.getCommonGoalsImgs().get(commonGoal.getIdRule()-1));
            commonGoalImage.setFitWidth(((bounds.getWidth()-bounds.getHeight())/2)*resolution*0.9);
            commonGoalImage.setFitHeight(((bounds.getWidth()-bounds.getHeight())/2)*resolution*0.66*0.9);
            int point = 0;
            if(commonGoal.getLastTokenCard() != null)
                point = commonGoal.getLastTokenCard().getPoints();

            ImageView tok = new ImageView(PicturesLoad.getToken(point));
            tok.setRotate(-8);
            tok.setFitWidth((((bounds.getWidth()-bounds.getHeight())/2)*resolution*0.66*0.9)/2.5);
            tok.setFitHeight((((bounds.getWidth()-bounds.getHeight())/2)*resolution*0.66*0.9)/2.5);
            tok.setX(50*resolution);
            HBox hbox = new HBox();
            hbox.setPrefWidth((((bounds.getWidth()-bounds.getHeight())/2)*resolution*0.9));
            hbox.setAlignment(Pos.CENTER_RIGHT);
            hbox.setPadding(new Insets(0,(((bounds.getWidth()-bounds.getHeight())/2)*resolution*0.9)/7,20*resolution,0));
            hbox.getChildren().add(tok);
            cg.getChildren().add(commonGoalImage);
            cg.getChildren().add(hbox);
            commonGoalBox.getChildren().add(cg);
        }


        HBox resizeButtonBox = new HBox(resizeWindow);
        HBox backButtonBox = new HBox(back);
        backButtonBox.autosize();


        VBox leftVBox = new VBox();
        leftVBox.setAlignment(Pos.BOTTOM_CENTER);
        leftVBox.setSpacing(30*resolution);
        leftVBox.setPadding(new Insets(0,0,25*resolution,0));

        VBox rightVBox = new VBox();
        rightVBox.setAlignment(Pos.TOP_CENTER);
        rightVBox.setSpacing(35*resolution);

        rightVBox.getChildren().add(resizeButtonBox);
        rightVBox.getChildren().add(commonGoalBox);
        leftVBox.getChildren().add(pointBox);


        //personalGoalBox.setPrefSize(((bounds.getWidth()-bounds.getHeight())/2)*resolution,bounds.getHeight()*resolution*0.2);

        if(user.getName().equals(GUI.getClient().getModel().getMyName()) || endgame){
            pgLabel.setFont(new Font("Comic Sans MS", 30* resolution));
            pgLabel.setTextFill(Color.rgb(204, 153, 102));
            personalGoalBox.setAlignment(Pos.CENTER);
            personalGoalBox.setBackground(new Background(backgroundParquet));
            personalGoalBox.setBorder(new Border(new BorderStroke(Color.rgb(204, 153, 102),  BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10*resolution))));
            personalGoal.setFitWidth(((bounds.getWidth()-bounds.getHeight())/2)*resolution*0.9);
            personalGoal.setFitHeight(((bounds.getWidth()-bounds.getHeight())/2)*resolution*1.5*0.9);
            personalGoalBox.setSpacing(20*resolution);
            personalGoalBox.setPadding(new Insets(5*resolution,0,10*resolution,0));
            personalGoalBox.getChildren().add(pgLabel);
            personalGoalBox.getChildren().add(personalGoal);
        }

        leftVBox.getChildren().add(personalGoalBox);
        leftVBox.getChildren().add(backButtonBox);

        resizeButtonBox.setAlignment(Pos.TOP_RIGHT);
        backButtonBox.setAlignment(Pos.BOTTOM_LEFT);
        leftVBox.setPrefSize((bounds.getWidth()-bounds.getHeight())*resolution/2,bounds.getHeight()*resolution);
        rightVBox.setPrefSize((bounds.getWidth()-bounds.getHeight())*resolution/2,bounds.getHeight()*resolution);

        bookshelfBox.getChildren().add(leftVBox);
        bookshelfBox.getChildren().add(bookshelfPane);
        bookshelfBox.getChildren().add(rightVBox);
        root.getChildren().add(bookshelfBox);
    }

}
