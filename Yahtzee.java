import java.util.ArrayList;

public class Yahtzee {
    private static ArrayList<Element> cardList = new ArrayList<>();
    private static int subTotal = 0;
    private static int upperTotal = 0;
    private static int lowerTotal = 0;
    private static int grandTotal = 0;
    private static int bonus = 0;

    public Turn turn;

    Yahtzee() {
        createScorecard(6);
        this.turn = new Turn();
    }

    public String[] Play(Hand hand)
    {
        return GetTempScores(hand);
    }

    public void SubmitScore(int index)
    {
        cardList.get(index).setScore(turn.getScores().get(index));
        cardList.get(index).setUsed('y');
    }
    public String[] GetTempScores(Hand hand)
    {
        String[] scorecard = new String[13];
        String[] temp1 = turn.upperScoreCard(cardList, hand);
        for (int i = 0; i < 6; i++)
            scorecard[i] = temp1[i];
        String[] temp2 = turn.lowerScoreCard(cardList, hand);
        for (int i = 0; i < 7; i++)
            scorecard[i + 6] = temp2[i];
        return scorecard;
    }

    public String[] GetScores()
    {
        calculateScoreCard();
        return printScoreCard();
    }

    public Boolean IsUsed(int index)
    {
        return cardList.get(index).getUsed() == 'y';
    }

    public void ResetYahtzee()
    {
        cardList = new ArrayList<>();
        subTotal = 0;
        upperTotal = 0;
        lowerTotal = 0;
        grandTotal = 0;
        bonus = 0;
        turn.ResetTurn();
        createScorecard(6);
    }

    public static void createScorecard(int sides) {
        String name = "";
        char used = 'n';
        char section = 'u';
        int score = 0;
        for (int i = 0; i < 6 + 7; i++) {
            Element e = new Element(name, section, used, score);
            if (i < 6) {
                e.name = Integer.toString(i + 1);
                e.used = 'n';
                e.section = 'u';
                e.score = 0;
                cardList.add(i, e);
            } else if (i == 6) {
                e.name = "3K";
                e.used = 'n';
                e.section = 'l';
                e.score = 0;
                cardList.add(i, e);
            } else if (i == 6 + 1) {
                e.name = "4K";
                e.used = 'n';
                e.section = 'l';
                e.score = 0;
                cardList.add(i, e);
            } else if (i == 6 + 2) {
                e.name = "FH";
                e.used = 'n';
                e.section = 'l';
                e.score = 0;
                cardList.add(i, e);
            } else if (i == 6 + 3) {
                e.name = "SS";
                e.used = 'n';
                e.section = 'l';
                e.score = 0;
                cardList.add(i, e);
            } else if (i == 6 + 4) {
                e.name = "LS";
                e.used = 'n';
                e.section = 'l';
                e.score = 0;
                cardList.add(i, e);
            } else if (i == 6 + 5) {
                e.name = "Y";
                e.used = 'n';
                e.section = 'l';
                e.score = 0;
                cardList.add(i, e);
            } else if (i == 6 + 6) {
                e.name = "C";
                e.used = 'n';
                e.section = 'l';
                e.score = 0;
                cardList.add(i, e);
            }
        }
    }

    public static void calculateScoreCard() {
        subTotal = 0;
        for (int i = 0; i < 6; i++) {
            char used = cardList.get(i).getUsed();
            if (used == 'y') {
                subTotal += cardList.get(i).getScore();
            }
        }
        // bonus
        if (subTotal > 63)
            bonus = 35;
        else
            bonus = 0;
        // upper total
        upperTotal = subTotal + bonus;
        // lower total
        lowerTotal = 0;
        for (int i = 0; i < 7; i++) {
            char used = cardList.get(i + 6).getUsed();
            if (used == 'y') {
                lowerTotal += cardList.get(i + 6).getScore();
            }
        }
        // grand total
        grandTotal = upperTotal + lowerTotal;
    }

    public String[] printScoreCard() {
        String[] scoresTemp = new String[18];
        for (int i = 0; i < 6; i++)
            scoresTemp[i] = String.format("%s", cardList.get(i).getScore());
        scoresTemp[6] = String.format("%s", subTotal);
        scoresTemp[7] = String.format("%s", bonus);
        scoresTemp[8] = String.format("%s", upperTotal);
        for (int i = 6; i < 13; i++)
            scoresTemp[i+3] = String.format("%s", cardList.get(i).getScore());
        scoresTemp[16] = String.format("%s", lowerTotal);
        scoresTemp[17] = String.format("%s", grandTotal);
        return scoresTemp;
    }

    public int getNumSides() {
        return 6;
    }

    public int getNumDice() {
        return 5;
    }

    public boolean isFull() {
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getUsed() == 'n') {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Element> getCardList() {
        return cardList;
    }

    public void setCardList(ArrayList<Element> cardList) {
        Yahtzee.cardList = cardList;
    }
}