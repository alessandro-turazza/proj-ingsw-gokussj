package it.polimi.ingsw.user_test;


import it.polimi.ingsw.object_card.Color;
import it.polimi.ingsw.object_card.ObjectCard;
import it.polimi.ingsw.user.bookshelf.Bookshelf;
import it.polimi.ingsw.user.bookshelf.CellShelf;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static it.polimi.ingsw.object_card.ObjectCard.convertToColor;
import static org.junit.jupiter.api.Assertions.*;

public class BookshelfTest {
    private static Bookshelf bookshelf;
    ObjectCard defaultObjectCard= new ObjectCard(1, Color.WHITE);

    @BeforeAll
    public static void initBookshelf(){
        bookshelf=new Bookshelf();
    }
    @BeforeEach
    public void reinitBookshelf() {
        bookshelf=new Bookshelf();
    }

    public static Bookshelf readFile(String path) throws Exception {
        //struct file for each line "IdObjectCard;ColorObjectCard;ColumnBookshelf"
        BufferedReader buff = new BufferedReader(new FileReader(path));
        Bookshelf result=new Bookshelf();
        String line = buff.readLine();
        while(line != null){
            String[] params = line.split(";");
            ObjectCard card = new ObjectCard(Integer.parseInt(params[0]), convertToColor(params[1]));
            result.insertBookshelf(card, Integer.parseInt(params[2]));
            line = buff.readLine();
        }
        return result;
    }

    public static CellShelf[][] readBookshelfMatrix(String path) throws IOException, ParseException {
        FileReader fr = new FileReader(path);
        JSONObject obj = (JSONObject) new JSONParser().parse(fr);
        JSONArray list = (JSONArray) obj.get("bookshelf");
        CellShelf[][] matrix = new CellShelf[6][5];
        int i = 0, j = 0;

        for(Object o: list){
            JSONObject ob1 = (JSONObject) o;
            JSONArray row = (JSONArray) ob1.get("row");

            for(Object o2: row){
                String s = o2.toString();

                if(!s.equals("-")){
                    Color color = convertToColor(s);
                    CellShelf cell = new CellShelf(new ObjectCard(1,color));
                    matrix[i][j] = cell;
                }

                j++;
            }

            j = 0;
            i++;
        }

        return matrix;
    }
    @Test
    public void createBookshelfMatrix() throws IOException, ParseException {
        String path = "src/test/TestFiles/BookshelfTest/BookshelfRuleCommonIICross.json";
        bookshelf = new Bookshelf(readBookshelfMatrix(path));

        assertEquals(bookshelf.getBookshelf()[5][0].getObjectCard().getColor(), Color.BLUE);
        assertNull(bookshelf.getBookshelf()[0][0]);
    }


    @Test
    public void bookshelfColumnFull() throws Exception {
        String path="src/test/TestFiles/BookshelfTest/Bookshelf_ColumnFull";
        bookshelf=readFile(path);
        assertThrows(Exception.class,
                () -> bookshelf.insertBookshelf(defaultObjectCard,1));

    }

    @Test
    public void bookshelfColumnWrong1(){

        assertThrows(Exception.class,
                () -> bookshelf.insertBookshelf(defaultObjectCard,-3));

    }

    @Test
    public void bookshelfColumnWrong2(){

        assertThrows(Exception.class,
                () -> bookshelf.insertBookshelf(defaultObjectCard,11));

    }

    @Test
    public void bookshelfNotFull() {
        assertFalse(bookshelf.isFull());
    }

    @Test
    public void bookshelfFull() throws Exception {
        for(int r = 5; r >= 0; r--){
            for(int c = 0; c <= 4; c++){
                bookshelf.insertBookshelf(defaultObjectCard, c);
            }
        }

        assertTrue(bookshelf.isFull());
    }


    @Test
    public void bookshelf3AdjacensesColumn() throws Exception {
        String path="src/test/TestFiles/BookshelfTest/Bookshelf_3AdjacensesColumn";
        bookshelf=readFile(path);
        //assertEquals(bookshelf.checkAdjacences(bookshelf.getBookshelf()[3][1], 3, 1),3);
        assertEquals(bookshelf.numberAdjacenses().get(0),3);

    }

    @Test
    public void bookshel4AdjacensesRow() throws Exception {
        String path="src/test/TestFiles/BookshelfTest/Bookshelf_4AdjacensesRow";
        bookshelf=readFile(path);
        //assertEquals(bookshelf.checkAdjacences(bookshelf.getBookshelf()[5][0], 5, 0),4);
        assertEquals(bookshelf.numberAdjacenses().get(0),4);

    }

    @Test
    public void bookshelf4AdjacensesL() throws Exception {
        String path="src/test/TestFiles/BookshelfTest/Bookshelf_4AdjacensesL";
        bookshelf=readFile(path);
        //assertEquals(bookshelf.checkAdjacences(bookshelf.getBookshelf()[5][0], 5, 0),4);
        assertEquals(bookshelf.numberAdjacenses().get(0),4);

    }

    @Test
    public void bookshelf5AdjacensesFullColor() throws Exception {
        String path="src/test/TestFiles/BookshelfTest/Bookshelf_5AdjacensesFullColor";
        bookshelf=readFile(path);
        assertEquals(bookshelf.checkAdjacences(bookshelf.getBookshelf()[5][2], 5, 2),5);

    }


    @Test
    public void bookshelfAdjacensesDifficult() throws Exception {
        String path="src/test/TestFiles/BookshelfTest/Bookshelf_AdjacensesDifficult";
        bookshelf=readFile(path);
        assertEquals(bookshelf.checkAdjacences(bookshelf.getBookshelf()[2][2], 2, 2),12);

    }

    @Test

    public void bookshelfDeleteMark() throws Exception {
        String path = "src/test/TestFiles/BookshelfTest/Bookshelf_AdjacensesDifficult";
        bookshelf = readFile(path);
        bookshelf.checkAdjacences(bookshelf.getBookshelf()[2][2], 2,2);
        bookshelf.deleteMark();
        assertFalse(bookshelf.getBookshelf()[2][2].isMarked());
    }



}
