package it.polimi.ingsw;

import java.io.BufferedReader;
import java.io.FileReader;

import static it.polimi.ingsw.ObjectCard.convertToColor;

public class RuleCommonTest {

     static RuleCommon ruleCommon;

     static User user;

     static BufferedReader buff;

    void initUser(){ user=new User("test"); }

    void readFile(String fileName) throws Exception {
        buff = new BufferedReader(new FileReader(fileName));
        Bookshelf bookshelf = user.getBookshelf();

        String line = buff.readLine();

        while(line != null){
            String[] params = line.split(";");

            ObjectCard card = new ObjectCard(Integer.parseInt(params[0]), convertToColor(params[1]));
            bookshelf.insertBookshelf(card, Integer.parseInt(params[2]));

            line = buff.readLine();
        }

    }
}
