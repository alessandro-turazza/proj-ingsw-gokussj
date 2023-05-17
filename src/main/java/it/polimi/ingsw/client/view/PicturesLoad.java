package it.polimi.ingsw.client.view;

import it.polimi.ingsw.server.model.game_manager.GameManager;
import it.polimi.ingsw.server.model.object_card.Color;
import it.polimi.ingsw.server.model.object_card.ObjectCard;
import javafx.scene.image.Image;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PicturesLoad {

    private static Image parquet;
    private static Image backgroundStart;
    private static Image plankImg;
    private static Image bookshelfImg;
    private static ArrayList<ObjectCardImg> objectCardImgs;
    private static ArrayList<Image> commonGoalsImgs;
    private static ArrayList<Image> personalGoalCardsImgs;
    public static Image getBackgroundStart() {
        return backgroundStart;
    }

    public static Image getPlankImg() {
        return plankImg;
    }

    public static Image getBookshelfImg() {
        return bookshelfImg;
    }

    public static ArrayList<ObjectCardImg> getObjectCardImgs() {
        return objectCardImgs;
    }

    public static ArrayList<Image> getCommonGoalsImgs() {
        return commonGoalsImgs;
    }

    public static ArrayList<Image> getPersonalGoalCardsImgs() {
        return personalGoalCardsImgs;
    }


    public static void loadParquet() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/misc/base_pagina2.jpg");
        parquet = new Image(reader);
    }
    public static void loadBackgroundStart() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/Publisher material/Display_1.jpg");
        backgroundStart = new Image(reader);
    }

    public static void loadPlankImg() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/boards/livingroom.png");
        plankImg = new Image(reader);
    }

    public static void loadBookshelfImg() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/boards/bookshelf.png");
        bookshelfImg = new Image(reader);
    }
    public static ObjectCardImg getObjectCardImg(Color color,int id){
        for(ObjectCardImg obj: objectCardImgs){
            if(obj.getColor().equals(color) && obj.getId()==id)return obj;
        }
        return null;
    }
    public static void loadObjectCardsImgs() throws IOException, ParseException {
        FileReader fr = new FileReader("src/data/ObjectCardsImages.json");
        JSONObject obj =  (JSONObject) new JSONParser().parse(fr);
        JSONArray list = (JSONArray) obj.get("cardList");

        objectCardImgs = new ArrayList<>();


        for(Object o: list){
            String path = "src/data/17_MyShelfie_BGA/item tiles/" + ( ((JSONObject) o).get("path") );
            Color color = ObjectCard.convertToColor(((JSONObject) o).get("color").toString());
            int id = Integer.parseInt( ((JSONObject) o).get("id").toString());

            ObjectCardImg objectCardImg = new ObjectCardImg(path, color, id);
            objectCardImgs.add(objectCardImg);
        }
    }

    public static void loadCommonGoals() throws FileNotFoundException {
        FileInputStream reader;
        commonGoalsImgs = new ArrayList<>();

        for(int i = 1; i <= GameManager.getNumCommonGoal(); i++){
            String path = "src/data/17_MyShelfie_BGA/common goal cards/"+i+".jpg";
            reader = new FileInputStream(path);
            commonGoalsImgs.add(new Image(reader));
        }
    }

    public static void loadPersonalGoals() throws FileNotFoundException {
        FileInputStream reader;
        personalGoalCardsImgs = new ArrayList<>();

        for(int i = 1; i <= GameManager.getNumPersonalGoal(); i++){
            String path = "src/data/17_MyShelfie_BGA/personal goal cards/Personal_Goals"+i+".png";
            reader = new FileInputStream(path);
            personalGoalCardsImgs.add(new Image(reader));
        }
    }

    public static void loadImages() throws IOException, ParseException {
        loadBackgroundStart();
        loadPlankImg();
        loadBookshelfImg();
        loadCommonGoals();
        loadObjectCardsImgs();
        loadPersonalGoals();
        loadParquet();
    }

    public static Image getParquet() {
        return parquet;
    }
}
