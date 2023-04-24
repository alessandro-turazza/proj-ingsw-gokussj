package it.polimi.ingsw.rule_common_test;

import it.polimi.ingsw.server.model.common_goal.rule_common.RuleCommonVI;
import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;
import it.polimi.ingsw.server.model.user.bookshelf.CellShelf;
import it.polimi.ingsw.user_test.BookshelfTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleCommonVITest extends RuleCommonTest{

    public void initRule(){ ruleCommon=new RuleCommonVI(); }

    @BeforeEach
    public void initalizer(){
        initUser();
        initRule();
    }


    @Test
    public void ruleCommonVI1() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonVI/RuleCommonVI_FullTrue.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVI2() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonVI/RuleCommonVI_FullFalse.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertFalse(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVI3() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonVI/RuleCommonVI_NotFullTrue.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVI4() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonVI/RuleCommonVI_NotFullFalse.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertFalse(ruleCommon.newRule(user));
    }
}
