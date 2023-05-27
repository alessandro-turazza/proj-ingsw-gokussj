package it.polimi.ingsw.server.model.object_card;

/**
 * This class represents the Object Card in the game
 */
public class ObjectCard {
    private int id;
    private Color color;

    public ObjectCard(int id, Color color) {
        this.id = id;
        this.color = color;
    }

    /**
     * This method converts a color to a string
     */
    public static Color convertToColor(String s){
        if(s.equals("YELLOW"))
            return Color.YELLOW;
        if(s.equals("BLUE"))
            return Color.BLUE;
        if(s.equals("PINK"))
            return Color.PINK;
        if(s.equals("LIGHT_BLUE"))
            return Color.LIGHT_BLUE;
        if(s.equals("GREEN"))
            return Color.GREEN;
        if(s.equals("WHITE"))
            return Color.WHITE;
        return null;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
