package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static it.polimi.ingsw.ObjectCard.convertToColor;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleCommonVTest {
    private static RuleCommonV ruleCommonV;

    private static User user;

    private static BufferedReader buff;

    public void initUser(){ user=new User("test"); }
    public void initRule(){ ruleCommonV=new RuleCommonV(); }


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
    public void ruleCommonV1() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonV_True");
        assertTrue(ruleCommonV.newRule(user));
    }

    @Test
    public void ruleCommonV2() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonV_False1");
        assertFalse(ruleCommonV.newRule(user));
    }

    @Test
    public void ruleCommonV3() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonV_False2");
        assertFalse(ruleCommonV.newRule(user));
    }

    @Test
    public void ruleCommonV4() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonV_False3");
        assertFalse(ruleCommonV.newRule(user));
    }

}
