package it.polimi.ingsw;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static it.polimi.ingsw.ObjectCard.convertToColor;
import static org.junit.jupiter.api.Assertions.*;

public class BookshelfTest {
    private static Bookshelf bookshelf;
    private static BufferedReader buff;
    ObjectCard defaultObjectCard= new ObjectCard(1,Color.WHITE);

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
        buff = new BufferedReader(new FileReader(path));
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

    public Bookshelf readJson(String path) throws Exception {
        FileReader fr = new FileReader(path);
        JSONObject obj = (JSONObject) new JSONParser().parse(fr);
        JSONArray list = (JSONArray) obj.get("cardList");

        for(int i = 0; i < list.size(); i++){
            JSONObject cardObj = (JSONObject) list.get(i);
            ObjectCard card = new ObjectCard(Integer.parseInt(cardObj.get("id").toString()), convertToColor(cardObj.get("color").toString()));
            bookshelf.insertBookshelf(card, Integer.parseInt(cardObj.get("column").toString()) );
        }

        return bookshelf;
    }

    @Test
    public void testJSON() throws Exception {
        String path="src/test/TestFiles/BookshelfTest/Bookshelf_Config_1.json";
        bookshelf=readJson(path);
        assertEquals(bookshelf.checkAdjacences(bookshelf.getBookshelf()[5][2], 5, 2),5);

    }


    @Test
    public void bookshelfColumnFull() throws Exception {
        String path="src/test/TestFiles/BookshelfTest/Bookshelf_ColumnFull";
        bookshelf=readFile(path);
        assertThrows(Exception.class,
                () -> {
                    bookshelf.insertBookshelf(defaultObjectCard,1);
                });

    }

    @Test
    public void bookshelfColumnWrong1(){

        assertThrows(Exception.class,
                () -> {
                    bookshelf.insertBookshelf(defaultObjectCard,-3);
                });

    }

    @Test
    public void bookshelfColumnWrong2(){

        assertThrows(Exception.class,
                () -> {
                    bookshelf.insertBookshelf(defaultObjectCard,11);
                });

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
