package it.polimi.ingsw.rule_common_test;


import it.polimi.ingsw.common_goal.rule_common.RuleCommonIX;
import it.polimi.ingsw.user.bookshelf.Bookshelf;
import it.polimi.ingsw.user.bookshelf.CellShelf;
import it.polimi.ingsw.user_test.BookshelfTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleCommonIXTest extends RuleCommonTest{

    public void initRule(){ ruleCommon=new RuleCommonIX(); }

    @BeforeEach
    public void initalizer(){
        initUser();
        initRule();
    }

    @Test
    public void ruleCommonIX1() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonIX/RuleCommonIX_FullTrue.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVI2() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonIX/RuleCommonIX_FullFalse.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertFalse(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVI3() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonIX/RuleCommonIX_NotFullTrue.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVI4() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonIX/RuleCommonIX_NotFullFalse.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertFalse(ruleCommon.newRule(user));
    }
}

