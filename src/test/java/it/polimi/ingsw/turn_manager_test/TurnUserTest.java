package it.polimi.ingsw.turn_manager_test;

import it.polimi.ingsw.game_manager.TurnUser;
import it.polimi.ingsw.user.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TurnUserTest {
    private static TurnUser turnUser;
    private static ArrayList<User> users;

    @BeforeEach
    public void initUsers() throws IOException, ParseException {
        users=loadUser();
    }

    public static ArrayList<User> loadUser() throws IOException, ParseException {
        ArrayList<User> result=new ArrayList<>();
        FileReader fr = new FileReader("src/test/TestFiles/TurnManagerTest/TurnManagerUser.json");
        JSONObject obj = (JSONObject) new JSONParser().parse(fr);
        JSONArray list=(JSONArray) obj.get("User");
        for(Object o:list){
            result.add(new User(o.toString()));
        }
        return result;
    }

    @Test
    public void TurnUserTest_next() throws IOException, ParseException {
        users=loadUser();
        turnUser=new TurnUser(users);
        for(User user:users){
            assertEquals(user.getName(),turnUser.activeUser().getName());
            turnUser.next();
        }
        assertEquals(users.get(0).getName(),turnUser.activeUser().getName());
    }

    @Test
    public void TurnUserTest_lastTurn() throws IOException, ParseException {
        loadUser();
        turnUser=new TurnUser(users);
        turnUser.lastTurn();
        for(int i=0;i<users.size()-1;i++){
            assertEquals(users.get(i).getName(),turnUser.activeUser().getName());
            turnUser.next();
        }
        assertNull(turnUser.next());
        assertNull(turnUser.next());
    }


}
