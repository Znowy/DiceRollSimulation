import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

/**
 * This program plays one round of yahtzee
 * This is the Turn class that controls the scoring of a turn
 * CPSC 224, Spring 2020
 * Programming Assignment #2
 * No sources to cite.
 *
 * @author Sophie Braun
 * 2/13/19
 */

public class Turn {
    //private static ArrayList<Integer> scores;
    public static ArrayList<Integer> scores = new ArrayList<>();
    //public int numSides = game.getNumSides();
    //public int numDie = game.getNumDice();
    public ArrayList<Element> cardList = new ArrayList<Element>();
    public static String lowerScorecard = "";
    public static String upperScorecard = "";

    /**
     * Constructor that tells the user they have an empty hand
     */
    Turn() {

    }

    public void ResetTurn()
    {
        scores = new ArrayList<>();
        cardList = new ArrayList<Element>();
        lowerScorecard = "";
        upperScorecard = "";
    }

    /**
     * this function represents the upper score card of the game Yahtzee
     * @return a String showing the upperScoreCard results
     */
    public String[] upperScoreCard(ArrayList<Element> cList, Hand hand) {
        String[] upperScorecard = new String[6];
        for (int dieValue = 1; dieValue <= 6; dieValue++) {
            int count = 0;
            for (int diePosition = 0; diePosition < 5; diePosition++) {
                if (hand.GetDie(diePosition) == dieValue) {
                    count++;
                }
            }
            if (cList.get(dieValue - 1).getUsed() == 'n') {
                upperScorecard[dieValue - 1] = "Score " + dieValue * count + " on the " + dieValue + " line";
                scores.add(dieValue - 1, dieValue * count);
            }
            else
            {
                upperScorecard[dieValue - 1] = "Score " + dieValue * count + " on the " + dieValue + " line [used]";
            }
        }
        return upperScorecard;
    };

    /**
     * represents the lower score card of the game Yahtzee
     * @return a String of the lowerScoreCard possible options
     */
    public String[] lowerScoreCard(ArrayList<Element> cList, Hand hand) {
        String[] lowerScorecard = new String[7];

        if (cList.get(6).getUsed() == 'n') {
            if (maxOfAKindFound(hand) >= 3) {
                lowerScorecard[0] = "Score " + sumOfDie(hand) + " on the " + "3 of a Kind line\n";
                scores.add(6, sumOfDie(hand));
            } else {
                lowerScorecard[0] = "Score 0 on the 3 of a Kind line\n";
                scores.add(6, 0);
            }
        }
        else
        {
            if (maxOfAKindFound(hand) >= 3) {
                lowerScorecard[0] = "Score " + sumOfDie(hand) + " on the " + "3 of a Kind line [used]\n";
            } else {
                lowerScorecard[0] = "Score 0 on the 3 of a Kind line [used]\n";
            }
        }

        if (cList.get(6 + 1).getUsed() == 'n') {
            if (maxOfAKindFound(hand) >= 4) {
                lowerScorecard[1] = "Score " + sumOfDie(hand) + " on the " + "4 of a Kind line\n";
                scores.add(6 + 1, sumOfDie(hand));
            } else {
                lowerScorecard[1] = "Score 0 on the 4 of a Kind line\n";
                scores.add(6 + 1, 0);
            }
        }
        else
        {
            if (maxOfAKindFound(hand) >= 4) {
                lowerScorecard[1] = "Score " + sumOfDie(hand) + " on the " + "4 of a Kind line [used]\n";
            } else {
                lowerScorecard[1] = "Score 0 on the 4 of a Kind line [used]\n";
            }
        }

        if (cList.get(6 + 2).getUsed() == 'n') {
            if (fullHouseFound(hand)) {
                lowerScorecard[2] = "Score 25 on the Full House line\n";
                scores.add(6 + 2, 25);
            } else {
                lowerScorecard[2] = "Score 0 on the Full House line\n";
                scores.add(6 + 2, 0);
            }
        }
        else
        {
            if (fullHouseFound(hand)) {
                lowerScorecard[2] = "Score 25 on the Full House line [used]\n";
            } else {
                lowerScorecard[2] = "Score 0 on the Full House line [used]\n";
            }
        }

        if (cList.get(6 + 3).getUsed() == 'n') {
            if (maxStraightFound(hand) >= 4) {
                lowerScorecard[3] = "Score 30 on the Small Straight line\n";
                scores.add(6 + 3, 30);
            } else {
                lowerScorecard[3] = "Score 0 on the Small Straight line\n";
                scores.add(6 + 3, 0);
            }
        }
        else
        {
            if (maxStraightFound(hand) >= 4) {
                lowerScorecard[3] = "Score 30 on the Small Straight line [used]\n";
            } else {
                lowerScorecard[3] = "Score 0 on the Small Straight line [used]\n";
            }
        }

        if (cList.get(6 + 4).getUsed() == 'n') {
            if (maxStraightFound(hand) >= 5) {
                lowerScorecard[4] = "Score 40 on the Large Straight line\n";
                scores.add(6 + 4, 40);
            } else {
                lowerScorecard[4] = "Score 0 on the Large Straight line\n";
                scores.add(6 + 4, 0);
            }
        }
        else
        {
            if (maxStraightFound(hand) >= 5) {
                lowerScorecard[4] = "Score 40 on the Large Straight line [used]\n";
            } else {
                lowerScorecard[4] = "Score 0 on the Large Straight line [used]\n";
            }
        }

        if (cList.get(6 + 5).getUsed() == 'n') {
            if (maxOfAKindFound(hand) >= 5) {
                lowerScorecard[5] = "Score 50 on the Yahtzee line\n";
                scores.add(6 + 5, 50);
            } else {
                lowerScorecard[5] = "Score 0 on the Yahtzee line\n";
                scores.add(6 + 5, 0);
            }
        }
        else
        {
            if (maxOfAKindFound(hand) >= 5) {
                lowerScorecard[5] = "Score 50 on the Yahtzee line [used]\n";
            } else {
                lowerScorecard[5] = "Score 0 on the Yahtzee line [used]\n";
            }
        }

        if (cList.get(6 + 6).getUsed() == 'n') {
            lowerScorecard[6] = "Score " + sumOfDie(hand) + " on the Chance line";
            scores.add(6 + 6, sumOfDie(hand));
        }
        else
        {
            lowerScorecard[6] = "Score " + sumOfDie(hand) + " on the Chance line [used]";
        }

        return lowerScorecard;
    }

