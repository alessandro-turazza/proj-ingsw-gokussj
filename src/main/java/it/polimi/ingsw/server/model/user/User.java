package it.polimi.ingsw.server.model.user;

import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;
import it.polimi.ingsw.server.model.user.bookshelf.CellShelf;
import it.polimi.ingsw.server.model.user.personal_goal.PersonalGoalCard;
import it.polimi.ingsw.server.model.object_card.ObjectCard;

import java.util.ArrayList;

public class User {
    private String name;
    private int points;
    private ArrayList<Integer> pointsToken;
    private PersonalGoalCard personalGoal;
    private Bookshelf bookshelf;

    public void setName(String name) {
        this.name = name;
    }
    public void initializePointsToken(int numToken){
        for(int i=0;i<numToken;i++)
            pointsToken.add(0);
    }
    public void setPointsToken(int points,int index) {
        this.pointsToken.set(index,points);
    }
    public Integer getPointsToken(int index) {
        if(index>=0 && index<pointsToken.size()) return pointsToken.get(index);
        else return null;
    }

    public void setBookshelf(Bookshelf bookshelf) {
        this.bookshelf = bookshelf;
    }

    public User(String name) {
        this.name = name;
        this.pointsToken = new ArrayList<>();
        this.points=0;
        this.bookshelf = new Bookshelf();
    }

    public void setPersonalGoal(PersonalGoalCard personalGoal) {
        this.personalGoal = personalGoal;
    }

    public String getName() {
        return name;
    }
    public int getPoints() {
        return points;
    }

    public int addTokenPointsToPoints() {
        int result=0;
        for(Integer point:pointsToken)
            result+=point;
        points+=result;
        return result;
    }

    public PersonalGoalCard getPersonalGoal() {
        return personalGoal;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    public void updatePointsAdjacenses(){

        ArrayList<Integer> numAdj = bookshelf.numberAdjacenses();

        for (Integer integer : numAdj) {
            if (integer == 3)
                points += 2;
            else if (integer == 4)
                points += 3;
            else if (integer == 5)
                points += 5;
            else if (integer >= 6)
                points += 8;

        }
    }

    public void checkPersonalGoal(){
        int numPersGoalAchieved = personalGoal.checkRule(this.bookshelf);

        switch(numPersGoalAchieved){
            case 1: points += 1;
                break;
            case 2: points += 2;
                break;
            case 3: points += 4;
                break;
            case 4: points += 6;
                break;
            case 5: points += 9;
                break;
            case 6: points += 12;
                break;
        }
    }

    public void dropObjectCard(ObjectCard card, int column) throws Exception {
        //Per la parte relativa al modello la drop (e la drag) necessita della colonna come parametro che verrà passata dal controller successivamente da CLI o GUI
        this.bookshelf.insertBookshelf(card,column);
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public User getUserClone(){
        User userClone=new User(this.getName());
        Bookshelf bookshelfUser=this.getBookshelf();
        CellShelf[][] cellShelves=new CellShelf[bookshelfUser.getNumRow()][bookshelfUser.getNumColumn()];
        for(int i = 0; i < bookshelfUser.getNumRow(); i++){
            for(int j = 0; j < bookshelfUser.getNumColumn(); j++){
                if(bookshelfUser.getObjectCard(i,j)!=null){
                    ObjectCard objectCardUser=new ObjectCard(bookshelfUser.getObjectCard(i,j).getId(),bookshelfUser.getObjectCard(i,j).getColor());
                    cellShelves[i][j]=new CellShelf(objectCardUser);}
            }
        }
        userClone.setBookshelf(new Bookshelf(cellShelves));
        userClone.setPersonalGoal(new PersonalGoalCard(this.getPersonalGoal().getId(),this.getPersonalGoal().getCostraints()));
        userClone.setPoints(this.getPoints());
        userClone.initializePointsToken(this.pointsToken.size());
        for(int i=0;i<this.pointsToken.size();i++)
            userClone.setPointsToken(this.getPointsToken(i),i);
        return userClone;
    }

    public ArrayList<Integer> getPointsToken() {
        return pointsToken;
    }

}
