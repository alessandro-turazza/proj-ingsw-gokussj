package it.polimi.ingsw;

public class User {
    private String name;
    private int points;
    private PersonalGoalCard personalGoal;
    private Bookshelf bookshelf;

    public User(String name) {
        this.name = name;
        this.points = 0;
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

    public PersonalGoalCard getPersonalGoal() {
        return personalGoal;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    public void updatePointsAdjacenses(){
        for(int i = 0; i < Bookshelf.NUM_ROW; i++){
            for(int j = 0; j < Bookshelf.NUM_COLUMN; j++){

                if(bookshelf.getBookshelf()[i][j] != null){
                    int numAdjacenses = bookshelf.checkAdjacences(bookshelf.getBookshelf()[i][j],i,j);

                    if(numAdjacenses == 3)
                        points += 2;
                    else if(numAdjacenses == 4)
                        points += 3;
                    else if(numAdjacenses == 5)
                        points += 5;
                    else if(numAdjacenses >= 6)
                        points += 8;
                }

            }
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
}
