package it.polimi.ingsw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static it.polimi.ingsw.ObjectCard.convertToColor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {
    private static User user;
    private static BufferedReader buff;

    @BeforeAll
    public static void initUser(){
        user = new User("User1");
    }

    @BeforeEach
    public void reInitUser(){
        initUser();
    }


    private void readFileBookshelf(String fileName) throws Exception {//uguale a readFile di RuleCommonTest che chiama quello di BookShelfTest
        user.setBookshelf(BookshelfTest.readFile(fileName));
    }
    private void readFilePersonalGoal(String fileName) throws Exception {
        buff = new BufferedReader(new FileReader(fileName));

        String line = buff.readLine();

        while(!line.equals("//")){
            String[] params = line.split(";");

            ObjectCard card = new ObjectCard(Integer.parseInt(params[0]), convertToColor(params[1]));
            user.getBookshelf().insertBookshelf(card, Integer.parseInt(params[2]));

            line = buff.readLine();
        }

        user.setPersonalGoal(new PersonalGoalCard(1));

        line = buff.readLine();

        while(line != null){
            String[] params = line.split(";");

            user.getPersonalGoal().getCostraints().add(new Costraints(Integer.parseInt(params[1]), Integer.parseInt(params[2]), convertToColor(params[0])));

            line = buff.readLine();
        }
    }
    @Test
    public void userAdjacenses1() throws Exception {
        this.readFileBookshelf("src/test/TestFiles/UserTest/User_Adjacenses1");
        user.updatePointsAdjacenses();
        assertEquals(user.getPoints(), 2);
    }

    @Test
    public void userAdjacenses2() throws Exception {
        this.readFileBookshelf("src/test/TestFiles/UserTest/User_Adjacenses2");
        user.updatePointsAdjacenses();
        assertEquals(user.getPoints(), 3);
    }
    @Test
    public void userAdjacenses3() throws Exception {
        this.readFileBookshelf("src/test/TestFiles/UserTest/User_Adjacenses3");
        user.updatePointsAdjacenses();
        assertEquals(user.getPoints(), 5);
    }

    @Test
    public void userAdjacenses4() throws Exception {
        this.readFileBookshelf("src/test/TestFiles/UserTest/User_Adjacenses4");
        user.updatePointsAdjacenses();
        assertEquals(user.getPoints(), 11);
    }

    @Test
    public void userPersonalGoal1() throws Exception {
        readFilePersonalGoal("src/test/TestFiles/UserTest/User_PersonalGoal1");
        user.checkPersonalGoal();
        assertEquals(user.getPoints(), 12);
    }

    @Test
    public void userPersonalGoal2() throws Exception {
        readFilePersonalGoal("src/test/TestFiles/UserTest/User_PersonalGoal2");
        user.checkPersonalGoal();
        assertEquals(user.getPoints(), 4);
    }

    @Test
    public void userPersonalGoal3() throws Exception {
        readFilePersonalGoal("src/test/TestFiles/UserTest/User_PersonalGoal3");
        user.checkPersonalGoal();
        assertEquals(user.getPoints(), 2);
    }

    @Test
    public void userPersonalGoal4() throws Exception {
        readFilePersonalGoal("src/test/TestFiles/UserTest/User_PersonalGoal4");
        user.checkPersonalGoal();
        assertEquals(user.getPoints(), 0);
    }

    @Test
    public void user_DropObjectCardColumnEmpty() throws Exception {
        readFileBookshelf("src/test/TestFiles/UserTest/User_DropObjectCardColumnEmpty");
        ObjectCard card = new ObjectCard(1, Color.WHITE);
        user.dropObjectCard(card,0);

        assertEquals(user.getBookshelf().getBookshelf()[5][0].getObjectCard(), card);
    }

    @Test
    public void user_DropObjectCardColumnNotEmpty() throws Exception {
        readFileBookshelf("src/test/TestFiles/UserTest/User_DropObjectCardColumnNotEmpty");
        ObjectCard card = new ObjectCard(1, Color.WHITE);
        user.dropObjectCard(card,1);

        assertEquals(user.getBookshelf().getBookshelf()[2][1].getObjectCard(), card);
    }

    @Test
    public void user_DropObjectCardColumnFull() throws Exception {
        readFileBookshelf("src/test/TestFiles/UserTest/User_DropObjectCardColumnFull");
        ObjectCard card = new ObjectCard(1, Color.WHITE);
        assertThrows(Exception.class,
                () -> {
                    user.dropObjectCard(card,1);
                });
    }
    @Test
    public void user_DropObjectCardWrongColumn1() throws Exception {
        readFileBookshelf("src/test/TestFiles/UserTest/User_DropObjectCardColumnFull");
        ObjectCard card = new ObjectCard(1, Color.WHITE);
        assertThrows(Exception.class,
                () -> {
                    user.dropObjectCard(card,9);
                });
    }

    @Test
    public void user_DropObjectCardWrongColumn2() throws Exception {
        readFileBookshelf("src/test/TestFiles/UserTest/User_DropObjectCardColumnFull");
        ObjectCard card = new ObjectCard(1, Color.WHITE);
        assertThrows(Exception.class,
                () -> {
                    user.dropObjectCard(card,-2);
                });
    }


}
