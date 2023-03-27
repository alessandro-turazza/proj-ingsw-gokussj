package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

public class RuleCommonIITest {

    private static RuleCommonII ruleCommonII;

    private static User user;

    private static BufferedReader buff;

    public void initUser(){ user=new User("test"); }
    public void initRule(){ ruleCommonII=new RuleCommonII(); }

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
        initUser();
        initRule();
        Bookshelf bookshelf = user.getBookshelf();

        String line = buff.readLine();

        while(line != null){
            line.trim();
            String[] params = line.split(";");

            ObjectCard card = new ObjectCard(Integer.parseInt(params[0]), convertToColor(params[1]));
            bookshelf.insertBookshelf(card, Integer.parseInt(params[2]));

            line = buff.readLine();
        }

    }
    @Test
    public void ruleCommonII1() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonII_Starting00");
        assertTrue(ruleCommonII.newRule(user));
    }

    @Test
    public void ruleCommonII2() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonII_Starting01");
        assertTrue(ruleCommonII.newRule(user));
    }

    @Test
    public void ruleCommonII3() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonII_Starting40");
        assertTrue(ruleCommonII.newRule(user));
    }

    @Test
    public void ruleCommonII4() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonII_Starting41");
        assertTrue(ruleCommonII.newRule(user));
    }

    @Test
    public void ruleCommonII5() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonII_Parallel");
        assertTrue(ruleCommonII.newRule(user));
    }

    @Test
    public void ruleCommonII6() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonII_Cross");
        assertTrue(ruleCommonII.newRule(user));
    }
    @Test
    public void ruleCommonII7() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonII_Failed1");
        assertFalse(ruleCommonII.newRule(user));
    }

    @Test
    public void ruleCommonII8() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonII_Failed2");
        assertFalse(ruleCommonII.newRule(user));
    }

}
