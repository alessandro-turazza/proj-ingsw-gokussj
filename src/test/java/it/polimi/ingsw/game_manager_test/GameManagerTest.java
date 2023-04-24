package it.polimi.ingsw.game_manager_test;

import it.polimi.ingsw.server.model.game_manager.GameManager;
import it.polimi.ingsw.server.game_data.GameData;
import it.polimi.ingsw.server.model.user.User;
import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;
import it.polimi.ingsw.user_test.BookshelfTest;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameManagerTest {
    private static GameManager gameManager;
    private static ArrayList<User> usersTest;

    @BeforeAll
    public static void inititialize() throws IOException, ParseException {
        GameData.loadObjectCards("src/data/Object_Cards_Data.json");
        GameData.loadPersonalGoals("src/data/PersonalGoals_Data.json");
        GameData.loadPlankConfig("src/data/Plank_Config_1.json");
        GameData.loadTokens("src/data/Tokens_Data.json");
        GameData.loadIdCommonGoals("src/data/Common_Goals_Setup.json");
        GameData.loadRuleCommons();
    }

    @Test
    public void startGameTest2Players() {
        usersTest = new ArrayList<>();
        usersTest.add(new User("User1"));
        usersTest.add(new User("User2"));

        gameManager = new GameManager(usersTest);
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
    public void startGameTest3Players() {
        usersTest = new ArrayList<>();
        usersTest.add(new User("User1"));
        usersTest.add(new User("User2"));
        usersTest.add(new User("User3"));

        gameManager = new GameManager(usersTest);
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
    public void startGameTest4Players() {
        usersTest = new ArrayList<>();
        usersTest.add(new User("User1"));
        usersTest.add(new User("User2"));
        usersTest.add(new User("User3"));
        usersTest.add(new User("User4"));

        gameManager = new GameManager(usersTest);
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
    public void endGameTest() throws IOException, ParseException {
        usersTest = new ArrayList<>();
        usersTest.add(new User("User1"));
        usersTest.add(new User("User2"));
        usersTest.add(new User("User3"));

        gameManager = new GameManager(usersTest);
        usersTest.get(0).setPersonalGoal(GameData.getPersonalGoalCards().get(0));
        usersTest.get(0).setBookshelf(new Bookshelf(BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/Bookshelf_PersonalGoal_3.json")));
        usersTest.get(1).setPersonalGoal(GameData.getPersonalGoalCards().get(0));
        usersTest.get(1).setBookshelf(new Bookshelf(BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/Bookshelf_PersonalGoal_1.json")));
        usersTest.get(2).setPersonalGoal(GameData.getPersonalGoalCards().get(0));
        usersTest.get(2).setBookshelf(new Bookshelf(BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/Bookshelf_PersonalGoal_2.json")));

        gameManager.endGame();

        assertEquals(gameManager.getWinner(), usersTest.get(1));

        for(User user: usersTest){
            System.out.println(user.getName() + " " + user.getPoints() + " points");
        }

    }

}
