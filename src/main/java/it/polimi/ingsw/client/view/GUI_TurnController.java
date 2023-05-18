package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.PersonalButton;
import it.polimi.ingsw.server.model.object_card.ObjectCard;
import it.polimi.ingsw.server.model.plank.CellPlank;
import it.polimi.ingsw.server.model.plank.Plank;
import it.polimi.ingsw.server.model.user.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUI_TurnController {

    public static void showStateGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-game.fxml"));
        Pane root = fxmlLoader.load();
        double resolution = GUI.getResolution();

        BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getParquetTurn(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);
        root.setBackground(new Background(backgroundImage));

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Scene scene = new Scene(root, bounds.getWidth()*resolution, bounds.getHeight()*resolution);
        Stage stage = GUI.getStage();
        stage.setScene(scene);
        root.setPrefSize(bounds.getWidth()*resolution, bounds.getHeight()*resolution);

        HBox hBox = new HBox();
        root.getChildren().add(hBox);

        VBox userList = fillUsers(bounds, resolution);

        hBox.getChildren().add(userList);


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

        VBox l2 = new VBox();
        l2.setPrefSize(((bounds.getWidth()-bounds.getHeight())/2)*resolution,bounds.getHeight()*resolution);
        hBox.getChildren().add(l2);
        HBox hBoxResize = new HBox();
        hBoxResize.setPrefWidth(((bounds.getWidth()-bounds.getHeight())/2)*resolution);

        PersonalButton resizeWindow = new PersonalButton();
        resizeWindow.setPrefSize(0.05*resolution*bounds.getHeight(), 0.05*resolution* bounds.getHeight());
        hBoxResize.getChildren().add(resizeWindow);
        hBoxResize.setAlignment(Pos.TOP_RIGHT);
        l2.getChildren().add(hBoxResize);
        l2.setSpacing(20*resolution);
        if(GUI.getResolution() == GUI.HALF_SCREEN)
            resizeWindow.setText("↗");
        else resizeWindow.setText("↙");

        VBox chatBox = new VBox();

        ScrollPane chat = new ScrollPane();

        chat.setPrefSize((bounds.getWidth()-bounds.getHeight())/2*resolution,bounds.getHeight()*resolution/2);
        chatBox.getChildren().add(chat);

        TextField chatReader = new TextField();

        HBox chatBar = new HBox();

        VBox chatContainer = new VBox();

        chatBar.setPrefSize((bounds.getWidth()-bounds.getHeight())/2*resolution,bounds.getHeight()*resolution/15);

        PersonalButton chatSend = new PersonalButton();
        chatSend.setText("→");
        chatSend.autosize();

        chatReader.setMinSize(((bounds.getWidth()-bounds.getHeight())/2*resolution)*(4/5),bounds.getHeight()*resolution/15);

        //chatSend.setPrefSize(((bounds.getWidth()-bounds.getHeight())/2*resolution)*(1/5),bounds.getHeight()*resolution/15);

        chatBar.getChildren().add(chatReader);
        chatBar.getChildren().add(chatSend);

        chatBox.getChildren().add(chatBar);
        chat.setContent(chatContainer);
        for(int i = 0; i <=100; i++){
            Label label = new Label();
            label.setText( i + " User: messaggio");
            chatContainer.getChildren().add(label);
        }

        l2.getChildren().add(chatBox);

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

    public static VBox fillUsers(Rectangle2D bounds, double resolution){
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(resolution*50,resolution*50 ,resolution*50 ,resolution*50));
        vBox.setSpacing(20*resolution);
        double x = (bounds.getWidth()-bounds.getHeight())/2*resolution;
        double y = bounds.getHeight()*resolution/10;
        ArrayList<User> users = GUI.getClient().getModel().getPlayers();
        for( User u : users){
            PersonalButton userButton= new PersonalButton();
            userButton.setFont(new Font("Comic Sans MS", resolution*40));
            userButton.setStyle("-fx-effect: dropshadow( one-pass-box , #332200 , 0 , 0.0 , 6 , 5 ); -fx-background-color: #734d26");
            userButton.setText(u.getName());
            userButton.setPrefSize(x,y);
            //userButton.setMaxSize( vBox.getWidth(),vBox.getHeight()/16);
            //userButton.setMinSize( vBox.getWidth(),vBox.getHeight()/16);
            vBox.getChildren().add(userButton);
        }
        return vBox;
    }

}
