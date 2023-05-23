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
    private static ArrayList<Image> personalGoalObjectCards;
    private static Image title;
    private static Image cranioLogo;
    private static Image polimiLogo;
    private static Image icon;
    private static Image playerMark;
    private static Image red;
    private static ArrayList<Image> place;
    private static Image place1;
    private static Image place2;
    private static Image place3;
    private static Image place4;
    private static Image endBackground;
    private static Image bookshelfbackgroundblury;
    private static Image bookshelfBackground;
    private  static Image parquetTurn;
    private static Image parquet;
    private static Image backgroundStart;
    private static Image plankImg;
    private static Image bookshelfImg;
    private static ArrayList<ObjectCardImg> objectCardImgs;
    private static ArrayList<Image> commonGoalsImgs;
    private static ArrayList<Image> personalGoalCardsImgs;

    private static ArrayList<Image> tokens;

    public static Image getToken(int point) {
        if(point == 2)
            return tokens.get(0);
        else if(point == 4)
            return tokens.get(1);
        else if(point == 6)
            return tokens.get(2);
        else if(point == 8)
            return tokens.get(3);
        else if (point == 1)
            return tokens.get(4);
        else
            return tokens.get(5);
    }

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

    public static void loadTokens() throws FileNotFoundException {
        tokens = new ArrayList<>();
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/scoring tokens/scoring_2.jpg");
        tokens.add(new Image(reader));
        reader = new FileInputStream("src/data/17_MyShelfie_BGA/scoring tokens/scoring_4.jpg");
        tokens.add(new Image(reader));
        reader = new FileInputStream("src/data/17_MyShelfie_BGA/scoring tokens/scoring_6.jpg");
        tokens.add(new Image(reader));
        reader = new FileInputStream("src/data/17_MyShelfie_BGA/scoring tokens/scoring_8.jpg");
        tokens.add(new Image(reader));
        reader = new FileInputStream("src/data/17_MyShelfie_BGA/scoring tokens/end game.jpg");
        tokens.add(new Image(reader));
        reader = new FileInputStream("src/data/17_MyShelfie_BGA/scoring tokens/scoring_back_EMPTY.jpg");
        tokens.add(new Image(reader));
    }


    public static void loadPlace() throws FileNotFoundException {
        place=new ArrayList<>();
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/misc/1posto.png");
        place1 = new Image(reader);
        place.add(place1);
        reader = new FileInputStream("src/data/17_MyShelfie_BGA/misc/2posto.png");
        place2 = new Image(reader);
        place.add(place2);
        reader = new FileInputStream("src/data/17_MyShelfie_BGA/misc/3posto.png");
        place3 = new Image(reader);
        place.add(place3);
        reader = new FileInputStream("src/data/17_MyShelfie_BGA/misc/4posto.png");
        place4 = new Image(reader);
        place.add(place4);

    }
    public static void loadParquet() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/misc/base_pagina2.jpg");
        parquet = new Image(reader);
    }

    public static void loadCranioLogo() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/Publisher material/Publisher.png");
        cranioLogo = new Image(reader);
    }

    public static void loadPolimiLogo() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/Publisher material/PolimiLogo.png");
        polimiLogo = new Image(reader);
    }
    public static void loadTitle() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/Publisher material/Title 2000x618px.png");
        title = new Image(reader);
    }

    public static void loadEndBackground() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/Publisher material/Display_2.jpg");
        endBackground = new Image(reader);
    }

    public static void loadPlayerMark() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/misc/firstplayertoken.png");
        playerMark = new Image(reader);
    }


    public static void loadParquetTurn() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/misc/sfondo parquet.jpg");
        parquetTurn = new Image(reader);
    }
    public static void loadBackgroundStart() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/Publisher material/Display_1.jpg");
        backgroundStart = new Image(reader);
    }
    public static void loadBookshelfbackgroundblury() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/Publisher material/bookshelfbackgroundblury.png");
        bookshelfbackgroundblury = new Image(reader);
    }

    public static void loadBookshelfBackground() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/Publisher material/Display_5.jpg");
        bookshelfBackground = new Image(reader);
    }

    public static void loadPlankImg() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/boards/livingroom.png");
        plankImg = new Image(reader);
    }
    public static void loadRed() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/boards/red.jpg");
        red = new Image(reader);
    }

    public static void loadBookshelfImg() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/boards/bookshelf_orth.png");
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
            String path = "src/data/17_MyShelfie_BGA/common goal cards/r_"+i+".jpg";
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
    public static void loadPersonalGoalObjectCards() throws FileNotFoundException {
        personalGoalObjectCards = new ArrayList<>();
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/personal goal cards/PgBlue.png");
        personalGoalObjectCards.add(new Image(reader));
        reader = new FileInputStream("src/data/17_MyShelfie_BGA/personal goal cards/PgGreen.png");
        personalGoalObjectCards.add(new Image(reader));
        reader = new FileInputStream("src/data/17_MyShelfie_BGA/personal goal cards/PgLightBlue.png");
        personalGoalObjectCards.add(new Image(reader));
        reader = new FileInputStream("src/data/17_MyShelfie_BGA/personal goal cards/PgPink.png");
        personalGoalObjectCards.add(new Image(reader));
        reader = new FileInputStream("src/data/17_MyShelfie_BGA/personal goal cards/PgWhite.png");
        personalGoalObjectCards.add(new Image(reader));
        reader = new FileInputStream("src/data/17_MyShelfie_BGA/personal goal cards/PgYellow.png");
        personalGoalObjectCards.add(new Image(reader));
    }

    public static void loadIcon() throws FileNotFoundException {
        FileInputStream reader = new FileInputStream("src/data/17_MyShelfie_BGA/Publisher material/Icon 50x50px.png");
        icon = new Image(reader);
    }

    public static void loadImages() throws IOException, ParseException {
        loadPlayerMark();
        loadBackgroundStart();
        loadPlankImg();
        loadBookshelfImg();
        loadCommonGoals();
        loadObjectCardsImgs();
        loadPersonalGoals();
        loadParquet();
        loadParquetTurn();
        loadBookshelfBackground();
        loadRed();
        loadBookshelfbackgroundblury();
        loadEndBackground();
        loadPlace();
        loadTokens();
        loadIcon();
        loadPersonalGoalObjectCards();
        loadCranioLogo();
        loadPolimiLogo();
        loadTitle();
    }

    public static Image getParquet() {
        return parquet;
    }

    public static Image getParquetTurn() {
        return parquetTurn;
    }

    public static Image getBookshelfBackground() {
        return bookshelfBackground;
    }

    public static Image getRed() {
        return red;
    }

    public static Image getBookshelfbackgroundblury() {
        return bookshelfbackgroundblury;
    }

    public static Image getEndBackground() {
        return endBackground;
    }

    public static Image getPlace(int numPlace) {
        if(numPlace<5 && numPlace>0)return place.get(numPlace-1);
        return null;
    }


    public static Image getPlayerMark() {
        return playerMark;
    }

    public static Image getIcon(){
        return icon;
    }

    public static Image getPersonalGoalObjectCard(Color color) {
        if(color==Color.BLUE)return personalGoalObjectCards.get(0);
        if(color==Color.GREEN)return personalGoalObjectCards.get(1);
        if(color==Color.LIGHT_BLUE)return personalGoalObjectCards.get(2);
        if(color==Color.PINK)return personalGoalObjectCards.get(3);
        if(color==Color.WHITE)return personalGoalObjectCards.get(4);
        if(color==Color.YELLOW)return personalGoalObjectCards.get(5);
        return null;
    }

    public static Image getCranioLogo() {
        return cranioLogo;
    }

    public static Image getPolimiLogo() {
        return polimiLogo;
    }

    public static Image getTitle() {
        return title;
    }
}
