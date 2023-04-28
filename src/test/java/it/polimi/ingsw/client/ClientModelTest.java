package it.polimi.ingsw.client;
import it.polimi.ingsw.client.model.ClientModel;
import it.polimi.ingsw.server.game_data.GameData;
import it.polimi.ingsw.server.model.common_goal.CommonGoal;
import it.polimi.ingsw.server.model.common_goal.TokenCard;
import it.polimi.ingsw.server.model.common_goal.rule_common.RuleCommonI;
import it.polimi.ingsw.server.model.game_manager.GameManager;
import it.polimi.ingsw.server.model.object_card.Color;
import it.polimi.ingsw.server.model.object_card.ObjectCard;
import it.polimi.ingsw.server.model.plank.CellPlank;
import it.polimi.ingsw.server.model.plank.Plank;
import it.polimi.ingsw.server.model.user.User;
import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;
import it.polimi.ingsw.server.state_game.CommonGoalClone;
import it.polimi.ingsw.server.state_game.StateGame;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static it.polimi.ingsw.turn_manager_test.TurnUserTest.loadUser;
import static org.junit.jupiter.api.Assertions.*;

public class ClientModelTest {
    public static ClientModel clientModel;
    private static ObjectCard objectCard;

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
        usersTest.get(1).setPersonalGoal(GameData.getPersonalGoalCards().get(0));
        GameManager gameManager = new GameManager(usersTest);

        gameManager.startGame();

        StateGame stateGame=new StateGame(gameManager);

