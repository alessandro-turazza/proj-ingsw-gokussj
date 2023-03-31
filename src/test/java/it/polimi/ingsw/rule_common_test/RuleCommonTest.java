package it.polimi.ingsw.rule_common_test;

import it.polimi.ingsw.user_test.BookshelfTest;
import it.polimi.ingsw.rule_common.RuleCommon;
import it.polimi.ingsw.user.User;

import java.io.BufferedReader;

public class RuleCommonTest {

     static RuleCommon ruleCommon;

     static User user;


    void initUser(){ user=new User("test"); }

    void readFile(String fileName) throws Exception {
        user.setBookshelf(BookshelfTest.readFile(fileName));
    }
}
