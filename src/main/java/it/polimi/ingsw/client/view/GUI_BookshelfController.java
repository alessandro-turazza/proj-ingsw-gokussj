package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.PersonalButton;
import it.polimi.ingsw.server.model.user.User;
import it.polimi.ingsw.server.model.user.bookshelf.CellShelf;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUI_BookshelfController {
    public static void fillBookshelf(User user, StackPane pane, Rectangle2D bounds){
        double resolution = GUI.getResolution();
        pane.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(0,0,(bounds.getHeight()/17)*resolution,0));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(19.5*resolution);
        CellShelf[][] cells = user.getBookshelf().getBookshelf();
        ImageView[][] objectCards=new ImageView[user.getBookshelf().getNumRow()][user.getBookshelf().getNumColumn()];
        for(int row = 0; row<=user.getBookshelf().getNumRow()-1; row++){
            HBox rowbox = new HBox();
            rowbox.setAlignment(Pos.CENTER);
            rowbox.setSpacing(39*GUI.getResolution());
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
        pane.getChildren().add(vBox);
    }

    public static void onBookshelfClick(User user) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-bookshelf.fxml")); //ciao
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

        StackPane bookshelfPane = GUI_BookshelfController.makeBookshelf(user);
        HBox bookshelfBox = new HBox();

        PersonalButton back = new PersonalButton(300.0, 70.0);
        back.setText("←Indietro");


        back.animation();
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                        GUI_TurnController.showStateGame();
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
                        GUI_BookshelfController.onBookshelfClick(user);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                GUI.getStage().setResizable(false);
            }
        });

        ImageView personalGoal = new ImageView(PicturesLoad.getPersonalGoalCardsImgs().get(user.getPersonalGoal().getId()-1));
        VBox personalGoalBox = new VBox();
        Label pgLabel = new Label("Obbiettivo personale");

        VBox pointBox = new VBox();
        BackgroundImage backgroundParquet = new BackgroundImage(PicturesLoad.getParquet(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);
        pointBox.setBackground(new Background(backgroundParquet));
        pointBox.setBorder(new Border(new BorderStroke(Color.rgb(204, 153, 102),  BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2*resolution))));

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
        points.setTextFill(Color.rgb(204, 153, 102));

        pointBox.getChildren().add(points);
        pointBox.getChildren().add(playerToken);

        VBox commonGoalBox = new VBox();
        commonGoalBox.setBackground(new Background(backgroundParquet));
        commonGoalBox.setBorder(new Border(new BorderStroke(Color.rgb(204, 153, 102),  BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10*resolution))));
        commonGoalBox.setSpacing(20*resolution);
        commonGoalBox.setPadding(new Insets(15*resolution,5*resolution,15*resolution,5*resolution));
        commonGoalBox.setAlignment(Pos.CENTER);


        Label commonGoalLabel = new Label("Obbettivi comuni");
        commonGoalLabel.setFont(new Font("Comic Sans MS", 30* resolution));
        commonGoalLabel.setTextFill(Color.rgb(204, 153, 102));

        StackPane cg1 = new StackPane();
        ImageView commonGoal1 = new ImageView(PicturesLoad.getCommonGoalsImgs().get(GUI.getClient().getModel().getCommonGoals().get(0).getIdRule()-1));
        commonGoal1.setFitWidth(resolution*138*2.5);
        commonGoal1.setFitHeight(resolution*91*2.5);
        int point1 = 0;

        if(GUI.getClient().getModel().getCommonGoals().get(0).getLastTokenCard() != null)
            point1 = GUI.getClient().getModel().getCommonGoals().get(0).getLastTokenCard().getPoints();

        ImageView tok1 = new ImageView(PicturesLoad.getToken(point1));
        tok1.setRotate(-8);
        tok1.setFitWidth(80*resolution);
        tok1.setFitHeight(80*resolution);
        HBox hbox1 = new HBox();
        hbox1.setPrefWidth(resolution*138*2.5);
        hbox1.setAlignment(Pos.CENTER_RIGHT);
        hbox1.setPadding(new Insets(0,77*resolution,20*resolution,0));
        hbox1.getChildren().add(tok1);
        cg1.getChildren().add(commonGoal1);
        cg1.getChildren().add(hbox1);

        StackPane cg2 = new StackPane();
        ImageView commonGoal2 = new ImageView(PicturesLoad.getCommonGoalsImgs().get(GUI.getClient().getModel().getCommonGoals().get(1).getIdRule()-1));
        commonGoal2.setFitWidth(resolution*138*2.5);
        commonGoal2.setFitHeight(resolution*91*2.5);
        int point2 = 0;
        if(GUI.getClient().getModel().getCommonGoals().get(1).getLastTokenCard() != null)
            point2 = GUI.getClient().getModel().getCommonGoals().get(1).getLastTokenCard().getPoints();
        ImageView tok2 = new ImageView(PicturesLoad.getToken(point2));
        tok2.setRotate(-8);
        tok2.setFitWidth(80*resolution);
        tok2.setFitHeight(80*resolution);
        tok2.setX(40*resolution);
        HBox hbox2 = new HBox();
        hbox2.setPrefWidth(resolution*138*2.5);
        hbox2.setAlignment(Pos.CENTER_RIGHT);
        hbox2.setPadding(new Insets(0,77*resolution,20*resolution,0));
        hbox2.getChildren().add(tok2);
        cg2.getChildren().add(commonGoal2);
        cg2.getChildren().add(hbox2);

        HBox resizeButtonBox = new HBox(resizeWindow);
        HBox backButtonBox = new HBox(back);

        commonGoalBox.getChildren().add(commonGoalLabel);
        commonGoalBox.getChildren().add(cg1);
        commonGoalBox.getChildren().add(cg2);

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

        personalGoalBox.setPrefSize(resolution*137*3, resolution*200*3);

        if(user.getName().equals(GUI.getClient().getModel().getMyName())){
            pgLabel.setFont(new Font("Comic Sans MS", 30* resolution));
            pgLabel.setTextFill(Color.rgb(204, 153, 102));
            personalGoalBox.setAlignment(Pos.CENTER);
            personalGoalBox.setBackground(new Background(backgroundParquet));
            personalGoalBox.setBorder(new Border(new BorderStroke(Color.rgb(204, 153, 102),  BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10*resolution))));
            personalGoal.setFitWidth(resolution*137*2.2);
            personalGoal.setFitHeight(resolution*200*2.2);
            personalGoalBox.setSpacing(20*resolution);
            personalGoalBox.setPadding(new Insets(5*resolution,0,10*resolution,0));
            personalGoalBox.getChildren().add(pgLabel);
            personalGoalBox.getChildren().add(personalGoal);
        }

        leftVBox.getChildren().add(personalGoalBox);
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

    public static StackPane makeBookshelf(User user){
        StackPane stackPane = new StackPane();
        double resolution = GUI.getResolution();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        ImageView bookshelf = new ImageView(PicturesLoad.getBookshelfImg());
        bookshelf.setFitWidth(bounds.getHeight()*resolution);
        bookshelf.setFitHeight(bounds.getHeight()*resolution);
        stackPane.getChildren().add(bookshelf);
        fillBookshelf(user, stackPane, bounds);
        return stackPane;
    }

}
