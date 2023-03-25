package it.polimi.ingsw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

    private void readFile(String fileName) throws Exception {
        buff = new BufferedReader(new FileReader(fileName));

        String line = buff.readLine();

        while(!line.equals("//")){
            line.trim();
            String[] params = line.split(";");

            ObjectCard card = new ObjectCard(Integer.parseInt(params[0]), convertToColor(params[1]));
            bookshelf.insertBookshelf(card, Integer.parseInt(params[2]));

            line = buff.readLine();
        }

        line = buff.readLine();

        while(line != null){
            line.trim();
            String[] params = line.split(";");

            personalGoal.getCostraints().add(new Costraints(Integer.parseInt(params[1]), Integer.parseInt(params[2]), convertToColor(params[0])));

            line = buff.readLine();
        }
    }

    @Test
    public void personalGoalCard1() throws Exception {
        this.readFile("src/test/TestFiles/PersonalGoalCard_1");
        assertEquals(personalGoal.checkRule(bookshelf), 6);
    }

    @Test
    public void personalGoalCard2() throws Exception {
        this.readFile("src/test/TestFiles/PersonalGoalCard_2");
        assertEquals(personalGoal.checkRule(bookshelf), 3);
    }

    @Test
    public void personalGoalCard3() throws Exception {
        this.readFile("src/test/TestFiles/PersonalGoalCard_3");
        assertEquals(personalGoal.checkRule(bookshelf), 2);
    }
}
