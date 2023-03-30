package it.polimi.ingsw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static it.polimi.ingsw.ObjectCard.convertToColor;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalGoalCardTest {
    private static Bookshelf bookshelf;
    private static PersonalGoalCard personalGoal;

    private static BufferedReader buff;

    @BeforeAll
    public static void initBookshelf(){
        bookshelf = new Bookshelf();
    }

    @BeforeEach
    public void reInit(){
        bookshelf = new Bookshelf();
        personalGoal = new PersonalGoalCard(1);
    }


    private void readFile(String fileName) throws Exception {
        buff = new BufferedReader(new FileReader(fileName));

        String line = buff.readLine();

        while(!line.equals("//")){
            String[] params = line.split(";");

            ObjectCard card = new ObjectCard(Integer.parseInt(params[0]), convertToColor(params[1]));
            bookshelf.insertBookshelf(card, Integer.parseInt(params[2]));

            line = buff.readLine();
        }

        line = buff.readLine();

        while(line != null){
            String[] params = line.split(";");

            personalGoal.getCostraints().add(new Costraints(Integer.parseInt(params[1]), Integer.parseInt(params[2]), convertToColor(params[0])));

            line = buff.readLine();
        }
    }

    @Test
    public void personalGoalCard1() throws Exception {
        this.readFile("src/test/TestFiles/PersonalGoalCardTest/PersonalGoalCard_1");
        assertEquals(personalGoal.checkRule(bookshelf), 6);
    }

    @Test
    public void personalGoalCard2() throws Exception {
        this.readFile("src/test/TestFiles/PersonalGoalCardTest/PersonalGoalCard_2");
        assertEquals(personalGoal.checkRule(bookshelf), 3);
    }

    @Test
    public void personalGoalCard3() throws Exception {
        this.readFile("src/test/TestFiles/PersonalGoalCardTest/PersonalGoalCard_3");
        assertEquals(personalGoal.checkRule(bookshelf), 2);
    }
}
