package it.polimi.ingsw.rule_common_test;

import it.polimi.ingsw.common_goal.rule_common.RuleCommonVII;
import it.polimi.ingsw.user.bookshelf.Bookshelf;
import it.polimi.ingsw.user.bookshelf.CellShelf;
import it.polimi.ingsw.user_test.BookshelfTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleCommonVIITest extends RuleCommonTest {

    public void initRule() {
        ruleCommon = new RuleCommonVII();
    }

    @BeforeEach
    public void initalizer() {
        initUser();
        initRule();
    }


    @Test
    public void ruleCommonVII1() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonVII/RuleCommonVII_FullTrue1.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVII2() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonVII/RuleCommonVII_FullFalse1.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertFalse(ruleCommon.newRule(user));
    }
    @Test
    public void ruleCommonVII3() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonVII/RuleCommonVII_FullTrue2.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVII4() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonVII/RuleCommonVII_FullFalse2.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertFalse(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVII5() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonVII/RuleCommonVII_NotFullTrue.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVII6() throws Exception {
        CellShelf[][] bs = BookshelfTest.readBookshelfMatrix("src/test/TestFiles/BookshelfTest/BookshelfForRuleCommonTest/RuleCommonVII/RuleCommonVII_NotFullFalse.json");
        Bookshelf b = new Bookshelf(bs);
        user.setBookshelf(b);
        assertFalse(ruleCommon.newRule(user));
    }

}
