package it.polimi.ingsw.game_data_test;

import it.polimi.ingsw.game_data.GameData;
import org.junit.jupiter.api.BeforeAll;

public class GameDataTest {
    private static GameData gameData;

    @BeforeAll
    public static void initializeGameData(){
        gameData = new GameData();
    }

}
