package it.polimi.ingsw.game_manager;

import it.polimi.ingsw.common_goal.CommonGoal;
import it.polimi.ingsw.common_goal.TokenCard;
import it.polimi.ingsw.game_data.GameData;
import it.polimi.ingsw.plank.Plank;
import it.polimi.ingsw.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameManager {
    private Plank plank;
    private ArrayList<User> users;
    private ArrayList<CommonGoal> commonGoals;

    public GameManager(ArrayList<User> users){
        this.users = users;
    }

    public Plank getPlank() {
        return plank;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<CommonGoal> getCommonGoals() {
        return commonGoals;
    }

    private ArrayList<Integer> generateTokens(){
        ArrayList<Integer> tokens = new ArrayList<>();

        switch(users.size()){
            case 2: Collections.addAll(tokens,8,4);
                break;
            case 3: Collections.addAll(tokens, 8,6,4);
                break;
            case 4: Collections.addAll(tokens, 8,6,4,2);
                break;
        }

        return tokens;

    }

    private void generateCommonGoalList(){
        int ind1 = -1;
        int ind2 = -1;

        while(ind1 == ind2){
            Random random = new Random();
            ind1 = random.nextInt(12);
            random = new Random();
            ind2 = random.nextInt(12);
        }

        CommonGoal firstGoal = new CommonGoal(1, GameData.getRuleCommons().get(ind1));
        CommonGoal secondGoal = new CommonGoal(2, GameData.getRuleCommons().get(ind2));

        ArrayList<TokenCard> tokenCards1 = new ArrayList<>();

        ArrayList<Integer> tokens = generateTokens();

        for(int i = 0; i < tokens.size(); i++){
            TokenCard t = new TokenCard(tokens.get(i), 1);
            tokenCards1.add(t);
        }

        ArrayList<TokenCard> tokenCards2 = new ArrayList<>();

        for(int i = 0; i < tokens.size(); i++){
            TokenCard t = new TokenCard(tokens.get(i), 2);
            tokenCards2.add(t);
        }

        firstGoal.setTokenCards(tokenCards1);
        secondGoal.setTokenCards(tokenCards2);

        this.commonGoals = new ArrayList<>();
        commonGoals.add(firstGoal);
        commonGoals.add(secondGoal);
    }

    private void assignPersonalGoal(){
        ArrayList<Integer> indexPersGoal = new ArrayList<>();

        int i = 0;

        while (i < users.size()){
            Random random = new Random();
            int n = random.nextInt(12);

            if(!indexPersGoal.contains(n)){
                indexPersGoal.add(n);
                users.get(i).setPersonalGoal(GameData.getPersonalGoalCards().get(n));
                i++;
            }
        }
    }
    public void startGame(){

        this.plank = new Plank();
        plank.initializePlank(GameData.getPlank_config(), users.size());
        plank.initializeCardBag(GameData.getDataObjectCards());
        plank.fillPlank();

        generateCommonGoalList();

        assignPersonalGoal();

        Collections.shuffle(users);

    }


}
