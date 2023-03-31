package it.polimi.ingsw.common_goal;

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
