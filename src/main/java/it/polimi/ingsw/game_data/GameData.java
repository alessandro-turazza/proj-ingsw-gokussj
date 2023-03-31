package it.polimi.ingsw.game_data;

import it.polimi.ingsw.object_card.Color;
import it.polimi.ingsw.user.personal_goal.Costraints;
import it.polimi.ingsw.user.personal_goal.PersonalGoalCard;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static it.polimi.ingsw.object_card.ObjectCard.convertToColor;

public class GameData {
    private ArrayList<DataObjectCard> dataObjectCards;
    private ArrayList<PersonalGoalCard> personalGoalCards;
    private int[][] plank_config;

    private final int DIM = 9;


    public ArrayList<DataObjectCard> getDataObjectCards() {
        return dataObjectCards;
    }

    public ArrayList<PersonalGoalCard> getPersonalGoalCards() {
        return personalGoalCards;
    }

    public int[][] getPlank_config() {
        return plank_config;
    }

    private JSONObject readJson(String path) throws IOException, ParseException {
        FileReader fr = new FileReader(path);
        JSONObject obj = (JSONObject) new JSONParser().parse(fr);
        return obj;
    }

    public void loadObjectCards(String path) throws IOException, ParseException {
        this.dataObjectCards = new ArrayList<>();
        JSONObject obj = readJson(path);
        JSONArray list = (JSONArray) obj.get("cardList");

        for (Object o : list) {
            JSONObject cardObj = (JSONObject) o;
            int id = Integer.parseInt(cardObj.get("id").toString());
            Color color = convertToColor(cardObj.get("color").toString());
            int numOccorrenze = Integer.parseInt(cardObj.get("numObjectCard").toString());

            DataObjectCard card = new DataObjectCard(id,color, numOccorrenze);
            dataObjectCards.add(card);

        }
    }

    public void loadPersonalGoals(String path) throws IOException, ParseException {
        this.personalGoalCards = new ArrayList<>();
        JSONObject obj = readJson(path);
        JSONArray list = (JSONArray) obj.get("listPersonalGoals");
        int i = 1;

        for (Object o : list) {
            JSONObject oggList = (JSONObject) o;
            JSONArray persGoalList = (JSONArray) oggList.get("listGoal");
            PersonalGoalCard pg = new PersonalGoalCard(i++);

            for(Object ob: persGoalList){
                JSONObject singleGoal = (JSONObject) ob;
                Color color = convertToColor(singleGoal.get("color").toString());
                int row = Integer.parseInt(singleGoal.get("row").toString());
                int column = Integer.parseInt(singleGoal.get("column").toString());
                Costraints costraint = new Costraints(row, column, color);
                pg.getCostraints().add(costraint);
            }

            this.personalGoalCards.add(pg);
        }
    }

    public void loadPlankConfig(String path) throws IOException, ParseException {
        this.plank_config = new int[DIM][DIM];
        JSONObject obj = readJson(path);
        JSONArray list = (JSONArray) obj.get("plank");
        int i = 0, j = 0;

        for(Object ob1: list){
            JSONObject obJs1 = (JSONObject) ob1;
            JSONArray row = (JSONArray) obJs1.get("row");

            for(Object ob2: row){
                int cell = Integer.parseInt(ob2.toString());
                plank_config[i][j] = cell;
                j++;
            }

            j=0;
            i++;
        }

    }


}
