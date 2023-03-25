package it.polimi.ingsw;

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

        for(int i = 0; i < costraints.size(); i++){
            if(bookshelf.getBookshelf()[costraints.get(i).getRow()][costraints.get(i).getColumn()] != null && bookshelf.getBookshelf()[costraints.get(i).getRow()][costraints.get(i).getColumn()].getObjectCard().getColor() == costraints.get(i).getColor())
                counter++;
        }

        return counter;
    }



}
