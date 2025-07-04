package it.polimi.ingsw.turn_manager_test;

import it.polimi.ingsw.server.model.common_goal.CommonGoal;
import it.polimi.ingsw.server.model.common_goal.rule_common.RuleCommon;
import it.polimi.ingsw.server.game_data.GameData;
import it.polimi.ingsw.server.model.game_manager.TurnManager;
import it.polimi.ingsw.server.model.object_card.Color;
import it.polimi.ingsw.server.model.object_card.ObjectCard;
import it.polimi.ingsw.server.model.plank.CellPlank;
import it.polimi.ingsw.server.model.plank.Plank;
import it.polimi.ingsw.server.model.user.User;
import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;
import it.polimi.ingsw.server.model.user.bookshelf.CellShelf;
import it.polimi.ingsw.user_test.BookshelfTest;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static it.polimi.ingsw.turn_manager_test.TurnUserTest.loadUser;
import static org.junit.jupiter.api.Assertions.*;

public class TurnManagerTest {
    private static TurnManager turnManager;
    private static ObjectCard  objectCard;
    @BeforeAll
    public static void init() throws IOException, ParseException {
        GameData.loadPlankConfig("src/test/TestFiles/PlankTest/PlankTest_checkPlayable.json");
        GameData.loadObjectCards("src/data/Object_Cards_Data.json");
        ArrayList<User> users=loadUser();
        Plank plank=new Plank();
        plank.initializePlank(GameData.getPlank_config(),users.size());
        plank.initializeCardBag(GameData.getDataObjectCards());
        plank.fillPlank();
        GameData.loadTokens("src/data/Tokens_Data.json");
        ArrayList<Integer> tokenCards=GameData.getDataTokens().get(users.size()-2);
        ArrayList<CommonGoal> commonGoals=new ArrayList<>();
        GameData.loadRuleCommons();
        ArrayList<RuleCommon> ruleCommons=GameData.getRuleCommons();
        int i=1;
        for(RuleCommon ruleCommon:ruleCommons){
            commonGoals.add(new CommonGoal(i,ruleCommon));
            commonGoals.get(i-1).setTokenCardsInteger(tokenCards,i);
            i++;
        }
        turnManager=new TurnManager(users,plank,commonGoals);
        objectCard=new ObjectCard(1, Color.WHITE);
    }
    @BeforeEach
    public void reInit() throws IOException, ParseException {
        init();
    }
    @Test
    public void turnManagerTest_checkDragColumn(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        //create arrayList playable
        for(int i=0;i<3;i++){
            CellPlank cellPlank=new CellPlank(null,i,0);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        assertTrue(turnManager.checkDrag(cellPlanks));
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
        assertTrue(turnManager.checkDrag(cellPlanks));
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
        assertTrue(turnManager.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDragNotAdjacense(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i<3;i++){
            CellPlank cellPlank=new CellPlank(null,0,i*2);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        assertFalse(turnManager.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDragNotAdjacense2(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i<3;i++){
            CellPlank cellPlank=new CellPlank(null,i*2,0);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        assertFalse(turnManager.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDragOneCell(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        cellPlanks.add(new CellPlank(null,0,0));
        cellPlanks.get(0).setPlayable(true);
        boolean result=turnManager.checkDrag(cellPlanks);
        assertTrue(result);
    }
    @Test
    public void turnManagerTest_checkDragTwoCell(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        cellPlanks.add(new CellPlank(null,4,4));
        cellPlanks.get(0).setPlayable(true);
        cellPlanks.add(new CellPlank(null,5,4));
        cellPlanks.get(1).setPlayable(true);
        boolean result=turnManager.checkDrag(cellPlanks);
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
        assertTrue(turnManager.checkDrag(cellPlanks));
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
        assertTrue(turnManager.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDrop(){
        for(int i=0;i<turnManager.getUsers().activeUser().getBookshelf().getNumColumn();i++)
            assertTrue(turnManager.checkDrop(3,i));
    }
    @Test
    public void turnManagerTest_checkDrop2() throws Exception {
        Bookshelf bookshelf=turnManager.getUsers().activeUser().getBookshelf();
        for(int column=0;column<bookshelf.getNumColumn();column++)
            for(int row=0;row<bookshelf.getNumRow()-2;row++)
                bookshelf.insertBookshelf(objectCard,column);

        for(int column=0;column<bookshelf.getNumColumn();column++)
            assertFalse(turnManager.checkDrop(3,column));
    }
    @Test
    public void turnManagerTest_updateGame() {
        Bookshelf bookshelf=turnManager.getUsers().activeUser().getBookshelf();
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        cellPlanks.add(new CellPlank(objectCard,0,0));
        assertThrows(Exception.class,
                () -> turnManager.updateGame(cellPlanks,0));//first if
        cellPlanks.get(0).setPlayable(true);
        for(int row=0;row<bookshelf.getNumColumn();row++){
            cellPlanks.add(new CellPlank(objectCard,0,0));
            cellPlanks.get(row+1).setPlayable(true);
        }
        assertThrows(Exception.class,
                () -> turnManager.updateGame(cellPlanks,0));//second if
    }
    @Test
    public void simulateGame() throws Exception {
        //1 player start
        ArrayList<CellPlank> deck=new ArrayList<>();
        deck.add(turnManager.getPlank().getBoard()[2][1]);
        Color color=deck.get(0).getObjectCard().getColor();
        int id=deck.get(0).getObjectCard().getId();
        User user=turnManager.getUsers().activeUser();
        turnManager.updateGame(deck,0);
        assertEquals(user.getBookshelf().getObjectCard(user.getBookshelf().getNumRow()-1,0).getColor(),color);
        assertEquals(user.getBookshelf().getObjectCard(user.getBookshelf().getNumRow()-1,0).getId(),id);
        //2 player start
        deck=new ArrayList<>();
        int[] ids=new int[3];
        Color[] colors=new Color[3];
        deck.add((turnManager.getPlank().getBoard()[1][2]));
        colors[0]=deck.get(0).getObjectCard().getColor();
        ids[0]=deck.get(0).getObjectCard().getId();
        deck.add((turnManager.getPlank().getBoard()[2][2]));
        colors[1]=deck.get(1).getObjectCard().getColor();
        ids[1]=deck.get(1).getObjectCard().getId();
        deck.add((turnManager.getPlank().getBoard()[3][2]));
        colors[2]=deck.get(2).getObjectCard().getColor();
        ids[2]=deck.get(2).getObjectCard().getId();
        user=turnManager.getUsers().activeUser();
        turnManager.updateGame(deck,0);

        assertEquals(user.getBookshelf().getObjectCard(user.getBookshelf().getNumRow()-1,0).getColor(),colors[0]);
        assertEquals(user.getBookshelf().getObjectCard(user.getBookshelf().getNumRow()-1,0).getId(),ids[0]);

        assertEquals(user.getBookshelf().getObjectCard(user.getBookshelf().getNumRow()-2,0).getColor(),colors[1]);
        assertEquals(user.getBookshelf().getObjectCard(user.getBookshelf().getNumRow()-2,0).getId(),ids[1]);

        assertEquals(user.getBookshelf().getObjectCard(user.getBookshelf().getNumRow()-3,0).getColor(),colors[2]);
        assertEquals(user.getBookshelf().getObjectCard(user.getBookshelf().getNumRow()-3,0).getId(),ids[2]);
        //3 player start
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[2][3]));
        turnManager.updateGame(deck,0);
        //4 player start
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[0][4]));
        turnManager.updateGame(deck,0);
        //1 player start
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[2][5]));
        turnManager.updateGame(deck,0);
        //2 player start
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[1][6]));
        deck.add((turnManager.getPlank().getBoard()[2][6]));
        deck.add((turnManager.getPlank().getBoard()[3][6]));
        turnManager.updateGame(deck,0);
        //3 player start
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[4][0]));
        turnManager.updateGame(deck,0);
        //4 player start
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[8][4]));
        turnManager.updateGame(deck,0);
        //1 player start
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[4][8]));
        turnManager.updateGame(deck,0);
        //2 player start but column is full
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[7][2]));
        ArrayList<CellPlank> finalDeck = deck;
        assertThrows(Exception.class,
                () -> turnManager.updateGame(finalDeck,0));
        //2 player start but cell playable is false
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[6][6]));
        ArrayList<CellPlank> finalDeck1 = deck;
        assertThrows(Exception.class,
                () -> turnManager.updateGame(finalDeck1,1));
        //2 player start
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[7][7]));
        turnManager.updateGame(deck,1);
        //3 player start
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[7][7]));//verificate plank refull
        turnManager.updateGame(deck,1);
    }
    @Test
    public void simulateGame2() throws Exception {
        Bookshelf bookshelfFirstUser=turnManager.getUsers().activeUser().getBookshelf();
        ArrayList<CellPlank> deck=new ArrayList<>();
        //only one cellshelf empty
        for(int column=0;column<bookshelfFirstUser.getNumColumn();column++){
            for(int row=0;row<bookshelfFirstUser.getNumRow()-1;row++){
                bookshelfFirstUser.insertBookshelf(objectCard,column);
            }
        }
        for(int column=0;column<bookshelfFirstUser.getNumColumn()-1;column++)
            bookshelfFirstUser.insertBookshelf(objectCard,column);
        //1 player start
        deck.add((turnManager.getPlank().getBoard()[8][4]));
        assertNotNull(turnManager.updateGame(deck,bookshelfFirstUser.getNumColumn()-1));//bookshelf is full
        //2 player start
        deck=new ArrayList<>();
        deck.add(turnManager.getPlank().getBoard()[2][1]);
        assertNotNull(turnManager.updateGame(deck,0));
        //3 player start
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[2][3]));
        assertNotNull(turnManager.updateGame(deck,0));
        //4 player start
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[0][4]));
        assertNull(turnManager.updateGame(deck,0));//verificate end game
    }
    @Test
    public void simulateUpdateGameCommonGoal() throws Exception {
        CellShelf[][] cellPlanks= BookshelfTest.readBookshelfMatrix("src/test/TestFiles/TurnManagerTest/TurnManagerTestCommonGoal.json");
        Bookshelf bookshelfFirstPlayer=new Bookshelf(cellPlanks);
        ArrayList<CellPlank> deck=new ArrayList<>();
        turnManager.getUsers().activeUser().setBookshelf(bookshelfFirstPlayer);
        int point= turnManager.getCommonGoals().get(11).getTokenCards().get(0).getPoints();
        assertEquals(turnManager.getUsers().activeUser().getPointsToken(11),0);
        //1 player start
        deck.add((turnManager.getPlank().getBoard()[8][4]));
        User firstUser=turnManager.getUsers().activeUser();
        turnManager.updateGame(deck, 4);//complete commonGoal
        assertEquals(firstUser.getPointsToken(11),point);
        //2 player start
        deck=new ArrayList<>();
        deck.add(turnManager.getPlank().getBoard()[2][1]);
        ObjectCard objectCard=new ObjectCard(1,deck.get(0).getObjectCard().getColor());
        for(int row=0;row<bookshelfFirstPlayer.getNumRow();row++){
            turnManager.getUsers().activeUser().getBookshelf().insertBookshelf(objectCard,0);
        }
        for(int row=0;row<bookshelfFirstPlayer.getNumRow()-1;row++){
            turnManager.getUsers().activeUser().getBookshelf().insertBookshelf(objectCard,bookshelfFirstPlayer.getNumColumn()-1);
        }
        point= turnManager.getCommonGoals().get(4).getTokenCards().get(0).getPoints();
        assertEquals(turnManager.getUsers().activeUser().getPointsToken(4),0);
        User secondUser=turnManager.getUsers().activeUser();
        turnManager.updateGame(deck,bookshelfFirstPlayer.getNumColumn()-1);//complete commonGoal
        assertEquals(secondUser.getPointsToken(4),point);
        //3 player start
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[2][3]));
        assertNotNull(turnManager.updateGame(deck,0));
        //4 player start
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[0][4]));
        assertNotNull(turnManager.updateGame(deck,0));
        //1 player start
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[4][0]));
        assertNotNull(turnManager.updateGame(deck,1));
        //2 player start
        deck=new ArrayList<>();
        deck.add((turnManager.getPlank().getBoard()[4][8]));
        assertNotNull(turnManager.updateGame(deck,2));//recomplete commonGoal
        assertEquals(secondUser.getPointsToken(4),point);
    }
}
