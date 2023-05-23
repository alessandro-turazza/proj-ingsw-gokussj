package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.PersonalButton;
import it.polimi.ingsw.client.PersonalTextField;
import it.polimi.ingsw.client.chat.ChatMessage;
import it.polimi.ingsw.server.model.object_card.ObjectCard;
import it.polimi.ingsw.server.model.plank.CellPlank;
import it.polimi.ingsw.server.model.plank.Plank;
import it.polimi.ingsw.server.model.user.User;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUI_TurnController {
    private static VBox chatContainer;
    private static ArrayList<ImageView> cardDragVector;
    private static ArrayList<CellPlank> objectCardDrag;
    private static final int MAX_CELLS_DROP=3;
    private static VBox chatBox;
    private static double scrollPaneWidth;
    private static double scrollPaneHeight;

    private static ScrollPane chat;

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

        //LEFT PANEL
        VBox userList = fillUsers(bounds, resolution);
        //start draganddrop element
        if(GUI.getClient().getModel().getActiveUser().equals(GUI.getClient().getModel().getMyName())) {
            HBox cardDragged = new HBox();
            BackgroundSize sizeCardDragged = new BackgroundSize(0, 0, true, true, true, true);
            cardDragged.setBackground(new Background(new BackgroundImage(PicturesLoad.getParquet(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, sizeCardDragged)));
            cardDragged.setAlignment(Pos.CENTER);
            cardDragged.setSpacing(5 * resolution);
            cardDragged.setPrefSize(((bounds.getWidth() - bounds.getHeight()) / 2) * resolution, (bounds.getHeight() / 8) * resolution);
            cardDragVector = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight((bounds.getHeight() * GUI.getResolution() / 10) * 0.80);
                imageView.setFitWidth((bounds.getHeight() * GUI.getResolution() / 10) * 0.80);
                cardDragVector.add(imageView);
                cardDragged.getChildren().add(imageView);
            }
            objectCardDrag = new ArrayList<>();
            userList.getChildren().add(cardDragged);
            PersonalButton stopDrag = new PersonalButton(((bounds.getWidth() - bounds.getHeight()) / 2), 70.0);
            stopDrag.setText("STOP");
            stopDrag.animation();
            stopDrag.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        GUI_StopController.showDrop(objectCardDrag);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });


            userList.getChildren().add(stopDrag);
        }
        //end draganddrop element
        hBox.getChildren().add(userList);


        //CENTER PANEL
        StackPane stackPane=new StackPane();


        ImageView plank = new ImageView(PicturesLoad.getPlankImg());
        plank.setY(0);
        plank.setX((bounds.getWidth()/2)*resolution);
        plank.setFitHeight(bounds.getHeight()*resolution);
        plank.setFitWidth(bounds.getHeight()*resolution);
        stackPane.getChildren().add(plank);
        ImageView endGame = new ImageView(PicturesLoad.getToken(1));
        VBox endGameVBox = new VBox(endGame);
        if(!GUI.getClient().getModel().isLastTurn()){
            stackPane.getChildren().add(endGameVBox);
            endGameVBox.setPrefSize(bounds.getHeight()*resolution,bounds.getHeight()*resolution);
            endGameVBox.setAlignment(Pos.BOTTOM_RIGHT);
            endGameVBox.setPadding(new Insets(0, (bounds.getHeight()/11)*resolution, (bounds.getHeight()/4.9)*resolution, 0));
            endGame.setFitWidth((bounds.getHeight()*resolution/10)*0.94);
            endGame.setFitHeight((bounds.getHeight()*resolution/10)*0.94);
            endGame.setRotate(8.2);
        }
        VBox alarmVBox = new VBox();
        alarmVBox.setPrefSize(bounds.getHeight()*resolution,bounds.getHeight()*resolution);
        alarmVBox.setAlignment(Pos.TOP_CENTER);
        Label lastTurnlabel = new Label("ATTENZIONE! ULTIMO TURNO!");
        if(GUI.getClient().getModel().isLastTurn()){
            lastTurnlabel.setAlignment(Pos.TOP_CENTER);
            lastTurnlabel.setTextFill(Color.rgb(180, 1, 1));
            lastTurnlabel.setFont(new Font("Verdana", 40*resolution));
            lastTurnlabel.setStyle("-fx-font-weight: bold");
            FadeTransition transition = new FadeTransition();
            transition.setNode(lastTurnlabel);
            transition.setDuration(Duration.millis(1500));
            transition.setCycleCount(TranslateTransition.INDEFINITE);
            transition.setInterpolator(Interpolator.EASE_IN);
            transition.setFromValue(0);
            transition.setToValue(1);
            transition.setAutoReverse(true);
            transition.play();
            alarmVBox.getChildren().add(lastTurnlabel);
            stackPane.getChildren().add(alarmVBox);
        }
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





        //RIGHT PANEL
        VBox l2 = new VBox();
        l2.setPrefSize(((bounds.getWidth()-bounds.getHeight())/2)*resolution,bounds.getHeight()*resolution);
        hBox.getChildren().add(l2);
        HBox hBoxResize = new HBox();
        hBoxResize.setPrefWidth(((bounds.getWidth()-bounds.getHeight())/2)*resolution);

        PersonalButton resizeWindow = new PersonalButton(70.0,70.0);
        hBoxResize.getChildren().add(resizeWindow);
        hBoxResize.setAlignment(Pos.TOP_RIGHT);
        l2.getChildren().add(hBoxResize);
        l2.setSpacing(20*resolution);
        if(GUI.getResolution() == GUI.HALF_SCREEN)
            resizeWindow.setText("↗");
        else resizeWindow.setText("↙");
        resizeWindow.animation();

        chat = new ScrollPane();
        chatBox=new VBox();
        scrollPaneWidth=(bounds.getWidth()-bounds.getHeight())/2*GUI.getResolution()*0.945;
        scrollPaneHeight=bounds.getHeight()*GUI.getResolution();
        chat.setPrefViewportHeight(0);
        chat.setPrefViewportWidth(0);
        chat.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chat.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        showChat();
        chat.setContent(chatContainer);
        chatBox.getChildren().add(chat);
        PersonalTextField chatReader = new PersonalTextField(scrollPaneWidth*0.60/resolution,(scrollPaneHeight/15)/resolution);

        HBox chatBar = new HBox();

        PersonalButton chatSend = new PersonalButton((scrollPaneWidth*0.40)/resolution,(scrollPaneHeight/15)/resolution);
        chatSend.setText("→");
        chatSend.animation();
        chatSend.setBorder(new Border(new BorderStroke(Color.rgb(204, 153, 102),  BorderStrokeStyle.SOLID, new CornerRadii(10*resolution), new BorderWidths(resolution*3))));
        chatSend.setFont(new Font("Comic Sans MS", resolution*20));

        chatBar.getChildren().add(chatReader);
        chatBar.getChildren().add(chatSend);

        chatBox.getChildren().add(chatBar);
        chatBox.setPadding(new Insets(10*resolution));
        chatBox.setSpacing(10*resolution);


        l2.getChildren().add(chatBox);
        chatSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String message=chatReader.getText();
                if(!Objects.equals(message, "") && message != null) {
                    GUI.getClient().getMessager().sendMessage(GUI.getClient().getMessager().getMessageHandler().sendMessageChat(message,GUI.getClient().getModel().getMyName()));
                }
                chatReader.setText("");
            }
        });
        chatReader.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode()==KeyCode.ENTER){
                    String message=chatReader.getText();
                    System.out.println(GUI.getClient().getModel().getMyName()+ "  "+ message);
                    GUI.getClient().getMessager().sendMessage(GUI.getClient().getMessager().getMessageHandler().sendMessageChat(message,GUI.getClient().getModel().getMyName()));
                    chatReader.setText("");
                }
            }
        });

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
                    int finalRow = row;
                    int finalCol = col;
                    objectCards[row][col].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            if (GUI.getClient().getModel().getActiveUser().equals(GUI.getClient().getModel().getMyName())) {
                                int min=MAX_CELLS_DROP;
                                int maxCellFreeBookshelf=0;
                                for(int i=0;i<GUI.getClient().getModel().getMyBookshelf().getNumColumn();i++){
                                    Integer temp=GUI.getClient().getModel().getMyBookshelf().checkColumn(i);
                                    if(temp!=null) if(temp+1>maxCellFreeBookshelf)maxCellFreeBookshelf=GUI.getClient().getModel().getMyBookshelf().checkColumn(i)+1;
                                }
                                min=Math.min(min,maxCellFreeBookshelf);

                                if (objectCards[finalRow][finalCol].getOpacity() == 1.0 && objectCardDrag.size()<min) {

                                    CellPlank[][] cellPlanks = GUI.getClient().getModel().getPlank().getBoard();
                                    objectCardDrag.add(cellPlanks[finalRow][finalCol]);
                                    if (!GUI.getClient().getModel().checkDrag(objectCardDrag)) {
                                        rotate(objectCards[finalRow][finalCol]);
                                        objectCardDrag.remove(objectCardDrag.size() - 1);
                                    } else {
                                        for (ImageView imageView : cardDragVector) {
                                            if (imageView.getImage() == null) {
                                                imageView.setImage(objectCards[finalRow][finalCol].getImage());
                                                break;
                                            }
                                        }
                                        objectCards[finalRow][finalCol].setOpacity(0.5);
                                    }
                                } else {
                                    for (CellPlank cellPlank : objectCardDrag) {
                                        objectCards[cellPlank.getRow()][cellPlank.getColumn()].setOpacity(1.0);
                                    }
                                    for (ImageView imageView : cardDragVector) imageView.setImage(null);
                                    objectCardDrag = new ArrayList<>();
                                }

                            }
                        }
                    });
                }
            }
        }
        return objectCards;
    }
    public static void rotate(ImageView imageView){
        RotateTransition rotateTransition=new RotateTransition();
        rotateTransition.setNode(imageView);
        rotateTransition.setDuration(Duration.millis(130));
        rotateTransition.setInterpolator(Interpolator.EASE_IN);
        rotateTransition.setFromAngle(15);
        rotateTransition.setToAngle(0);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();
        RotateTransition rotateTransition2=new RotateTransition();
        rotateTransition2.setNode(imageView);
        rotateTransition2.setDelay(Duration.millis(130));
        rotateTransition2.setDuration(Duration.millis(130));
        rotateTransition2.setInterpolator(Interpolator.EASE_IN);
        rotateTransition2.setFromAngle(-15);
        rotateTransition2.setToAngle(0);
        rotateTransition2.setAutoReverse(true);
        rotateTransition2.play();
    }

    public static VBox fillUsers(Rectangle2D bounds, double resolution){

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(resolution*50,resolution*50 ,resolution*50 ,resolution*50));
        vBox.setSpacing(20*resolution);
        vBox.setPrefSize(((bounds.getWidth()-bounds.getHeight())/2)*resolution,bounds.getHeight()*resolution);
        double x = (bounds.getWidth()-bounds.getHeight())/2;
        double y = bounds.getHeight()/10;
        ArrayList<User> users = GUI.getClient().getModel().getPlayers();
        for( User u : users){
            Image image = null;
            ImageView activeToken = new ImageView(image);
            activeToken.setFitHeight(resolution*35);
            activeToken.setFitWidth(resolution*35);
            String name = u.getName();
            String activeUser = GUI.getClient().getModel().getActiveUser();
            PersonalButton userButton= new PersonalButton(x,y);
            double sizeText = 24;


            if(u.getName().length() >= 10)
                sizeText = 14;
            if(u.getName().length() >= 12)
                sizeText = 10;
            userButton.setTextAlignment(TextAlignment.CENTER);
            userButton.setFont(new Font("Comic Sans MS", resolution*sizeText));

            userButton.setStyle("-fx-background-color: #734d26");
            userButton.setBorder(new Border(new BorderStroke(Color.rgb(77, 40, 0),  BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5*GUI.getResolution()))));
            userButton.setText(name);
            vBox.getChildren().add(userButton);
            userButton.setAlignment(Pos.CENTER_LEFT);
            userButton.setGraphic(activeToken);
            if(name.equals(activeUser)){
                activeToken.setImage(PicturesLoad.getPlayerMark());
                userButton.setGraphic(activeToken);
            }
            userButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        GUI_BookshelfController.onBookshelfClick(GUI.getClient().getModel().getUserByName(userButton.getText()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            });
    }
        return vBox;}

    public static void showChat(){
        chat.setBackground(new Background(new BackgroundFill(Color.rgb(204, 153, 102), new CornerRadii(10*GUI.getResolution()),Insets.EMPTY)));
        chat.setPrefSize(scrollPaneWidth*0.65,scrollPaneHeight*0.75);
        chatContainer = new VBox();
        chatContainer.setMinSize(scrollPaneWidth*0.65, scrollPaneHeight);
        chatContainer.setPrefWidth(scrollPaneWidth);
        BackgroundSize sizeContainer = new BackgroundSize(0, 0, true, true, true, true);
        chatContainer.setBackground(new Background(new BackgroundImage(PicturesLoad.getParquet(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, sizeContainer)));

        for(ChatMessage chatMessage: GUI.getClient().getChat().chatPrint()){
            HBox mBox = new HBox();
            VBox messageBox = new VBox();
            messageBox.setBackground(new Background(new BackgroundFill(Color.rgb(239, 207, 175),new CornerRadii(10*GUI.getResolution()), Insets.EMPTY)));
            Label nameLabel=new Label(chatMessage.getNamePlayer());
            nameLabel.setStyle("-fx-font-weight: bold");
            Label textLabel=new Label(chatMessage.getMessage());
            nameLabel.setFont(new Font("Verdana", 17*GUI.getResolution()));
            textLabel.setFont(new Font("Verdana", 17*GUI.getResolution()));
            if(chatMessage.getNamePlayer().equals(GUI.getClient().getModel().getMyName())){
                mBox.setAlignment(Pos.CENTER_RIGHT);
                messageBox.setPadding(new Insets(2*GUI.getResolution(),7*GUI.getResolution(),2*GUI.getResolution(),2*GUI.getResolution()));
            }else{
                mBox.setAlignment(Pos.CENTER_LEFT);
                messageBox.setPadding(new Insets(2*GUI.getResolution(),2*GUI.getResolution(),2*GUI.getResolution(),7*GUI.getResolution()));
                messageBox.getChildren().add(nameLabel);
            }
            messageBox.getChildren().add(textLabel);
            mBox.getChildren().add(messageBox);
            chatContainer.getChildren().add(mBox);
        }

    }

}
