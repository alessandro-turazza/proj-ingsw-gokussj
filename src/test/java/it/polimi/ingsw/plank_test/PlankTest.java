package it.polimi.ingsw.plank_test;

import it.polimi.ingsw.game_data.GameData;
import it.polimi.ingsw.object_card.ObjectCard;
import it.polimi.ingsw.plank.CellPlank;
import it.polimi.ingsw.plank.Plank;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;



import static org.junit.jupiter.api.Assertions.*;

public class PlankTest {
    private static Plank plank;
    private final static int MAXUSER=4;
    private final static int MINUSER=2;

    @BeforeAll
    public static void initTest(){plank=new Plank();}
    @BeforeEach
    public void reInit(){plank=new Plank();}

    private int[][] loadFileintoArray(String path) throws IOException {
        BufferedReader buff = new BufferedReader(new FileReader(path));
        int[][] result=new int[plank.getDIM()][plank.getDIM()];
        int row=0;
        int column=0;
        String line = buff.readLine();
        while(line != null){
            String[] params = line.split(";");
            for(String param:params){
                result[row][column]=Integer.parseInt(param);
                column++;
            }
            column=0;
            row++;
            line = buff.readLine();
        }
        return result;
    }

    @Test
    public void plankTest_initializePlank() throws IOException {
        for(int i=MINUSER;i<=MAXUSER;i++){
            int[][] array;
            array=loadFileintoArray("src/test/TestFiles/PlankTest/PlankSetup1");
            plank.initializePlank(array,i);
            CellPlank[][] board;
            board= plank.getBoard();
            for(int row=0;row<plank.getDIM();row++){
                for(int column=0;column<plank.getDIM();column++){
                    if(array[row][column]>i || array[row][column]==0)assertNull(board[row][column]);
                    else {
                        assertNotNull(board[row][column]);
                        assertNull(board[row][column].getObjectCard());
                    }
                }
            }
        }

    }
    @Test
    public void plankTest_fillPlank() throws IOException, ParseException {
        for(int i=MINUSER;i<=MAXUSER;i++){
            int[][] array;
            array=loadFileintoArray("src/test/TestFiles/PlankTest/PlankSetup1");
            plank.initializePlank(array,i);
            GameData.loadObjectCards("src/data/Object_Cards_Data.json");
            plank.initializeCardBag(GameData.getDataObjectCards());
            plank.fillPlank();
            CellPlank[][] board;
            board= plank.getBoard();
            for(int row=0;row<plank.getDIM();row++){
                for(int column=0;column<plank.getDIM();column++){
                    if(array[row][column]>i || array[row][column]==0)assertNull(board[row][column]);
                    else {
                        assertNotNull(board[row][column]);
                        assertNotNull(board[row][column].getObjectCard());
                    }
                }
            }
        }

    }

    @Test
    public void plankTest_dragObjectCard() throws Exception {
        for(int i=MINUSER;i<=MAXUSER;i++){
            int[][] array;
            array=loadFileintoArray("src/test/TestFiles/PlankTest/PlankSetup1");
            plank.initializePlank(array,i);
            GameData.loadObjectCards("src/data/Object_Cards_Data.json");
            plank.initializeCardBag(GameData.getDataObjectCards());
            plank.fillPlank();
            CellPlank[][] board;
            board= plank.getBoard();
            for(int row=0;row<plank.getDIM();row++){
                for(int column=0;column<plank.getDIM();column++){
                    int finalRow = row;
                    int finalColumn = column;
                    if(array[row][column]>i || array[row][column]==0) {

                        assertThrows(Exception.class,
                                () -> plank.dragObjectCard(finalRow, finalColumn));
                    }
                    else if(board[row][column].getPlayable()){
                        assertNotNull(board[row][column]);
                        assertNotNull(board[row][column].getObjectCard());
                        ObjectCard precard=board[row][column].getObjectCard();
                        ObjectCard postcard=plank.dragObjectCard(row, column);
                        assertEquals(postcard,precard);
                    }
                    else{
                        assertThrows(Exception.class,
                                () -> plank.dragObjectCard(finalRow, finalColumn));
                    }
                }
            }
        }
    }
    @Test
    public void plankTest_checkPlayable() throws Exception {
        int[][] array;
        array=loadFileintoArray("src/test/TestFiles/PlankTest/PlankTest_checkPlayable");
        plank.initializePlank(array,2);
        GameData.loadObjectCards("src/data/Object_Cards_Data.json");
        plank.initializeCardBag(GameData.getDataObjectCards());
        plank.fillPlank();
        CellPlank[][] board= plank.getBoard();
        for(int r=0;r<plank.getDIM();r++){
            for(int c=0;c<plank.getDIM();c++){
                if(board[r][c]!=null && board[r][c].getObjectCard()!=null){
                    if(r==4 && c==4)assertFalse(board[r][c].getPlayable());
                    else if(r==2 && c==2)assertFalse(board[r][c].getPlayable());
                    else if(r==2 && c==6)assertFalse(board[r][c].getPlayable());
                    else if(r==6 && c==2)assertFalse(board[r][c].getPlayable());
                    else if(r==6 && c==6)assertFalse(board[r][c].getPlayable());
                    else assertTrue(board[r][c].getPlayable());
                }
            }
        }
        plank.dragObjectCard(3,4);
        plank.checkPlayable();
        assertTrue(board[4][4].getPlayable());
        plank.dragObjectCard(2,3);
        plank.checkPlayable();
        assertTrue(board[2][2].getPlayable());
        plank.dragObjectCard(3,6);
        plank.checkPlayable();
        assertTrue(board[2][6].getPlayable());
        plank.dragObjectCard(6,1);
        plank.checkPlayable();
        assertTrue(board[6][2].getPlayable());
        plank.dragObjectCard(7,7);
        plank.checkPlayable();
        assertFalse(board[6][6].getPlayable());
        plank.dragObjectCard(6,7);
        plank.checkPlayable();
        assertTrue(board[6][6].getPlayable());
    }

    @Test
    public void plankTest_checkReFull() throws Exception {
        int[][] array;
        array=loadFileintoArray("src/test/TestFiles/PlankTest/PlankTest_checkPlayable");
        plank.initializePlank(array,2);
        GameData.loadObjectCards("src/data/Object_Cards_Data.json");
        plank.initializeCardBag(GameData.getDataObjectCards());
        plank.fillPlank();
        assertFalse(plank.checkRefull());
        plank.dragObjectCard(7,7);
        plank.checkPlayable();
        assertTrue(plank.checkRefull());
    }
}
