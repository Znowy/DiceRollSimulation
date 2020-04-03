import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        final Hand myHand = new Hand();

        EventQueue.invokeLater(()-> 
        {
            new Simple(myHand);
        });
    }
}