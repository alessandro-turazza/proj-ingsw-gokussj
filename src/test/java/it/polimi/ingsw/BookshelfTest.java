package it.polimi.ingsw;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookshelfTest {
    private static Bookshelf bookshelf;
    ObjectCard defaultObjectCard= new ObjectCard(1,Color.WHITE);

    @BeforeAll
    public static void InitBookshelf(){ bookshelf=new Bookshelf();}

    @BeforeEach
    public void ReInitBookshelf(){ bookshelf=new Bookshelf();}

    @Test
    public void BookshelfEmpty() throws Exception {
    bookshelf.insertBookshelf(defaultObjectCard,3);
    assertEquals(bookshelf.getBookshelf()[5][3].getObjectCard(),defaultObjectCard);
    }

    @Test
    public void BookshelfFull() throws Exception {
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

}
