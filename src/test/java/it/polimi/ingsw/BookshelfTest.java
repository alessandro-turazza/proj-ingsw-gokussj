package it.polimi.ingsw;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void bookshelfEmpty() throws Exception {
    bookshelf.insertBookshelf(defaultObjectCard,3);
    assertEquals(bookshelf.getBookshelf()[5][3].getObjectCard(),defaultObjectCard);
    }

    @Test
    public void bookshelfFull() throws Exception {
        for(int r=5;r>=0;r--){
            for(int c=4;c>=0;c--){
                bookshelf.insertBookshelf(defaultObjectCard,c);
            }
        }
        assertThrows(Exception.class,
                () -> {
                    bookshelf.insertBookshelf(defaultObjectCard,3);
                });
    }

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
    public void bookshelf_ColumnFull() throws Exception {
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


}
