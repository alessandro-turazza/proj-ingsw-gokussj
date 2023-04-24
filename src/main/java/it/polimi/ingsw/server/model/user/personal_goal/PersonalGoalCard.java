package it.polimi.ingsw.server.model.user.personal_goal;

import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;

import java.util.ArrayList;

public class PersonalGoalCard {
    private int id;
    private ArrayList<Costraints> costraints;

    public PersonalGoalCard(int id){
        this.id = id;
        costraints = new ArrayList<>();
    }


    public int getId() {
        return id;
    }


    public ArrayList<Costraints> getCostraints() {
        return costraints;
    }

    public int checkRule(Bookshelf bookshelf){
        int counter = 0;

        for (Costraints costraint : costraints) {
            if (bookshelf.getBookshelf()[costraint.getRow()][costraint.getColumn()] != null && bookshelf.getBookshelf()[costraint.getRow()][costraint.getColumn()].getObjectCard().getColor() == costraint.getColor())
                counter++;
        }

        return counter;
    }



}
