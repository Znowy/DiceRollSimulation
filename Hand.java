import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Hand
{
    private ArrayList<Integer> handArray = new ArrayList<Integer>();

    Hand()
    {
        InitializeDice();
        Roll();
    }

    public void InitializeDice()
    {
        for (int i = 0; i < 5; i++)
            handArray.add(i, 1);
    }

    public void Roll()
    {
        Random rand = new Random();

        for (int i = 0; i < 5; i++)
            handArray.set(i, rand.nextInt(6) + 1);
    }

    public void RollSelected(Boolean b1, Boolean b2, Boolean b3, Boolean b4, Boolean b5)
    {
        Random rand = new Random();

        if (!b1)
            handArray.set(0, rand.nextInt(6) + 1);
        if (!b2)
            handArray.set(1, rand.nextInt(6) + 1);
        if (!b3)
            handArray.set(2, rand.nextInt(6) + 1);
        if (!b4)
            handArray.set(3, rand.nextInt(6) + 1);
        if (!b5)
            handArray.set(4, rand.nextInt(6) + 1);
    }

    public int GetDie(int num)
    {
        return handArray.get(num);
    }

    public int GetSortedDie(int num)
    {
        ArrayList<Integer> tempArray = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++)
            tempArray.add(handArray.get(i));
        Collections.sort(tempArray);
        return tempArray.get(num);
    }

    public ArrayList<Integer> GetHand()
    {
        return handArray;
    }
}