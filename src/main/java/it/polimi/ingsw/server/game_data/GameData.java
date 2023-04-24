package it.polimi.ingsw.server.game_data;

import it.polimi.ingsw.server.model.common_goal.rule_common.*;
import it.polimi.ingsw.server.model.object_card.Color;
import it.polimi.ingsw.server.model.user.personal_goal.Costraints;
import it.polimi.ingsw.server.model.user.personal_goal.PersonalGoalCard;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static it.polimi.ingsw.server.model.object_card.ObjectCard.convertToColor;

public class GameData {
    private static ArrayList<DataObjectCard> dataObjectCards;
    private static ArrayList<PersonalGoalCard> personalGoalCards;
    private static int[][] plank_config;
    private static ArrayList<RuleCommon> ruleCommons;
    private static ArrayList<Integer> idCommonGoals;
    private static ArrayList<ArrayList<Integer>> dataTokens;

    private static final int DIM = 9;


    public static ArrayList<DataObjectCard> getDataObjectCards() {
        return dataObjectCards;
    }

    public static ArrayList<PersonalGoalCard> getPersonalGoalCards() {
        return personalGoalCards;
    }

    public static int[][] getPlank_config() {
        return plank_config;
    }

    public static ArrayList<RuleCommon> getRuleCommons(){
        return ruleCommons;
    }

    public static ArrayList<ArrayList<Integer>> getDataTokens(){
        return dataTokens;
    }

    public static ArrayList<Integer> getIdCommonGoals(){
        return idCommonGoals;
    }

    public static void loadRuleCommons(){
        ruleCommons = new ArrayList<>();
        ruleCommons.add(new RuleCommonI());
        ruleCommons.add(new RuleCommonII());
        ruleCommons.add(new RuleCommonIII());
        ruleCommons.add(new RuleCommonIV());
        ruleCommons.add(new RuleCommonV());
        ruleCommons.add(new RuleCommonVI());
        ruleCommons.add(new RuleCommonVII());
        ruleCommons.add(new RuleCommonVIII());
        ruleCommons.add(new RuleCommonIX());
        ruleCommons.add(new RuleCommonX());
        ruleCommons.add(new RuleCommonXI());
        ruleCommons.add(new RuleCommonXII());
    }

    private static JSONObject readJson(String path) throws IOException, ParseException {
        FileReader fr = new FileReader(path);
        return (JSONObject) new JSONParser().parse(fr);
    }

    public static void loadObjectCards(String path) throws IOException, ParseException {
        dataObjectCards = new ArrayList<>();
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

    public static void loadPersonalGoals(String path) throws IOException, ParseException {
        personalGoalCards = new ArrayList<>();
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

            personalGoalCards.add(pg);
        }
    }

    public static void loadPlankConfig(String path) throws IOException, ParseException {
        plank_config = new int[DIM][DIM];
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

    public static void loadTokens(String path) throws IOException, ParseException {
        dataTokens = new ArrayList<>();

        JSONObject obj = readJson(path);
        JSONArray list = (JSONArray) obj.get("tokens");

        for(Object o: list){
            JSONObject jOb = (JSONObject) o;
            ArrayList<Integer> singleList = new ArrayList<>();
            JSONArray singleArr = (JSONArray) jOb.get("points");

            for(Object o2: singleArr){
                singleList.add(Integer.parseInt(o2.toString()));
            }

            dataTokens.add(singleList);

        }

    }

    public static void loadIdCommonGoals(String path) throws IOException, ParseException {
        idCommonGoals = new ArrayList<>();

        JSONObject obj = readJson(path);
        JSONArray list = (JSONArray) obj.get("listIdCommonGoal");

        for(Object o: list){
            idCommonGoals.add(Integer.parseInt(o.toString()));
        }
    }


}
