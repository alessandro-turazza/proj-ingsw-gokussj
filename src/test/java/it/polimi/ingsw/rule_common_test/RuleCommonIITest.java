package it.polimi.ingsw.rule_common_test;

import it.polimi.ingsw.common_goal.rule_common.RuleCommonII;
import it.polimi.ingsw.user.bookshelf.Bookshelf;
import it.polimi.ingsw.user.bookshelf.CellShelf;
import it.polimi.ingsw.user_test.BookshelfTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleCommonIITest extends RuleCommonTest{


    private void initRule(){ ruleCommon=new RuleCommonII(); }


    @BeforeEach
    public void initalizer(){
        initUser();
        initRule();
    }

    @Test
    public void ruleCommonII1() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonII/RuleCommonII_Starting00");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII2() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonII/RuleCommonII_Starting01");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII3() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonII/RuleCommonII_Starting40");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII4() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonII/RuleCommonII_Starting41");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII5() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonII/RuleCommonII_Parallel");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII6() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonII/RuleCommonII_Cross");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII6Json() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfRuleCommonIICross.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertTrue(ruleCommon.newRule(user));
    }
    @Test
    public void ruleCommonII7() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonII/RuleCommonII_Failed1");
        assertFalse(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII8() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonII/RuleCommonII_Failed2");
        assertFalse(ruleCommon.newRule(user));
    }

}
