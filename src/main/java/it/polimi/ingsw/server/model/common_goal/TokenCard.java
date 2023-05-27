package it.polimi.ingsw.server.model.common_goal;

/**
 * This class that represents the tokens cards (points for the common goals)
 */
public class TokenCard {
    private int points;
    private int series;


    public TokenCard(int points, int series) {
        this.points = points;
        this.series = series;
    }

    public int getPoints() {
        return points;
    }

    public int getSeries() {
        return series;
    }
}
