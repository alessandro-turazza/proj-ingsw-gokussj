package it.polimi.ingsw.user_test;

import it.polimi.ingsw.game_data.GameData;
import it.polimi.ingsw.object_card.Color;
import it.polimi.ingsw.object_card.ObjectCard;
import it.polimi.ingsw.user.User;
import it.polimi.ingsw.user.bookshelf.Bookshelf;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {
    private static User user;

    @BeforeAll
    public static void initUser() throws IOException, ParseException {
        user = new User("User1");
        GameData.loadObjectCards("src/data/Object_Cards_Data.json");
        GameData.loadPersonalGoals("src/data/PersonalGoals_Data.json");
    }

    @BeforeEach
    public void reInitUser() throws IOException, ParseException {
        initUser();
    }

    @Test
    public void userAdjacenses1() throws Exception {
        user.setBookshelf(BookshelfTest.readBookshelfList("src/test/TestFiles/UserTest/User_Adjacenses1.json"));
        user.updatePointsAdjacenses();
        assertEquals(user.getPoints(), 2);
    }

    @Test
    public void userAdjacenses2() throws Exception {
        user.setBookshelf(BookshelfTest.readBookshelfList("src/test/TestFiles/UserTest/User_Adjacenses2.json"));
        user.updatePointsAdjacenses();
        assertEquals(user.getPoints(), 3);
    }
    @Test
    public void userAdjacenses3() throws Exception {
        user.setBookshelf(BookshelfTest.readBookshelfList("src/test/TestFiles/UserTest/User_Adjacenses3.json"));
        user.updatePointsAdjacenses();
        assertEquals(user.getPoints(), 5);
    }

    @Test
    public void userAdjacenses4() throws Exception {
        user.setBookshelf(BookshelfTest.readBookshelfList("src/test/TestFiles/UserTest/User_Adjacenses4.json"));
        user.updatePointsAdjacenses();
        assertEquals(user.getPoints(), 11);
    }

    @Test
    public void userPersonalGoal1() throws Exception {
        user.setBookshelf(new Bookshelf(BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/Bookshelf_PersonalGoal_1.json")));
        user.setPersonalGoal(GameData.getPersonalGoalCards().get(0));
        user.checkPersonalGoal();
        assertEquals(user.getPoints(), 12);
    }

    @Test
    public void userPersonalGoal2() throws Exception {
        user.setBookshelf(new Bookshelf(BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/Bookshelf_PersonalGoal_2.json")));
        user.setPersonalGoal(GameData.getPersonalGoalCards().get(0));
        user.checkPersonalGoal();
        assertEquals(user.getPoints(), 4);
    }

    @Test
    public void userPersonalGoal3() throws Exception {
        user.setBookshelf(new Bookshelf(BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/Bookshelf_PersonalGoal_3.json")));
        user.setPersonalGoal(GameData.getPersonalGoalCards().get(0));
        user.checkPersonalGoal();
        assertEquals(user.getPoints(), 2);
    }

    @Test
    public void user_DropObjectCardColumnEmpty() throws Exception {
        user.setBookshelf(BookshelfTest.readBookshelfList("src/test/TestFiles/UserTest/User_DropObjectCardColumnEmpty.json"));
        ObjectCard card = new ObjectCard(1, Color.WHITE);
        user.dropObjectCard(card,0);

        assertEquals(user.getBookshelf().getBookshelf()[5][0].getObjectCard(), card);
    }

    @Test
    public void user_DropObjectCardColumnNotEmpty() throws Exception {
        user.setBookshelf(BookshelfTest.readBookshelfList("src/test/TestFiles/UserTest/User_DropObjectCardColumnNotEmpty.json"));
        ObjectCard card = new ObjectCard(1, Color.WHITE);
        user.dropObjectCard(card,1);

        assertEquals(user.getBookshelf().getBookshelf()[2][1].getObjectCard(), card);
    }

    @Test
    public void user_DropObjectCardColumnFull() throws Exception {
        user.setBookshelf(BookshelfTest.readBookshelfList("src/test/TestFiles/UserTest/User_DropObjectCardColumnFull.json"));
        ObjectCard card = new ObjectCard(1, Color.WHITE);
        assertThrows(Exception.class,
                () -> user.dropObjectCard(card,1));
    }
    @Test
    public void user_DropObjectCardWrongColumn1() throws Exception {
        user.setBookshelf(BookshelfTest.readBookshelfList("src/test/TestFiles/UserTest/User_DropObjectCardColumnFull.json"));
        ObjectCard card = new ObjectCard(1, Color.WHITE);
        assertThrows(Exception.class,
                () -> user.dropObjectCard(card,9));
    }

    @Test
    public void user_DropObjectCardWrongColumn2() throws Exception {
        user.setBookshelf(BookshelfTest.readBookshelfList("src/test/TestFiles/UserTest/User_DropObjectCardColumnFull.json"));
        ObjectCard card = new ObjectCard(1, Color.WHITE);
        assertThrows(Exception.class,
                () -> user.dropObjectCard(card,-2));
    }


}
