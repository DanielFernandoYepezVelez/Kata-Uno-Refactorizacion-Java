package TennisClass;

import Interfaces.TennisGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TennisGame1 implements TennisGame {
    
    private Integer scorePlayer1 = 0;
    private Integer scorePlayer2 = 0;
    private final String player1Name;
    private final String player2Name;

    private static final Map<Integer, String> SCORES = new HashMap<>();

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = Objects.requireNonNull(player1Name, "Player 1 Can't Be Null");
        this.player2Name = Objects.requireNonNull(player2Name, "Player 2 Can't Be Null");
    }

    public void wonPoint(String playerName) {
        if (this.player1Name.equalsIgnoreCase(playerName)) {
            scorePlayer1 += 1;
            return;
        }

        scorePlayer2 += 1;
    }

    private String scoreEqual() {
        SCORES.put(0, "Love-All");
        SCORES.put(1, "Fifteen-All");
        SCORES.put(2, "Thirty-All");
        SCORES.put(3, "Deuce");
        SCORES.put(4, "Deuce");

        return SCORES.get(scorePlayer1);
    }

    private String scoreHighestOrEqualFour() {
        int minusResult = scorePlayer1 - scorePlayer2;

        if (minusResult == 1) {
            return "Advantage " + this.player1Name;
        }

        if (minusResult == (-1)) {
            return "Advantage " + this.player2Name;
        }

        return minusResult >= 2 ? "Win for " + this.player1Name : "Win for " + this.player2Name;
    }

    private String scoreTemp() {
        Integer tempScore = 0;
        StringBuilder scoreBuilder = new StringBuilder();

        for (int i = 1; i < 3; i++) {
            tempScore = i == 1 ? scorePlayer1 : scorePlayer2;

            if (i != 1) {
                scoreBuilder.append("-");
            }

            SCORES.put(0, "Love");
            SCORES.put(1, "Fifteen");
            SCORES.put(2, "Thirty");
            SCORES.put(3, "Forty");

            scoreBuilder.append(SCORES.get(tempScore));
        }

        return scoreBuilder.toString();
    }

    public String getScore() {
        if (scorePlayer1.equals(scorePlayer2)) {
            return this.scoreEqual();
        }

        return (scorePlayer1 >= 4 || scorePlayer2 >= 4) ? this.scoreHighestOrEqualFour() : this.scoreTemp();
    }
}
