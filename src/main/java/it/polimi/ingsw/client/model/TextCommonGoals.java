package it.polimi.ingsw.client.model;

import java.util.ArrayList;

public class TextCommonGoals {
    private ArrayList<TextCommonGoal> textCommonGoals;

    public TextCommonGoals(){
        textCommonGoals=new ArrayList<>();
        textCommonGoals.add(new TextCommonGoal(1,"Sei gruppi separati formati ciascuno da due tessere adiacenti dello stesso tipo. Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo"));
        textCommonGoals.add((new TextCommonGoal(2,"Cinque tessere dello stesso tipo che formano una diagonale.")));
        textCommonGoals.add((new TextCommonGoal(3,"Quattro gruppi separati formati ciascuno da quattro tessere adiacenti dello stesso tipo.\n" +
                "Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.")));
        textCommonGoals.add(new TextCommonGoal(4,"Quattro righe formate ciascuna da 5 tessere di uno, due o tre tipi differenti. Righe diverse possono avere combinazioni diverse di tipi di tessere."));
        textCommonGoals.add((new TextCommonGoal(5,"Quattro tessere dello stesso tipo ai quattro angoli della Libreria.")));
        textCommonGoals.add((new TextCommonGoal(6,"Due colonne formate ciascuna da 6 diversi tipi di tessere.")));
        textCommonGoals.add((new TextCommonGoal(7,"Due gruppi separati di 4 tessere dello stesso tipo che formano un quadrato 2x2. Le tessere dei due gruppi devono essere dello stesso tipo.")));
        textCommonGoals.add((new TextCommonGoal(8,"Due righe formate ciascuna da 5 diversi tipi di tessere.")));
        textCommonGoals.add(new TextCommonGoal(9,"Tre colonne formate ciascuna da 6 tessere di uno, due o tre tipi differenti. Colonne diverse possono avere combinazioni diverse di tipi di tessere."));
        textCommonGoals.add((new TextCommonGoal(10,"Cinque tessere dello stesso tipo che formano una X.")));
        textCommonGoals.add((new TextCommonGoal(11,"Otto tessere dello stesso tipo. Non ci sono restrizioni sulla posizione di queste tessere.")));
        textCommonGoals.add(new TextCommonGoal(12,"Cinque colonne di altezza crescente o decrescente: a partire dalla prima colonna\n" +
                "a sinistra o a destra, ogni colonna successiva deve essere formata da una tessera in più. Le tessere possono essere di qualsiasi tipo."));

    }

    public ArrayList<TextCommonGoal> getTextCommonGoals() {
        return textCommonGoals;
    }
}
