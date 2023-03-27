package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleCommonVIIITest {
    private static RuleCommonVIII ruleCommonVIII;

    private static User user;

    private static BufferedReader buff;

    public void initUser(){ user=new User("test"); }
    public void initRule(){ ruleCommonVIII=new RuleCommonVIII(); }

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
    public void ruleCommonVIII1() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonVIII_FullTrue");
        assertTrue(ruleCommonVIII.newRule(user));
    }

    @Test
    public void ruleCommonVIII2() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonVIII_FullFalse");
        assertFalse(ruleCommonVIII.newRule(user));
    }

    @Test
    public void ruleCommonVIII3() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonVIII_NotFullTrue");
        assertTrue(ruleCommonVIII.newRule(user));
    }

    @Test
    public void ruleCommonVIII4() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonVIII_NotFullFalse");
        assertFalse(ruleCommonVIII.newRule(user));
    }
}
