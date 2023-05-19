package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.PersonalButton;
import it.polimi.ingsw.server.model.object_card.ObjectCard;
import it.polimi.ingsw.server.model.plank.Plank;
import it.polimi.ingsw.server.model.user.User;
import it.polimi.ingsw.server.model.user.bookshelf.CellShelf;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUI_BookshelfController {
    public static void fillBookshelf(User user, StackPane pane, Rectangle2D bounds){
        CellShelf[][] cells = user.getBookshelf().getBookshelf();
        ImageView[][] objectCards=new ImageView[user.getBookshelf().getNumRow()][user.getBookshelf().getNumColumn()];
        for(int row = 0; row<=user.getBookshelf().getNumRow()-1; row++)
            for(int column = 0; column<=user.getBookshelf().getNumColumn()-1; column++){
                objectCards[row][column] = new ImageView();
                objectCards[row][column].setFitHeight((bounds.getHeight()*GUI.getResolution()/8)*0.95);
                objectCards[row][column].setFitWidth((bounds.getHeight()*GUI.getResolution()/8)*0.95);
                if(cells[row][column]!=null && cells[row][column].getObjectCard()!=null){
                    objectCards[row][column].setImage(PicturesLoad.getObjectCardImg(cells[row][column].getObjectCard().getColor(),cells[row][column].getObjectCard().getId()).getCardImg());
                }
                pane.getChildren().add(objectCards[row][column]);
            }
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

        VBox resizeButtonBox = new VBox(resizeWindow);
        VBox backButtonBox = new VBox(back);
        resizeButtonBox.setAlignment(Pos.TOP_RIGHT);
        backButtonBox.setAlignment(Pos.BOTTOM_LEFT);
        resizeButtonBox.setPrefWidth((bounds.getWidth()-bounds.getHeight())*resolution/2);
        backButtonBox.setPrefWidth((bounds.getWidth()-bounds.getHeight())*resolution/2);
        bookshelfBox.getChildren().add(backButtonBox);
        bookshelfBox.getChildren().add(bookshelfPane);
        bookshelfBox.getChildren().add(resizeButtonBox);
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
