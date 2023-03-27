package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleCommonIVTest {
    private static RuleCommonIV ruleCommonIV;

    private static User user;

    private static BufferedReader buff;

    public void initUser(){ user=new User("test"); }
    public void initRule(){ ruleCommonIV=new RuleCommonIV(); }

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
    public void ruleCommonIV1() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonIV_FullTrue");
        assertTrue(ruleCommonIV.newRule(user));
    }

    @Test
    public void ruleCommonIV2() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonIV_FullFalse");
        assertFalse(ruleCommonIV.newRule(user));
    }
    @Test
    public void ruleCommonIV3() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonIV_NotFullTrue1");
        assertTrue(ruleCommonIV.newRule(user));
    }

    @Test
    public void ruleCommonIV4() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonIV_NotFullFalse1");
        assertFalse(ruleCommonIV.newRule(user));
    }

    @Test
    public void ruleCommonIV5() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonIV_NotFullTrue2");
        assertTrue(ruleCommonIV.newRule(user));
    }

    @Test
    public void ruleCommonIV6() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonIV_NotFullFalse2");
        assertFalse(ruleCommonIV.newRule(user));
    }

}
