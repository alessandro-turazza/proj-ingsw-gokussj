package it.polimi.ingsw.server.model.user.personal_goal;

import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;

import java.util.ArrayList;

/**
 * This class represents a personal goal card
 */
public class PersonalGoalCard {
    private int id;
    private ArrayList<Costraints> costraints;

    public PersonalGoalCard(int id){
        this.id = id;
        costraints = new ArrayList<>();
    }

    public PersonalGoalCard(int id, ArrayList<Costraints> costraints){
        this.id = id;
        this.costraints = new ArrayList<>();

        for(Costraints c: costraints){
            this.costraints.add(new Costraints(c.getRow(), c.getColumn(), c.getColor()));
        }
    }


    public int getId() {
        return id;
    }


    public ArrayList<Costraints> getCostraints() {
        return costraints;
    }

    /**
     * This method controls how many cards in bookshelf respect the personal goal costraints
     */
    public int checkRule(Bookshelf bookshelf){
        int counter = 0;

        for (Costraints costraint : costraints) {
            if (bookshelf.getBookshelf()[costraint.getRow()][costraint.getColumn()] != null && bookshelf.getBookshelf()[costraint.getRow()][costraint.getColumn()].getObjectCard().getColor() == costraint.getColor())
                counter++;
        }

        return counter;
    }



}
