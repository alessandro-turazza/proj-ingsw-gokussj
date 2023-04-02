package it.polimi.ingsw.game_data_test;

import it.polimi.ingsw.game_data.GameData;
import it.polimi.ingsw.object_card.Color;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameDataTest {
    private static GameData gameData;

    @BeforeAll
    public static void initializeGameData(){
        gameData = new GameData();
    }

    @Test
    public void testLoadObjectCards() throws IOException, ParseException {
        String path = "src/data/Object_Cards_Data.json";
        gameData.loadObjectCards(path);

        assertEquals(gameData.getDataObjectCards().get(0).getId(), 1);
        assertEquals(gameData.getDataObjectCards().get(0).getColor(), Color.YELLOW);
        assertEquals(gameData.getDataObjectCards().get(0).getNumObjectCard(), 7);


    }
    @Test
    public void testLoadPersonalGoals() throws IOException, ParseException {
        String path = "src/data/PersonalGoals_Data.json";
        gameData.loadPersonalGoals(path);

        assertEquals(gameData.getPersonalGoalCards().get(0).getId(), 1);
        assertEquals(gameData.getPersonalGoalCards().get(0).getCostraints().get(0).getRow(), 0);
        assertEquals(gameData.getPersonalGoalCards().get(0).getCostraints().get(0).getColumn(), 0);
        assertEquals(gameData.getPersonalGoalCards().get(0).getCostraints().get(0).getColor(), Color.PINK);

    }

    @Test
    public void testLoadPlankConfig() throws IOException, ParseException {
        String path = "src/data/Plank_Config_1.json";
        gameData.loadPlankConfig(path);

        assertEquals(gameData.getPlank_config()[0][0], 0);
        assertEquals(gameData.getPlank_config()[0][3], 3);
        assertEquals(gameData.getPlank_config()[1][3], 2);

    }

    @Test
    public void testLoadTokensData() throws IOException, ParseException {
        String path = "src/data/Tokens_Data.json";
        gameData.loadTokens(path);

        assertEquals(gameData.getDataTokens().get(0).get(0),8);
        assertEquals(gameData.getDataTokens().get(0).get(1),4);

        assertEquals(gameData.getDataTokens().get(1).get(0),8);
        assertEquals(gameData.getDataTokens().get(1).get(1),6);
        assertEquals(gameData.getDataTokens().get(1).get(2),4);

        assertEquals(gameData.getDataTokens().get(2).get(0),8);
        assertEquals(gameData.getDataTokens().get(2).get(1),6);
        assertEquals(gameData.getDataTokens().get(2).get(2),4);
        assertEquals(gameData.getDataTokens().get(2).get(3),2);
    }

    @Test
    public void testLoadIdCommonGoals() throws IOException, ParseException {
        String path = "src/data/Common_Goals_Setup.json";
        gameData.loadIdCommonGoals(path);

        assertEquals(gameData.getIdCommonGoals().get(0), 1);
        assertEquals(gameData.getIdCommonGoals().get(1), 2);
    }


}
