package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.chat.Chat;
import it.polimi.ingsw.server.model.plank.CellPlank;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.io.FileInputStream;
import java.util.ArrayList;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUI extends Application implements View{

    @Override
    public Character selectTypeGame() {
        return null;
    }

    @Override
    public JSONObject lobby(Character choose) {
        return null;
    }

    @Override
    public void showNormalMessage(String message) {

    }

    @Override
    public void showCorrectMessage(String message) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showStateGame() throws Exception {

    }

    @Override
    public void showPlank() {

    }

    @Override
    public void showBookshelfs() {

    }

    @Override
    public void showBookshelf(String username) throws Exception {

    }

    @Override
    public void showPersonalGoal() {

    }

    @Override
    public void showCommonGoals() {

    }

    @Override
    public void showUsers() {

    }

    @Override
    public void showCommonGoal(int idCommonGoal) {

    }

    @Override
    public String catchAction() {
        return null;
    }

    @Override
    public ArrayList<CellPlank> drag() {
        return null;
    }

    @Override
    public int drop(int numCards) throws Exception {
        return 0;
    }

    @Override
    public ArrayList<CellPlank> reorderCards(ArrayList<CellPlank> cells) {
        return null;
    }

    @Override
    public void showEndGame() {

    }

    @Override
    public void openChat(Chat chat) {

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("hello-view.fxml"));
        Pane root = fxmlLoader.load();
        Scene scene = new Scene(root, stage.getMaxHeight(), stage.getMaxWidth());

        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/Publisher material/Display_1.jpg");
        Image imgBackground = new Image(reader);
        BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(imgBackground, BackgroundRepeat.NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);
        root.setBackground(new Background(backgroundImage));

        stage.setMaximized(true);
        stage.setTitle("MyShelfie");
        stage.setScene(scene);
        stage.show();
    }
}
