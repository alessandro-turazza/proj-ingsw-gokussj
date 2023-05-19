package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.PersonalButton;
import it.polimi.ingsw.server.model.plank.CellPlank;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;

import java.util.ArrayList;

public class GUI_ColumnController {

    private static ArrayList<CellPlank> objectCardDragOrdered;
    public static void controllColumn(int column, ArrayList<CellPlank> objectCardDrag, VBox stackPaneContained){
        objectCardDragOrdered=new ArrayList<>();
        double resolution=GUI.getResolution();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        HBox correntCard=new HBox();
        correntCard.setAlignment(Pos.CENTER);
        correntCard.setSpacing(10*resolution);
        VBox ordenCard =new VBox();
        ordenCard.setSpacing(10*resolution);
        ordenCard.setAlignment(Pos.CENTER);

        ArrayList<ImageView> cardOrdered=new ArrayList<>();

        for(CellPlank cellPlank:objectCardDrag){
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
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(imageView.getOpacity()==1.0){
                        for(ImageView img:cardOrdered){
                            if(img.getImage()==null){
                                img.setImage(imageView.getImage());
                                objectCardDragOrdered.add(cellPlank);
                                break;
                            }
                        }
                        imageView.setOpacity(0.5);
                    }
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
        stackPaneContained.getChildren().add(bottons);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for(ImageView imageView:correntCardImg)imageView.setOpacity(1.0);
                for(ImageView imageView:cardOrdered)imageView.setImage(null);
                objectCardDragOrdered =new ArrayList<>();
            }
        });
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(objectCardDragOrdered.size()==objectCardDrag.size()){
                    GUI.getClient().getMessager().sendMessage(GUI.getClient().getMessager().getMessageHandler().sendDragAndDrop(objectCardDragOrdered,column));
                }


            }
        });




    }
}
