package it.polimi.ingsw.user_test;

import it.polimi.ingsw.server.game_data.GameData;
import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;
import it.polimi.ingsw.server.model.user.bookshelf.CellShelf;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalGoalCardTest {
    private static Bookshelf bookshelf;

    @BeforeAll
    public static void initBookshelf() throws IOException, ParseException {
        GameData.loadObjectCards("src/data/Object_Cards_Data.json");
        GameData.loadPersonalGoals("src/data/PersonalGoals_Data.json");
    }

    @Test
    public void personalGoalCard1() throws Exception {
        CellShelf[][] cs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/Bookshelf_PersonalGoal_1.json");
        bookshelf = new Bookshelf(cs);
        assertEquals(GameData.getPersonalGoalCards().get(0).checkRule(bookshelf), 6);
    }

    @Test
    public void personalGoalCard2() throws Exception {
        CellShelf[][] cs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/Bookshelf_PersonalGoal_2.json");
        bookshelf = new Bookshelf(cs);
        assertEquals(GameData.getPersonalGoalCards().get(0).checkRule(bookshelf), 3);
    }

    @Test
    public void personalGoalCard3() throws Exception {
        CellShelf[][] cs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/Bookshelf_PersonalGoal_3.json");
        bookshelf = new Bookshelf(cs);
        assertEquals(GameData.getPersonalGoalCards().get(0).checkRule(bookshelf), 2);
    }
}
