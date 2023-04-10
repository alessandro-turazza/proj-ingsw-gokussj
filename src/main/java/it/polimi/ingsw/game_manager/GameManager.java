package it.polimi.ingsw.game_manager;

import it.polimi.ingsw.common_goal.CommonGoal;
import it.polimi.ingsw.game_data.GameData;
import it.polimi.ingsw.plank.CellPlank;
import it.polimi.ingsw.plank.Plank;
import it.polimi.ingsw.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameManager {
    private Plank plank;
    private ArrayList<User> users;
    private int numUser;
    private int idGame;
    private ArrayList<CommonGoal> commonGoals;
    private TurnManager turnManager;
    private User winner;

    public GameManager(ArrayList<User> users){
        this.users = users;
    }

    public GameManager(User startUser, int numUser, int codGame){
        users = new ArrayList<>();
        users.add(startUser);
        this.numUser = numUser;
        this.idGame = codGame;
    }

    public int getNumUser(){
        return numUser;
    }

    public int getIdGame() {
        return idGame;
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

    public User getWinner(){
        return winner;
    }

    private ArrayList<Integer> generateTokens(){

        return new ArrayList<>(GameData.getDataTokens().get(users.size() - 2));
    }

    private void generateCommonGoalList(){
        this.commonGoals = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        int numCommonGoal = GameData.getIdCommonGoals().size();
        int i = 0;

        while(i < numCommonGoal){
            Random random = new Random();
            int n = random.nextInt(12);

            if(!indexes.contains(n)){
                indexes.add(n);
                i++;
            }
        }

        for(i = 0; i < numCommonGoal; i++){
            commonGoals.add(new CommonGoal(GameData.getIdCommonGoals().get(i), GameData.getRuleCommons().get(indexes.get(i))));
        }

        for(i = 0; i < numCommonGoal; i++){
            ArrayList<Integer> tokens = generateTokens();
            commonGoals.get(i).setTokenCardsInteger(tokens, i+1);
        }
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

    public boolean addNewPlayer(User user){
        if(users.size() < numUser){
            users.add(user);
            return true;
        }
        return false;
    }
    public void startGame(){
        this.plank = new Plank();
        plank.initializePlank(GameData.getPlank_config(), users.size());
        plank.initializeCardBag(GameData.getDataObjectCards());
        plank.fillPlank();

        generateCommonGoalList();
        this.turnManager = new TurnManager(users, plank, commonGoals);

        assignPersonalGoal();

        Collections.shuffle(users);

        System.out.println("Game " + idGame +" starts");

    }

    public User nextUserTurn(){
        return turnManager.getUsers().next();
    }

    public void updateGame(ArrayList<CellPlank> chosenCard, int column) throws Exception {
        if(turnManager.updateGame(chosenCard,column)==null)endGame();
        //else notify controller
    }
    public void endGame(){
        winner = users.get(0);
        for(User user: users){
            user.updatePointsAdjacenses();
            user.checkPersonalGoal();

            if(winner.getPoints() < user.getPoints())
                winner = user;
        }
    }

    public TurnManager getTurnManager() {
        return turnManager;
    }
}
