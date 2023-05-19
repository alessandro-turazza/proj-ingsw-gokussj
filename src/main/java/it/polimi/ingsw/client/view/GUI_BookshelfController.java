package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.PersonalButton;
import it.polimi.ingsw.server.model.object_card.ObjectCard;
import it.polimi.ingsw.server.model.plank.Plank;
import it.polimi.ingsw.server.model.user.User;
import it.polimi.ingsw.server.model.user.bookshelf.CellShelf;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

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

        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-bookshelf.fxml"));
        Pane root = fxmlLoader.load();
        double resolution = GUI.getResolution();


        BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getBookshelfBackground(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);
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

        resizeWindow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GUI.getStage().setResizable(true);
                GUI_ResizeController.resize();
                try {
                    onBookshelfClick(user);
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

        VBox rightVBox = new VBox();
        rightVBox.setAlignment(Pos.TOP_CENTER);
        rightVBox.setSpacing(10*resolution);

        rightVBox.getChildren().add(resizeButtonBox);
        if(user.getName().equals(GUI.getClient().getModel().getMyName())){
            rightVBox.getChildren().add(commonGoal1);
            rightVBox.getChildren().add(commonGoal2);
            leftVBox.getChildren().add(personalGoal);
        }
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
