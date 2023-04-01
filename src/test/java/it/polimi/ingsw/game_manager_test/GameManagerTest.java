package it.polimi.ingsw.game_manager_test;

import it.polimi.ingsw.game_manager.GameManager;
import it.polimi.ingsw.game_data.GameData;
import it.polimi.ingsw.user.User;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameManagerTest {
    private static GameManager gameManager;
    private static ArrayList<User> usersTest;


    @Test
    public void startGameTest2Players() throws IOException, ParseException {
        usersTest = new ArrayList<>();
        usersTest.add(new User("User1"));
        usersTest.add(new User("User2"));

        gameManager = new GameManager(usersTest);
        GameData.loadObjectCards("src/data/Object_Cards_Data.json");
        GameData.loadPersonalGoals("src/data/PersonalGoals_Data.json");
        GameData.loadPlankConfig("src/data/Plank_Config_1.json");
        GameData.loadRuleCommons();

        gameManager.startGame();

        assertNotNull(gameManager.getPlank());
        assertNotNull(gameManager.getUsers());
        assertNotNull(gameManager.getCommonGoals());

        System.out.println("Plank: ");
        gameManager.getPlank().printPlank();

        System.out.println("Common goal 1: ");
        for(int i = 0; i < gameManager.getUsers().size(); i++)
            System.out.println("Token: " + gameManager.getCommonGoals().get(0).getTokenCards().get(i).getPoints());

        System.out.println("Common goal 2: ");
        for(int i = 0; i < gameManager.getUsers().size(); i++)
            System.out.println("Token: " + gameManager.getCommonGoals().get(1).getTokenCards().get(i).getPoints());

        for(int i = 0; i < gameManager.getUsers().size(); i++)
            System.out.println(gameManager.getUsers().get(i).getName() + " personal goal: " + gameManager.getUsers().get(i).getPersonalGoal().getId());


    }

    @Test
    public void startGameTest3Players() throws IOException, ParseException {
        usersTest = new ArrayList<>();
        usersTest.add(new User("User1"));
        usersTest.add(new User("User2"));
        usersTest.add(new User("User3"));

        gameManager = new GameManager(usersTest);
        GameData.loadObjectCards("src/data/Object_Cards_Data.json");
        GameData.loadPersonalGoals("src/data/PersonalGoals_Data.json");
        GameData.loadPlankConfig("src/data/Plank_Config_1.json");
        GameData.loadRuleCommons();

        gameManager.startGame();

        assertNotNull(gameManager.getPlank());
        assertNotNull(gameManager.getUsers());
        assertNotNull(gameManager.getCommonGoals());

        System.out.println("Plank: ");
        gameManager.getPlank().printPlank();

        System.out.println("Common goal 1: ");
        for(int i = 0; i < gameManager.getUsers().size(); i++)
            System.out.println("Token: " + gameManager.getCommonGoals().get(0).getTokenCards().get(i).getPoints());

        System.out.println("Common goal 2: ");
        for(int i = 0; i < gameManager.getUsers().size(); i++)
            System.out.println("Token: " + gameManager.getCommonGoals().get(1).getTokenCards().get(i).getPoints());

        for(int i = 0; i < gameManager.getUsers().size(); i++)
            System.out.println(gameManager.getUsers().get(i).getName() + " personal goal: " + gameManager.getUsers().get(i).getPersonalGoal().getId());


    }

    @Test
    public void startGameTest4Players() throws IOException, ParseException {
        usersTest = new ArrayList<>();
        usersTest.add(new User("User1"));
        usersTest.add(new User("User2"));
        usersTest.add(new User("User3"));
        usersTest.add(new User("User4"));

        gameManager = new GameManager(usersTest);
        GameData.loadObjectCards("src/data/Object_Cards_Data.json");
        GameData.loadPersonalGoals("src/data/PersonalGoals_Data.json");
        GameData.loadPlankConfig("src/data/Plank_Config_1.json");
        GameData.loadRuleCommons();

        gameManager.startGame();

        assertNotNull(gameManager.getPlank());
        assertNotNull(gameManager.getUsers());
        assertNotNull(gameManager.getCommonGoals());

        System.out.println("Plank: ");
        gameManager.getPlank().printPlank();

        System.out.println("Common goal 1: ");
        for(int i = 0; i < gameManager.getUsers().size(); i++)
            System.out.println("Token: " + gameManager.getCommonGoals().get(0).getTokenCards().get(i).getPoints());

        System.out.println("Common goal 2: ");
        for(int i = 0; i < gameManager.getUsers().size(); i++)
            System.out.println("Token: " + gameManager.getCommonGoals().get(1).getTokenCards().get(i).getPoints());

        for(int i = 0; i < gameManager.getUsers().size(); i++)
            System.out.println(gameManager.getUsers().get(i).getName() + " personal goal: " + gameManager.getUsers().get(i).getPersonalGoal().getId());


    }

}