    /**
     * determines the max number of a kind is found in a hand
     * @return maxCount - the maximum number of repeated numbers in hand
     */
    private int maxOfAKindFound(Hand hand) {
        int max = 0;
        int count;
        for (int dieValue = 1; dieValue <= 6; dieValue++) {
            count = 0;
            for (int diePosition = 0; diePosition < 5; diePosition++) {
                if (hand.GetSortedDie(diePosition) == dieValue) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    /**
     * finds the max number of a straight
     * @return maxLength - the max length of a straight
     */

    private int maxStraightFound(Hand hand) {
        int maxLength = 1;
        for (int i = 0; i < 4; i++)
        {
            if (hand.GetSortedDie(i) + 1 == hand.GetSortedDie(i + 1)) {
                maxLength++;
            }
            else if (hand.GetSortedDie(i) == hand.GetSortedDie(i+1))
            {

            }
            else if (hand.GetSortedDie(i) == 1 && hand.GetSortedDie(i+1) == 3)
            {

            }
            else
            {
                return maxLength;
            }
        }
        return maxLength;
    }

    /**
     * checks for a full house
     * @return true if there is a full house, false if not
     */
    private boolean fullHouseFound(Hand hand) {
        boolean fullHouse = false;
        boolean threeOfKind = false;
        boolean twoOfKind = false;
        for (int dieNum = 1; dieNum <= 6; dieNum++) {
            int count = 0;
            for (int diePosition = 0; diePosition < 5; diePosition++) {
                if (hand.GetSortedDie(diePosition) == dieNum) {
                    count++;
                }
            }
            if (count == 2) {
                twoOfKind = true;
            }
            if (count == 3) {
                threeOfKind = true;
            }
        }
        if (twoOfKind && threeOfKind) {
            fullHouse = true;
        }
        return fullHouse;
    }

    /**
     * Sums the dice in the hand
     * @return the sum of the dice
     */
    private int sumOfDie(Hand hand) {
        int sum = 0;
        for (int diePosition = 0; diePosition < 5; diePosition++)
        {
            sum += hand.GetSortedDie(diePosition);
        }
        return sum;
    }

    public static String getLowerScorecard() {
        return lowerScorecard;
    }

    public void setLowerScorecard(String lowerScorecard) {
        this.lowerScorecard = lowerScorecard;
    }
/*
    public ArrayList<String> getUpperScorecard() {
        return upperScorecard;
    }
*/
    public void setUpperScorecard(String upperScorecard) {
        this.upperScorecard = upperScorecard;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }
/*
    @Override
    public String toString() {
        return upperScoreCard(upperScorecard) + lowerScoreCard(lowerScorecard);
    }*/
}