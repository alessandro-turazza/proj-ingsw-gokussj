package it.polimi.ingsw;

import org.junit.jupiter.api.BeforeAll;

import java.io.BufferedReader;
import java.io.FileReader;

import static it.polimi.ingsw.ObjectCard.convertToColor;

public class RuleCommonTest {

     static RuleCommon ruleCommon;

     static User user;

     static BufferedReader buff;

    void initUser(){ user=new User("test"); }

    void readFile(String fileName) throws Exception {
        user.setBookshelf(BookshelfTest.readFile(fileName));
    }
}
