package it.polimi.ingsw.server.model.game_manager;

import it.polimi.ingsw.server.game_data.GameData;
import it.polimi.ingsw.server.model.common_goal.CommonGoal;
import it.polimi.ingsw.server.model.plank.Plank;
import it.polimi.ingsw.server.model.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 * This class handles the turns and permits the unfolding of the game and esteblishes the order of the players
 */
public class GameManager {
    private Plank plank;
    private ArrayList<User> users;
    private int numUser;
    private int idGame;
    private ArrayList<CommonGoal> commonGoals;
    private TurnManager turnManager;
    private User winner;
    private static final int numPersonalGoal = 12;
    private static final int numCommonGoal = 12;

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

    public static int getNumCommonGoal() {
        return numCommonGoal;
    }

    public static int getNumPersonalGoal() {
        return numPersonalGoal;
    }

    private ArrayList<Integer> generateTokens(){

        return new ArrayList<>(GameData.getDataTokens().get(users.size() - 2));
    }

    /**
     * This method takes two random common goals and set them into the game
     */
    private void generateCommonGoalList(){
        this.commonGoals = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        int dimCommonGoal = GameData.getIdCommonGoals().size();
        int i = 0;

        while(i < dimCommonGoal){
            Random random = new Random();
            int n = random.nextInt(numCommonGoal);

            if(!indexes.contains(n)){
                indexes.add(n);
                i++;
            }
        }

        for(i = 0; i < dimCommonGoal; i++){
            commonGoals.add(new CommonGoal(GameData.getIdCommonGoals().get(i), GameData.getRuleCommons().get(indexes.get(i))));
        }

        for(i = 0; i < dimCommonGoal; i++){
            ArrayList<Integer> tokens = generateTokens();
            commonGoals.get(i).setTokenCardsInteger(tokens, i+1);
        }
    }

    /**
     * This method takes and assignes randomly a personal goal to each player
     */
    private void assignPersonalGoal(){
        ArrayList<Integer> indexPersGoal = new ArrayList<>();

        int i = 0;

        while (i < users.size()){
            Random random = new Random();
            int n = random.nextInt(numPersonalGoal);

            if(!indexPersGoal.contains(n)){
                indexPersGoal.add(n);
                users.get(i).setPersonalGoal(GameData.getPersonalGoalCards().get(n));
                i++;
            }
        }
    }

    /**
     * This method add a new user to the game
     */
    public boolean addNewPlayer(User user){
        if(users.size() < numUser){

            for(User u: users){
                if(u.getName().equals(user.getName()))
                    return false;
            }

            users.add(user);
            return true;
        }
        return false;
    }

    /**
     * This method sets all the components in order to start the game
     */
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

    /**
     * This method takes the action from a player and modify the model then goes on with the new turn
     */
    public User updateGame(ArrayList<Integer> X,ArrayList<Integer> Y, int column) throws Exception {
        User result=turnManager.updateGame(X,Y,column);
        if(result==null)endGame();
        else calculatePoints();
        return result;
    }

    /**
     * This method calculates the points for each user and terminates the game
     */
    public void endGame(){
        calculatePoints();
    }

    /**
     * This method calculates points for each user basing on their bookshelfs and common goals
     */
    public void calculatePoints(){
        winner = users.get(0);
        for(User user: users){
            user.setPoints(0);
            user.updatePointsAdjacenses();
            user.checkPersonalGoal();
            user.addTokenPointsToPoints();
            if(winner.getPoints() < user.getPoints())
                winner = user;
        }
    }

    public TurnManager getTurnManager() {
        return turnManager;
    }
}
