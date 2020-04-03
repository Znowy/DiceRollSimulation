import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Simple
{
    JFrame myFrame;
    final Hand hand;
    JPanel dicePanel;
    JButton[] diceButtons;
    ImageIcon[] images = new ImageIcon[] {
    new ImageIcon("Images/DieFace1.jpg"),
    new ImageIcon("Images/DieFace2.jpg"),
    new ImageIcon("Images/DieFace3.jpg"),
    new ImageIcon("Images/DieFace4.jpg"),
    new ImageIcon("Images/DieFace5.jpg"),
    new ImageIcon("Images/DieFace6.jpg")};

    Simple(Hand hand)
    {
        myFrame = new JFrame();
        diceButtons = new JButton[5];
        dicePanel = new JPanel();
        dicePanel.setBackground(Color.gray);
        myFrame.add(dicePanel);

        this.hand = hand;
        CreateHand(hand);

        JButton rollButton = new JButton("Roll");
        rollButton.setPreferredSize(new Dimension(100, 50));
        rollButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                hand.RollSelected(diceButtons[0].isBorderPainted(), diceButtons[1].isBorderPainted(), diceButtons[2].isBorderPainted(), diceButtons[3].isBorderPainted(), diceButtons[4].isBorderPainted());
                UpdateHand(hand);
            }
        });
        myFrame.add(rollButton);

        myFrame.setSize(800, 600);
        myFrame.setLayout(new FlowLayout());
        myFrame.setVisible(true);
    }

    public void CreateHand(Hand hand)
    {
        for (int i = 0; i < 5; i++)
        {
            diceButtons[i] = new JButton(images[hand.GetDie(i)-1]);
            diceButtons[i].setPreferredSize(new Dimension(100, 100));
            diceButtons[i].setBorder(BorderFactory.createBevelBorder(1, Color.RED, Color.RED));
            diceButtons[i].setBorderPainted(false);
            diceButtons[i].addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int buttonNumber = 0;
                    for(int i = 0; i < 6; i++)
                    {
                        if (e.getSource() == diceButtons[i])
                            {
                                buttonNumber = i;
                                break;
                            }
                    }

                    diceButtons[buttonNumber].setBorderPainted(!diceButtons[buttonNumber].isBorderPainted());
                }
            });
            dicePanel.add(diceButtons[i]);
        }
        dicePanel.setVisible(true);
    }

    public void UpdateHand(Hand hand)
    {
        for (int i = 0; i < 5; i++)
        {
            diceButtons[i].setIcon(images[hand.GetDie(i+1)-1]);
        }
    }
}