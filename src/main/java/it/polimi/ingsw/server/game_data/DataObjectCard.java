package it.polimi.ingsw.server.game_data;

import it.polimi.ingsw.server.model.object_card.Color;
/*This class represents the Object card from json file*/
public class DataObjectCard {
    private int id;

    private Color color;
    private int numObjectCard;

    public DataObjectCard(int id, Color color, int numObjectCard) {
        this.id = id;
        this.color = color;
        this.numObjectCard = numObjectCard;
    }

    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public int getNumObjectCard() {
        return numObjectCard;
    }
}
