package it.polimi.ingsw.client.view;

import it.polimi.ingsw.server.model.plank.CellPlank;
import it.polimi.ingsw.server.model.user.User;
import it.polimi.ingsw.server.model.user.personal_goal.Costraints;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUI_StopController {
    private static boolean clickable;

    public static void showDrop(ArrayList<CellPlank> objectCardDrag) throws IOException {
        if(objectCardDrag.size()>0){
            clickable=true;
            double resolution=GUI.getResolution();
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            User user=GUI.getClient().getModel().getUserByName(GUI.getClient().getModel().getMyName());
            StackPane bookshelf=GUI_BookshelfController.makeBookshelfColumn(user);
            Stage stage=GUI.getStage();
            FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-dropcolumn.fxml"));
            Pane root = fxmlLoader.load();
            HBox container=new HBox();
            StackPane stackPane=new StackPane();
            stackPane.setPrefSize((bounds.getWidth()-bounds.getHeight())*resolution,bounds.getHeight()*resolution);
            VBox vBox=new VBox();
            vBox.setPadding(new Insets(10*resolution));
            vBox.getChildren().add(stackPane);
            container.getChildren().add(vBox);
            root.getChildren().add(container);
            BackgroundSize size1 = new BackgroundSize(0,0,true,true, true, true);
            BackgroundImage backgroundImage1 = new BackgroundImage(PicturesLoad.getParquet(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size1);
            vBox.setBackground(new Background(backgroundImage1));
            vBox.setSpacing(10*resolution);
            VBox stackPaneContained=new VBox();
            stackPane.getChildren().add(stackPaneContained);



            Scene scene = new Scene(root, bounds.getWidth()*resolution, bounds.getHeight()*resolution);
            stage.setScene(scene);
            root.setPrefSize(bounds.getWidth()*resolution, bounds.getHeight()*resolution);
            BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
            BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getBookshelfbackgroundblury(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);
            root.setBackground(new Background(backgroundImage));
            container.getChildren().add(bookshelf);
            HBox columnConteiner=new HBox();
            columnConteiner.setAlignment(Pos.CENTER);
            columnConteiner.setSpacing((bounds.getHeight()*resolution/8)*0.28);

            ArrayList<ImageView> column=new ArrayList<>();
            for(int i=0; i<user.getBookshelf().getNumColumn();i++){
                boolean full= false;
                try {
                    full = GUI.getClient().getModel().checkDrop(objectCardDrag.size(),i);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                ImageView imageView=new ImageView(PicturesLoad.getRed());
                if(full)imageView.setImage(PicturesLoad.getParquetTurn());
                StackPane imageViewStack=new StackPane();
                imageViewStack.setAlignment(Pos.TOP_CENTER);
                imageViewStack.setPrefWidth(bounds.getHeight()*resolution/8);
                imageViewStack.setPrefHeight(bounds.getHeight()*resolution);
                imageView.setOpacity(0.0);
                imageView.setFitWidth(bounds.getHeight()*resolution/8);
                imageView.setFitHeight(bounds.getHeight()*resolution);
                imageViewStack.getChildren().add(imageView);
                column.add(imageView);
                columnConteiner.getChildren().add(imageViewStack);
                imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(clickable) {
                            imageView.setOpacity(0.3);
                        }
                    }
                });
                imageView.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(clickable) {
                            imageView.setOpacity(0.0);
                        }
                    }
                });
                imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        int col=-1;
                        for(int i=0;i<column.size();i++){
                            if(imageView==column.get(i))col=i;
                        }
                        try {
                            if(clickable && GUI.getClient().getModel().checkDrop(objectCardDrag.size(),col)){
                                imageView.setOpacity(0.3);
                                clickable=false;

                                GUI_ColumnController.controllColumn(imageViewStack,col,objectCardDrag,stackPaneContained);
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                    }
                });
            }
            bookshelf.getChildren().add(columnConteiner);
        }

    }
}
