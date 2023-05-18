package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.PersonalButton;
import it.polimi.ingsw.server.model.object_card.ObjectCard;
import it.polimi.ingsw.server.model.plank.CellPlank;
import it.polimi.ingsw.server.model.plank.Plank;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI_TurnController {

    public static void showStateGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-game.fxml"));
        Pane root = fxmlLoader.load();
        double resolution = GUI.getResolution();

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Scene scene = new Scene(root, bounds.getWidth()*resolution, bounds.getHeight()*resolution);
        Stage stage = GUI.getStage();
        stage.setScene(scene);
        root.setPrefSize(bounds.getWidth()*resolution, bounds.getHeight()*resolution);

        HBox hBox = new HBox();
        root.getChildren().add(hBox);

        Label l1 = new Label();
        l1.setPrefSize(((bounds.getWidth()-bounds.getHeight())/2)*resolution,bounds.getHeight()*resolution);

        hBox.getChildren().add(l1);
        StackPane stackPane=new StackPane();
        ImageView plank = new ImageView(PicturesLoad.getPlankImg());
        plank.setY(0);
        plank.setX((bounds.getWidth()/2)*resolution);
        plank.setFitHeight(bounds.getHeight()*resolution);
        plank.setFitWidth(bounds.getHeight()*resolution);
        stackPane.getChildren().add(plank);
        ImageView[][] objectCards=fillImageObjectCards();
        VBox vBox=new VBox();
        vBox.setPadding(new Insets(0.048*GUI.getResolution()* bounds.getHeight(),0,0,0.044*GUI.getResolution()* bounds.getHeight()));
        vBox.setSpacing(5.25*GUI.getResolution());
        for(int i=0;i<Plank.DIM;i++){
            HBox hBox1=new HBox();
            for(int j=0;j<Plank.DIM;j++){
                if(objectCards[i][j]!=null)hBox1.getChildren().add(objectCards[i][j]);
            }
            hBox1.setSpacing(5.25*GUI.getResolution());
            vBox.getChildren().add(hBox1);
        }

        stackPane.getChildren().add(vBox);
        hBox.getChildren().add(stackPane);

        Pane l2 = new Pane();
        l2.setPrefSize(((bounds.getWidth()-bounds.getHeight())/2)*resolution,bounds.getHeight()*resolution);
        hBox.getChildren().add(l2);

        PersonalButton resizeWindow = new PersonalButton();
        resizeWindow.setPrefSize(0.05*resolution*bounds.getHeight(), 0.05*resolution* bounds.getHeight());
        l2.getChildren().add(resizeWindow);
        if(GUI.getResolution() == GUI.HALF_SCREEN)
            resizeWindow.setText("↗");
        else resizeWindow.setText("↙");


        resizeWindow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setResizable(true);
                GUI_ResizeController.resize();
                try {
                    showStateGame();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setResizable(false);
            }
        });

    }

    public static ImageView[][] fillImageObjectCards(){
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        ImageView[][] objectCards=new ImageView[Plank.DIM][Plank.DIM];
        CellPlank[][] plank=GUI.getClient().getModel().getPlank().getBoard();
        for(int row=0;row<Plank.DIM;row++){
            for(int col=0;col<Plank.DIM;col++){
                objectCards[row][col]=new ImageView();
                objectCards[row][col].setFitHeight((bounds.getHeight()*GUI.getResolution()/10)*0.95);
                objectCards[row][col].setFitWidth((bounds.getHeight()*GUI.getResolution()/10)*0.95);
                if(plank[row][col]!=null && plank[row][col].getObjectCard()!=null){
                    ObjectCard obj=plank[row][col].getObjectCard();
                    objectCards[row][col].setImage(PicturesLoad.getObjectCardImg(obj.getColor(),obj.getId()).getCardImg());
                }
            }
        }
        return objectCards;
    }

}
