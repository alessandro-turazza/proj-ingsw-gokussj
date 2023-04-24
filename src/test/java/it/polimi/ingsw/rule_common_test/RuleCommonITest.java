package it.polimi.ingsw.rule_common_test;

import it.polimi.ingsw.server.model.common_goal.rule_common.RuleCommonI;
import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;
import it.polimi.ingsw.server.model.user.bookshelf.CellShelf;
import it.polimi.ingsw.user_test.BookshelfTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleCommonITest extends RuleCommonTest{


    private void initRule(){ ruleCommon=new RuleCommonI(); }


    @BeforeEach
    public void initalizer(){
        initUser();
        initRule();
    }

    @Test
    public void ruleCommonI1() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonI/RuleCommonI_True1.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonI2() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonI/RuleCommonI_False.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertFalse(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonI3() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonI/RuleCommonI_True2.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertTrue(ruleCommon.newRule(user));
    }
}
