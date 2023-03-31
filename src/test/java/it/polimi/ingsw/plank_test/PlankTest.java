package it.polimi.ingsw.plank_test;

import it.polimi.ingsw.object_card.ObjectCard;
import it.polimi.ingsw.plank.CellPlank;
import it.polimi.ingsw.plank.Plank;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;


import static it.polimi.ingsw.plank_test.CardBagTest.insertToFileObjectCard;

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
            array=loadFileintoArray("src/test/TestFiles/PlankTest/PlankSetup/PlankSetup1");
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
    public void plankTest_fillPlank() throws IOException {
        for(int i=MINUSER;i<=MAXUSER;i++){
            int[][] array;
            array=loadFileintoArray("src/test/TestFiles/PlankTest/PlankSetup/PlankSetup1");
            plank.initializePlank(array,i);
            plank.initializeCardBag(insertToFileObjectCard("src/test/TestFiles/CardBagTest/CardBag_FileTest"));
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
            array=loadFileintoArray("src/test/TestFiles/PlankTest/PlankSetup/PlankSetup1");
            plank.initializePlank(array,i);
            plank.initializeCardBag(insertToFileObjectCard("src/test/TestFiles/CardBagTest/CardBag_FileTest"));
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

}
