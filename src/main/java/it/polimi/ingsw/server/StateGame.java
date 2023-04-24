package it.polimi.ingsw.server;

import com.google.gson.Gson;
import it.polimi.ingsw.server.model.common_goal.CommonGoal;
import it.polimi.ingsw.server.model.game_manager.GameManager;
import it.polimi.ingsw.server.model.object_card.ObjectCard;
import it.polimi.ingsw.server.model.plank.CellPlank;
import it.polimi.ingsw.server.model.plank.Plank;
import it.polimi.ingsw.server.model.user.User;
import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;
import it.polimi.ingsw.server.model.user.bookshelf.CellShelf;
import it.polimi.ingsw.server.model.user.personal_goal.PersonalGoalCard;

import java.util.ArrayList;

public class StateGame {
    private ArrayList<User> usersClone;
    private Plank plankClone;

    private String activeUser;

    private ArrayList<CommonGoal> commonGoalsClone;


    public StateGame(GameManager gameManager) {
        usersClone=new ArrayList<>();
        activeUser=gameManager.getTurnManager().getUsers().activeUser().getName();
        for(User user: gameManager.getUsers()){
            User userClone=new User(user.getName());
            Bookshelf bookshelfUser=user.getBookshelf();
            CellShelf[][] cellShelves=new CellShelf[bookshelfUser.getNumRow()][bookshelfUser.getNumColumn()];
            for(int i = 0; i < bookshelfUser.getNumRow(); i++){
                for(int j = 0; j < bookshelfUser.getNumColumn(); j++){
                    if(bookshelfUser.getObjectCard(i,j)!=null){
                    ObjectCard objectCardUser=new ObjectCard(bookshelfUser.getObjectCard(i,j).getId(),bookshelfUser.getObjectCard(i,j).getColor());
                    cellShelves[i][j]=new CellShelf(objectCardUser);}
                }
            }
            userClone.setBookshelf(new Bookshelf(cellShelves));
            userClone.setPersonalGoal(new PersonalGoalCard(user.getPersonalGoal().getId()));
            userClone.setPoints(user.getPoints());
            userClone.initializePointsToken(gameManager.getCommonGoals().size());
            for(int i=0;i<gameManager.getCommonGoals().size();i++)
                userClone.setPointsToken(user.getPointsToken(i),i);
            usersClone.add(userClone);
        }


        CellPlank[][] board=gameManager.getPlank().getBoard();
        CellPlank[][] boardClone=new CellPlank[gameManager.getPlank().getDIM()][gameManager.getPlank().getDIM()];
        for(int i = 0; i < gameManager.getPlank().getDIM(); i++) {
            for (int j = 0; j < gameManager.getPlank().getDIM(); j++) {
                if(board[i][j]!=null && board[i][j].getObjectCard()!=null){
                    ObjectCard objectCard=new ObjectCard(board[i][j].getObjectCard().getId(),board[i][j].getObjectCard().getColor());
                    boardClone[i][j]=new CellPlank(objectCard,i,j);
                }
            }
        }
        plankClone=new Plank();
        plankClone.setBoard(boardClone);
        commonGoalsClone=new ArrayList<>();
        for(CommonGoal commonGoal:gameManager.getCommonGoals())
            commonGoalsClone.add(new CommonGoal(commonGoal.getId(), commonGoal.getRule(),commonGoal.getTokenCardsClone()));

    }


    public String messageStateGame(){
        return new Gson().toJson(this);
    }
    public String messagePlank(){
        return new Gson().toJson(this.plankClone);
    }
    public String messageUser(){
        return new Gson().toJson(this.usersClone);
    }

    public ArrayList<User> getUsersClone() {
        return usersClone;
    }

    public Plank getPlankClone() {
        return plankClone;
    }

    public ArrayList<CommonGoal> getCommonGoalsClone() {
        return commonGoalsClone;
    }

    public String getActiveUser() {
        return activeUser;
    }
}
