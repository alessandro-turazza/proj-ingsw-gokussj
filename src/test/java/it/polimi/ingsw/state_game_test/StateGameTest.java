package it.polimi.ingsw.state_game_test;

import it.polimi.ingsw.server.game_data.GameData;
import it.polimi.ingsw.server.model.common_goal.CommonGoal;
import it.polimi.ingsw.server.model.game_manager.GameManager;
import it.polimi.ingsw.server.model.plank.CellPlank;
import it.polimi.ingsw.server.model.user.User;
import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;
import it.polimi.ingsw.server.model.user.bookshelf.CellShelf;
import it.polimi.ingsw.server.state_game.CommonGoalClone;
import it.polimi.ingsw.server.state_game.StateGame;

import it.polimi.ingsw.user_test.BookshelfTest;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StateGameTest {
    public static StateGame stateGame;
    public static GameManager gameManager;

    @BeforeAll
    public static void init() throws IOException, ParseException {
        GameData.loadObjectCards("src/data/Object_Cards_Data.json");
        GameData.loadPersonalGoals("src/data/PersonalGoals_Data.json");
        GameData.loadPlankConfig("src/data/Plank_Config_1.json");
        GameData.loadTokens("src/data/Tokens_Data.json");
        GameData.loadIdCommonGoals("src/data/Common_Goals_Setup.json");
        GameData.loadRuleCommons();

        ArrayList<User> usersTest = new ArrayList<>();
        usersTest.add(new User("User1"));
        usersTest.add(new User("User2"));
        usersTest.get(0).setPersonalGoal(GameData.getPersonalGoalCards().get(0));
        usersTest.get(0).setBookshelf(new Bookshelf(BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/Bookshelf_PersonalGoal_3.json")));
        usersTest.get(1).setPersonalGoal(GameData.getPersonalGoalCards().get(0));
        usersTest.get(1).setBookshelf(new Bookshelf(BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/Bookshelf_PersonalGoal_1.json")));
        gameManager = new GameManager(usersTest);

        gameManager.startGame();

        stateGame=new StateGame(gameManager);
    }
    @BeforeEach
    public void reinit() throws IOException, ParseException {
        init();
    }
    @Test
    public void testStateGame() {
        User user= gameManager.getUsers().get(0);
        User userClone=stateGame.getUsersClone().get(0);
        assertEquals(user.getName(),userClone.getName());
        assertEquals(user.getPoints(),userClone.getPoints());
        for(int i=0;i<user.getPointsToken().size();i++)
            assertEquals(user.getPointsToken(i),userClone.getPointsToken(i));
        assertEquals(user.getPersonalGoal().getId(),userClone.getPersonalGoal().getId());
        for(int i=0;i<user.getPersonalGoal().getCostraints().size();i++){
            assertEquals(user.getPersonalGoal().getCostraints().get(i).getColor(),userClone.getPersonalGoal().getCostraints().get(i).getColor());
            assertEquals(user.getPersonalGoal().getCostraints().get(i).getRow(),userClone.getPersonalGoal().getCostraints().get(i).getRow());
            assertEquals(user.getPersonalGoal().getCostraints().get(i).getColumn(),userClone.getPersonalGoal().getCostraints().get(i).getColumn());
        }
        CellShelf[][] cellShelves=user.getBookshelf().getBookshelf();
        CellShelf[][] cellShelvesClone=userClone.getBookshelf().getBookshelf();
        for(int i = 0; i < user.getBookshelf().getNumRow(); i++){
            for(int j = 0; j < user.getBookshelf().getNumColumn(); j++){
                if(cellShelves[i][j] != null){
                    assertEquals(cellShelves[i][j].getObjectCard().getColor(),cellShelvesClone[i][j].getObjectCard().getColor());
                    assertEquals(cellShelves[i][j].getObjectCard().getId(),cellShelvesClone[i][j].getObjectCard().getId());
                    if(cellShelves[i][j].isMarked())assertTrue(cellShelvesClone[i][j].isMarked());
                    else assertFalse(cellShelvesClone[i][j].isMarked());
                }
                else assertNull(cellShelvesClone[i][j]);
            }
        }
        user= gameManager.getUsers().get(1);
        userClone=stateGame.getUsersClone().get(1);
        assertEquals(user.getPoints(),userClone.getPoints());
        for(int i=0;i<user.getPointsToken().size();i++)
            assertEquals(user.getPointsToken(i),userClone.getPointsToken(i));
        assertEquals(user.getPersonalGoal().getId(),userClone.getPersonalGoal().getId());
        for(int i=0;i<user.getPersonalGoal().getCostraints().size();i++){
            assertEquals(user.getPersonalGoal().getCostraints().get(i).getColor(),userClone.getPersonalGoal().getCostraints().get(i).getColor());
            assertEquals(user.getPersonalGoal().getCostraints().get(i).getRow(),userClone.getPersonalGoal().getCostraints().get(i).getRow());
            assertEquals(user.getPersonalGoal().getCostraints().get(i).getColumn(),userClone.getPersonalGoal().getCostraints().get(i).getColumn());
        }
        cellShelves=user.getBookshelf().getBookshelf();
        cellShelvesClone=userClone.getBookshelf().getBookshelf();
        for(int i = 0; i < user.getBookshelf().getNumRow(); i++){
            for(int j = 0; j < user.getBookshelf().getNumColumn(); j++){
                if(cellShelves[i][j] != null){
                    assertEquals(cellShelves[i][j].getObjectCard().getColor(),cellShelvesClone[i][j].getObjectCard().getColor());
                    assertEquals(cellShelves[i][j].getObjectCard().getId(),cellShelvesClone[i][j].getObjectCard().getId());
                    if(cellShelves[i][j].isMarked())assertTrue(cellShelvesClone[i][j].isMarked());
                    else assertFalse(cellShelvesClone[i][j].isMarked());
                }
                else assertNull(cellShelvesClone[i][j]);
            }
        }
        assertEquals(stateGame.getActiveUser(),gameManager.getTurnManager().getUsers().activeUser().getName());


        CellPlank[][] cellPlanksClone=stateGame.getPlankClone().getBoard();
        CellPlank[][] cellPlanks=gameManager.getPlank().getBoard();
        for(int r=0;r< gameManager.getPlank().getDIM();r++)
            for(int c=0;c<gameManager.getPlank().getDIM();c++){
                if(cellPlanks[r][c]!=null){
                    CellPlank cellPlank=cellPlanks[r][c];
                    CellPlank cellPlankClone=cellPlanksClone[r][c];
                    assertEquals(cellPlank.getColumn(),cellPlankClone.getColumn());
                    assertEquals(cellPlank.getRow(),cellPlankClone.getRow());
                    assertEquals(cellPlank.getObjectCard().getColor(),cellPlankClone.getObjectCard().getColor());
                    assertEquals(cellPlank.getObjectCard().getId(),cellPlankClone.getObjectCard().getId());
                    assertEquals(cellPlank.getPlayable(),cellPlankClone.getPlayable());
                }
                else assertNull(cellPlanksClone[r][c]);
            }
        assertEquals(gameManager.getCommonGoals().size(),stateGame.getCommonGoalsClone().size());
        for(int i=0; i<gameManager.getCommonGoals().size();i++){
            CommonGoal commonGoal=gameManager.getCommonGoals().get(i);
            CommonGoalClone commonGoalClone=stateGame.getCommonGoalsClone().get(i);
            assertEquals(commonGoal.getId(),commonGoalClone.getId());
            assertEquals(commonGoal.getRule().getIdRule(),commonGoalClone.getIdRule());

            assertEquals(commonGoal.getTokenCards().size(),commonGoalClone.getTokens().size());

            for(int j=0;j<commonGoal.getTokenCards().size();j++){
                assertEquals(commonGoal.getTokenCards().get(j).getPoints(),commonGoalClone.getTokens().get(j).getPoints());
                assertEquals(commonGoal.getTokenCards().get(j).getSeries(),commonGoalClone.getTokens().get(j).getSeries());
            }
        }


    }


}
