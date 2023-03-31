package it.polimi.ingsw.game_data;

import it.polimi.ingsw.object_card.Color;
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
    //private ArrayList<ObjectCard> objectCards;
    private ArrayList<PersonalGoalCard> personalGoalCards;
    private int[][] plank_config;

    public void loadObjectCards(String path) throws IOException, ParseException {
        FileReader fr = new FileReader(path);
        JSONObject obj = (JSONObject) new JSONParser().parse(fr);
        JSONArray list = (JSONArray) obj.get("cardList");

        for (Object o : list) {
            JSONObject cardObj = (JSONObject) o;
            int id = Integer.parseInt(cardObj.get("id").toString());
            Color color = convertToColor(cardObj.get("color").toString());
            int numOccorrenze = Integer.parseInt(cardObj.get("numobjectCard").toString());

            DataObjectCard card = new DataObjectCard(id,color, numOccorrenze);
            dataObjectCards.add(card);

        }
    }


}
