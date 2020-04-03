import java.util.Random;

public class Hand
{
    public int die1;
    public int die2;
    public int die3;
    public int die4;
    public int die5;

    Hand()
    {
        die1 = die2 = die3 = die4 = die5 = 1;
    }

    public void Roll()
    {
        Random rand = new Random();

        die1 = rand.nextInt(6) + 1;
        die2 = rand.nextInt(6) + 1;
        die3 = rand.nextInt(6) + 1;
        die4 = rand.nextInt(6) + 1;
        die5 = rand.nextInt(6) + 1;
    }

    public void RollSelected(Boolean b1, Boolean b2, Boolean b3, Boolean b4, Boolean b5)
    {
        Random rand = new Random();

        if (!b1)
            die1 = rand.nextInt(6) + 1;
        if (!b2)
            die2 = rand.nextInt(6) + 1;
        if (!b3)
            die3 = rand.nextInt(6) + 1;
        if (!b4)
            die4 = rand.nextInt(6) + 1;
        if (!b5)
            die5 = rand.nextInt(6) + 1;
    }

    public int GetDie(int num)
    {
        if (num == 1)
            return die1;
        else if (num == 2)
            return die2;
        else if (num == 3)
            return die3;
        else if (num == 4)
            return die4;
        else //(num == 5)
            return die5;
    }
}