        clientModel=new ClientModel(stateGame,"User1", null);
        objectCard=new ObjectCard(1, Color.WHITE);
    }

    @BeforeEach
    public void reinit() throws IOException, ParseException {
        init();
    }

    public void setPlankConfig1() throws IOException, ParseException {
        GameData.loadPlankConfig("src/test/TestFiles/PlankTest/PlankTest_checkPlayable.json");
        GameData.loadObjectCards("src/data/Object_Cards_Data.json");
        ArrayList<User> users=loadUser();
        Plank plank=new Plank();
        plank.initializePlank(GameData.getPlank_config(),users.size());
        plank.initializeCardBag(GameData.getDataObjectCards());
        plank.fillPlank();
        clientModel.setPlank(plank);
        clientModel.setPlayers(users);
    }

    @Test
    public void turnManagerTest_checkDragColumn(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i<3;i++){
            CellPlank cellPlank=new CellPlank(null,i,0);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        assertTrue(clientModel.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDrag2Column(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i<3;i++){
            CellPlank cellPlank=new CellPlank(null,i,0);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        CellPlank temp=cellPlanks.get(1);
        cellPlanks.set(1,cellPlanks.get(0));
        cellPlanks.set(0,temp);
        assertTrue(clientModel.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDragRow(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i<3;i++){
            CellPlank cellPlank=new CellPlank(null,0,i);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        CellPlank temp=cellPlanks.get(1);
        cellPlanks.set(1,cellPlanks.get(0));
        cellPlanks.set(0,temp);
        assertTrue(clientModel.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDragNotAdjacense(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i<3;i++){
            CellPlank cellPlank=new CellPlank(null,0,i*2);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        assertFalse(clientModel.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDragNotAdjacense2(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i<3;i++){
            CellPlank cellPlank=new CellPlank(null,i*2,0);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        assertFalse(clientModel.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDragOneCell(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        cellPlanks.add(new CellPlank(null,0,0));
        cellPlanks.get(0).setPlayable(true);
        boolean result=clientModel.checkDrag(cellPlanks);
        assertTrue(result);
    }
    @Test
    public void turnManagerTest_checkDragTwoCell(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        cellPlanks.add(new CellPlank(null,4,4));
        cellPlanks.get(0).setPlayable(true);
        cellPlanks.add(new CellPlank(null,5,4));
        cellPlanks.get(1).setPlayable(true);
        boolean result=clientModel.checkDrag(cellPlanks);
        assertTrue(result);
    }
    @Test
    public void turnManagerTest_checkDragDifficultColumn(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i<15;i++){
            CellPlank cellPlank=new CellPlank(null,i,0);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        Collections.shuffle(cellPlanks);
        assertTrue(clientModel.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDragDifficultRow(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i<15;i++){
            CellPlank cellPlank=new CellPlank(null,0,i);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        Collections.shuffle(cellPlanks);
        assertTrue(clientModel.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDrop() throws Exception {
        for(int i=0;i<clientModel.getMyBookshelf().getNumColumn();i++)assertTrue(clientModel.checkDrop(3,i));
    }
    @Test
    public void turnManagerTest_checkDrop2() throws Exception {
        Bookshelf bookshelf=clientModel.getMyBookshelf();
        for(int column=0;column<bookshelf.getNumColumn();column++)
            for(int row=0;row<bookshelf.getNumRow()-2;row++)
                bookshelf.insertBookshelf(objectCard,column);

        for(int column=0;column<bookshelf.getNumColumn();column++)
            assertFalse(clientModel.checkDrop(3,column));
    }
    //fine test uguali a turn manager
    @Test
    public void clientModel_checDrop(){
        clientModel.setMyName("luca");
        assertThrows(Exception.class,
                () -> clientModel.checkDrop(3,2));
    }
    @Test
    public void clientModel_getAdjacentCell() throws Exception {
        setPlankConfig1();
        assertThrows(Exception.class,
                () -> clientModel.getCellPlank(clientModel.getPlank().getDIM(),clientModel.getPlank().getDIM()));
        assertThrows(Exception.class,
                () -> clientModel.getAdjacentCell(clientModel.getPlank().getBoard()[clientModel.getPlank().getDIM()][clientModel.getPlank().getDIM()]));
        //first test
        ArrayList<CellPlank> cellPlanksTest=clientModel.getAdjacentCell(clientModel.getPlank().getBoard()[2][2]);
        ArrayList<CellPlank> cellPlanks= new ArrayList<>();
        cellPlanks.add(clientModel.getCellPlank(3,2));
        cellPlanks.add(clientModel.getCellPlank(2,3));
        cellPlanks.add(clientModel.getCellPlank(1,2));
        cellPlanks.add(clientModel.getCellPlank(2,1));
        for(int i=0;i<4;i++)assertEquals(cellPlanks.get(i),cellPlanksTest.get(i));
        //second test
        cellPlanksTest=clientModel.getAdjacentCell(clientModel.getPlank().getBoard()[0][4]);
        for(int i=0;i<4;i++)assertNull(cellPlanksTest.get(i));
        //third test
        cellPlanksTest=clientModel.getAdjacentCell(clientModel.getPlank().getBoard()[7][7]);
        cellPlanks= new ArrayList<>();
        cellPlanks.add(null);
        cellPlanks.add(null);
        cellPlanks.add(clientModel.getCellPlank(6,7));
        cellPlanks.add(clientModel.getCellPlank(7,6));
        for(int i=0;i<4;i++)assertEquals(cellPlanks.get(i),cellPlanksTest.get(i));
    }
    @Test
    public void clientModel_getPositionPlayablePlank() throws IOException, ParseException {
        setPlankConfig1();
        Boolean[][] playable=new Boolean[clientModel.getPlank().getDIM()][clientModel.getPlank().getDIM()];
        for(int r=0;r<clientModel.getPlank().getDIM();r++)
            for(int c=0;c<clientModel.getPlank().getDIM();c++)playable[r][c]=false;
        playable[0][4]=true;

        playable[1][2]=true;
        playable[2][1]=true;
        playable[3][2]=true;
        playable[2][3]=true;

        playable[1][6]=true;
        playable[2][5]=true;
        playable[3][6]=true;
        playable[2][7]=true;

        playable[4][0]=true;
        playable[4][8]=true;

        playable[4][5]=true;
        playable[5][4]=true;
        playable[4][3]=true;
        playable[3][4]=true;

        playable[6][1]=true;
        playable[5][2]=true;
        playable[7][2]=true;
        playable[6][3]=true;

        playable[5][6]=true;
        playable[6][5]=true;
        playable[7][6]=true;
        playable[6][7]=true;

        playable[7][7]=true;
        playable[8][4]=true;

        Boolean[][] test= clientModel.getPositionPlayablePlank();

        for(int r=0;r<clientModel.getPlank().getDIM();r++)
            for(int c=0;c<clientModel.getPlank().getDIM();c++)
                assertEquals(test[r][c],playable[r][c]);

    }

    @Test
    public void clientModel_getMyBookshelf(){
        clientModel.setMyName("pippo");
        assertThrows(Exception.class,
                () -> clientModel.getMyBookshelf());
    }
    @Test
    public void clientModel_getBookshelf(){
        assertThrows(Exception.class,
                () -> clientModel.getBookshelf("pippo"));
    }
    @Test
    public void clientModel_getLastTokenCardCommonGoal1() throws Exception {
        assertThrows(Exception.class,
                () -> clientModel.getLastTokenCardCommonGoal(42));
        assertEquals(clientModel.getLastTokenCardCommonGoal(1).getPoints(),clientModel.getCommonGoals().get(0).getLastTokenCard().getPoints());
    }

    @Test
    public void clientModel_getCommonGoal(){
        assertThrows(Exception.class,
                () -> clientModel.getCommonGoal(42));
        ArrayList<CommonGoalClone> commonGoals=new ArrayList<>();
        ArrayList<TokenCard> tokenCards=new ArrayList<>();
        commonGoals.add(new CommonGoalClone(new CommonGoal(1,new RuleCommonI(),tokenCards)));
        clientModel.setCommonGoals(commonGoals);
        assertEquals(clientModel.getCommonGoals(),commonGoals);
    }
    @Test
    public void clientModel_getPoints(){
        ArrayList<Integer> testPoints=clientModel.getPoints();
        for(Integer point:testPoints)assertEquals(point,0);

    }
    @Test
    public void clientModel_TestSetMethod(){
        clientModel=new ClientModel();
        clientModel.setIdGame(1);
        ArrayList<User> players=new ArrayList<>();
        players.add(new User("user1"));
        players.add(new User("user2"));
        players.add(new User("user3"));
        players.add(new User("user4"));
        clientModel.setPlayers(players);
        clientModel.setMyName(players.get(0).getName());
        clientModel.setActiveUser(players.get(0).getName());
        assertEquals(clientModel.getIdGame(),1);
        assertEquals(clientModel.getActiveUser(),players.get(0).getName());
        assertEquals(clientModel.getMyName(),players.get(0).getName());
        ArrayList<String> usernames=new ArrayList<>();
        for(User user:players)usernames.add(user.getName());
        assertEquals(clientModel.getPlayers(),players);
        assertEquals(clientModel.getUsernames(),usernames);
    }
}
