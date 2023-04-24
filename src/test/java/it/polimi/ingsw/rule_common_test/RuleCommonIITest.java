package it.polimi.ingsw.rule_common_test;

import it.polimi.ingsw.server.model.common_goal.rule_common.RuleCommonII;
import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;
import it.polimi.ingsw.server.model.user.bookshelf.CellShelf;
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
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonII/RuleCommonII_Starting00.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII2() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonII/RuleCommonII_Starting01.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII3() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonII/RuleCommonII_Starting40.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII4() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonII/RuleCommonII_Starting41.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII5() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonII/RuleCommonII_Parallel.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertTrue(ruleCommon.newRule(user));
    }


    @Test
    public void ruleCommonII6() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonII/RuleCommonII_Cross.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertTrue(ruleCommon.newRule(user));
    }
    @Test
    public void ruleCommonII7() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonII/RuleCommonII_Failed1.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertFalse(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII8() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonII/RuleCommonII_Failed2.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertFalse(ruleCommon.newRule(user));
    }

}
