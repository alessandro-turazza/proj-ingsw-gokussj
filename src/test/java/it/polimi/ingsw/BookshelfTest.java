package it.polimi.ingsw;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class BookshelfTest {
    private static Bookshelf bookshelf;
    private static BufferedReader buff;
    ObjectCard defaultObjectCard= new ObjectCard(1,Color.WHITE);

    @BeforeAll
    public static void initBookshelf()  throws IOException{
        bookshelf=new Bookshelf();
    }

    @BeforeEach
    public void reInitBookshelf(){ bookshelf=new Bookshelf();}

    private Color convertToColor(String s){
        if(s.equals("YELLOW"))
            return Color.YELLOW;
        if(s.equals("BLUE"))
            return Color.BLUE;
        if(s.equals("PINK"))
            return Color.PINK;
        if(s.equals("LIGHT_BLUE"))
            return Color.LIGHT_BLUE;
        if(s.equals("GREEN"))
            return Color.GREEN;
        if(s.equals("WHITE"))
            return Color.WHITE;
        return null;

    }

    @Test
    public void bookshelf1Column() throws Exception {
        buff = new BufferedReader(new FileReader("src/test/TestFiles/Bookshelf_1Column"));
        String line = buff.readLine();

        while(line != null){
            line.trim();
            String[] params = line.split(";");

            ObjectCard card = new ObjectCard(Integer.parseInt(params[0]), convertToColor(params[1]));

            bookshelf.insertBookshelf(card, 1);
            line = buff.readLine();
        }


    }

    @Test
    public void bookshelfColumnFull() throws Exception {
        buff = new BufferedReader(new FileReader("src/test/TestFiles/Bookshelf_ColumnFull"));

        String line = buff.readLine();

        while(line != null){
            line.trim();
            String[] params = line.split(";");

            ObjectCard card = new ObjectCard(Integer.parseInt(params[0]), convertToColor(params[1]));

            if(Integer.parseInt(params[0]) < 7)
                bookshelf.insertBookshelf(card, 1);
            else
                assertThrows(Exception.class,
                        () -> {
                            bookshelf.insertBookshelf(card,1);
                        });

            line = buff.readLine();
        }


    }

    @Test
    public void bookshelfNotFull() throws Exception {
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
        buff = new BufferedReader(new FileReader("src/test/TestFiles/Bookshelf_3AdjacensesColumn"));
        String line = buff.readLine();

        while(line != null){
            line.trim();
            String[] params = line.split(";");

            ObjectCard card = new ObjectCard(Integer.parseInt(params[0]), convertToColor(params[1]));

            bookshelf.insertBookshelf(card, 1);


            line = buff.readLine();
        }

        assertEquals(bookshelf.checkAdjacences(bookshelf.getBookshelf()[3][1], 3, 1),3);

    }

    @Test
    public void bookshel4AdjacensesRow() throws Exception {
        buff = new BufferedReader(new FileReader("src/test/TestFiles/Bookshelf_4AdjacensesRow"));
        String line = buff.readLine();
        int column = 0;

        while(line != null){
            line.trim();
            String[] params = line.split(";");

            ObjectCard card = new ObjectCard(Integer.parseInt(params[0]), convertToColor(params[1]));

            bookshelf.insertBookshelf(card, column);

            column++;

            line = buff.readLine();
        }

        assertEquals(bookshelf.checkAdjacences(bookshelf.getBookshelf()[5][0], 5, 0),4);

    }

    @Test
    public void bookshelf4AdjacensesL() throws Exception {
        buff = new BufferedReader(new FileReader("src/test/TestFiles/Bookshelf_4AdjacensesRow"));
        String line = buff.readLine();
        int column = 0;

        while(line != null){
            line.trim();
            String[] params = line.split(";");

            ObjectCard card = new ObjectCard(Integer.parseInt(params[0]), convertToColor(params[1]));

            bookshelf.insertBookshelf(card, column);

            if(column < 2){
                column++;
            }

            line = buff.readLine();
        }

        assertEquals(bookshelf.checkAdjacences(bookshelf.getBookshelf()[5][0], 5, 0),4);

    }

    @Test
    public void bookshelf5AdjacensesFullColor() throws Exception {
        buff = new BufferedReader(new FileReader("src/test/TestFiles/Bookshelf_5AdjacensesFullColor"));
        String line = buff.readLine();

        while(line != null){
            line.trim();
            String[] params = line.split(";");

            ObjectCard card = new ObjectCard(Integer.parseInt(params[0]), convertToColor(params[1]));

            bookshelf.insertBookshelf(card, Integer.parseInt(params[2]));

            line = buff.readLine();
        }

        assertEquals(bookshelf.checkAdjacences(bookshelf.getBookshelf()[5][2], 5, 2),5);

    }

    @Test
    public void bookshelfAdjacensesDifficult() throws Exception {
        buff = new BufferedReader(new FileReader("src/test/TestFiles/Bookshelf_AdjacensesDifficult"));
        String line = buff.readLine();

        while(line != null){
            line.trim();
            String[] params = line.split(";");

            ObjectCard card = new ObjectCard(Integer.parseInt(params[0]), convertToColor(params[1]));

            bookshelf.insertBookshelf(card, Integer.parseInt(params[2]));

            line = buff.readLine();
        }

        assertEquals(bookshelf.checkAdjacences(bookshelf.getBookshelf()[2][2], 2, 2),12);

    }



}
