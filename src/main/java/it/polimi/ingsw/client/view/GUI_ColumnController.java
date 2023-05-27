package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.PersonalButton;
import it.polimi.ingsw.server.model.plank.CellPlank;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * This class manage the drop action in the drop scene
 */
public class GUI_ColumnController {
    private static Integer pos;
    private static ArrayList<ImageView> imgAnimations;
    private static ArrayList<CellPlank> objectCardDragOrdered;
    public static void controllColumn(StackPane imageViewStack,int column, ArrayList<CellPlank> objectCardDrag, VBox stackPaneContained){
        objectCardDragOrdered=new ArrayList<>();
        imgAnimations=new ArrayList<>();
        double resolution=GUI.getResolution();
        pos = GUI.getClient().getModel().getMyBookshelf().checkColumn(column);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        HBox correntCard=new HBox();
        correntCard.setAlignment(Pos.CENTER);
        correntCard.setSpacing(10*resolution);
        VBox ordenCard =new VBox();
        ordenCard.setSpacing(10*resolution);
        ordenCard.setAlignment(Pos.CENTER);

        ArrayList<ImageView> cardOrdered=new ArrayList<>();

        for(CellPlank ignored :objectCardDrag){
            ImageView imageView=new ImageView();
            imageView.setFitHeight(bounds.getHeight()*resolution/9);
            imageView.setFitWidth(bounds.getHeight()*resolution/9);
            cardOrdered.add(imageView);
            ordenCard.getChildren().add(imageView);
        }

        ArrayList<ImageView> correntCardImg=new ArrayList<>();
        for(CellPlank cellPlank:objectCardDrag){
            ImageView imageView=new ImageView(PicturesLoad.getObjectCardImg(cellPlank.getObjectCard().getColor(),cellPlank.getObjectCard().getId()).getCardImg());
            imageView.setFitHeight(bounds.getHeight()*resolution/9);
            imageView.setFitWidth(bounds.getHeight()*resolution/9);
            imageView.setOnMouseClicked(mouseEvent -> {
                if(imageView.getOpacity()==1.0){
                    for(int i=cardOrdered.size()-1;i>=0;i--){
                        if(cardOrdered.get(i).getImage()==null){
                            ImageView imgAnimation=new ImageView();
                            imgAnimation.setImage(imageView.getImage());
                            imgAnimation.setFitHeight((bounds.getHeight()*resolution/8)*0.94);
                            imgAnimation.setFitWidth((bounds.getHeight()*resolution/8)*0.94);
                            imageViewStack.getChildren().add(imgAnimation);
                            imgAnimations.add(imgAnimation);
                            TranslateTransition translateTransition=new TranslateTransition();
                            translateTransition.setNode(imgAnimation);
                            translateTransition.setDuration(Duration.millis(250));
                            translateTransition.setFromY(0);
                            if(pos==0)translateTransition.setToY(imageViewStack.getHeight()/14.5);
                            else if(pos==1)translateTransition.setToY(imageViewStack.getHeight()/4.80);
                            else if(pos==2)translateTransition.setToY(imageViewStack.getHeight()/2.88);
                            else if(pos==3)translateTransition.setToY(imageViewStack.getHeight()/2.06);
                            else if(pos==4)translateTransition.setToY(imageViewStack.getHeight()/1.60);
                            else if(pos==5)translateTransition.setToY(imageViewStack.getHeight()/1.31);
                            pos--;
                            translateTransition.play();
                            cardOrdered.get(i).setImage(imageView.getImage());
                            objectCardDragOrdered.add(cellPlank);
                            break;
                        }
                    }
                    imageView.setOpacity(0.5);
                }
            });
            correntCard.getChildren().add(imageView);
            correntCardImg.add(imageView);

        }
        stackPaneContained.setSpacing(10*resolution);
        stackPaneContained.setAlignment(Pos.CENTER);
        stackPaneContained.getChildren().add(correntCard);
        Label label=new Label("CARTE ORDINATE");
        label.setTextFill(Color.rgb(204, 153, 102));
        label.setFont(new Font("Comic Sans MS", 20*resolution));
        stackPaneContained.getChildren().add(label);
        stackPaneContained.getChildren().add(ordenCard);
        HBox bottons=new HBox();
        bottons.setSpacing(10*resolution);
        bottons.setAlignment(Pos.CENTER);
        PersonalButton back=new PersonalButton(200.0,70.0);
        back.setText("Annulla");
        PersonalButton confirm=new PersonalButton(200.0,70.0);
        confirm.setText("Conferma");
        bottons.getChildren().add(back);
        bottons.getChildren().add(confirm);
        PersonalButton backAll = new PersonalButton(400.0, 70.0);
        backAll.setText("Annulla Tutto");


        backAll.animation();
        backAll.setOnAction(actionEvent -> {
            try {
                GUI_TurnController.showStateGame();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        stackPaneContained.getChildren().add(bottons);
        stackPaneContained.getChildren().add(backAll);

        back.animation();
        back.setOnAction(actionEvent -> {
            for(ImageView imageView:correntCardImg)imageView.setOpacity(1.0);
            for(ImageView imageView:cardOrdered)imageView.setImage(null);
            for(ImageView imageView: imgAnimations){
                pos=GUI.getClient().getModel().getMyBookshelf().checkColumn(column);
                imageView.setImage(null);
            }
            imgAnimations=new ArrayList<>();
            objectCardDragOrdered =new ArrayList<>();
            imageViewStack.getChildren().removeAll();
        });

        confirm.animation();
        confirm.setOnAction(actionEvent -> {
            if(objectCardDragOrdered.size()==objectCardDrag.size()){
                GUI.getClient().getMessager().sendMessage(GUI.getClient().getMessager().getMessageHandler().sendDragAndDrop(objectCardDragOrdered,column));
            }


        });




    }
}
