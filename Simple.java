import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Simple
{
    JFrame myFrame;
    Hand hand;
    Yahtzee yahtzee;
    JPanel dicePanel;
    JPanel scorePanel;
    JPanel organizePanel;
    JButton rollButton;
    JButton submitButton;
    JButton[] diceButtons;
    JLabel[] scoreLabels;
    ImageIcon[] images = new ImageIcon[] {
    new ImageIcon("Images/DieFace1.jpg"),
    new ImageIcon("Images/DieFace2.jpg"),
    new ImageIcon("Images/DieFace3.jpg"),
    new ImageIcon("Images/DieFace4.jpg"),
    new ImageIcon("Images/DieFace5.jpg"),
    new ImageIcon("Images/DieFace6.jpg")};

    String scoreTemplate = new String("Score: " + 0 + ", on the " + "1" + " line");
    String[] scoreStrings = new String[] {
        new String("Score: " + 0 + ", on the 1 line"), 
        new String("Score: " + 0 + ", on the 2 line"), 
        new String("Score: " + 0 + ", on the 3 line"), 
        new String("Score: " + 0 + ", on the 4 line"), 
        new String("Score: " + 0 + ", on the 5 line"), 
        new String("Score: " + 0 + ", on the 6 line"), 
        new String("Score: " + 0 + ", on the 3 of a Kind line"), 
        new String("Score: " + 0 + ", on the 4 of a Kind line"), 
        new String("Score: " + 0 + ", on the Full House line"), 
        new String("Score: " + 0 + ", on the Small Straight line"), 
        new String("Score: " + 0 + ", on the Large Straight line"), 
        new String("Score: " + 0 + ", on the Yahtzee line"), 
        new String("Score: " + 0 + ", on the Chance line")};

    DefaultComboBoxModel<String> model;

    JComboBox<String> scoreBox;

    int rollCount = 0;

    Simple()
    {
        myFrame = new JFrame();
        diceButtons = new JButton[5];

        scorePanel = new JPanel();
        scorePanel.setBackground(Color.gray);
        scorePanel.setPreferredSize(new Dimension(150, 600));
        scorePanel.setLayout(new GridLayout(18, 1));
        myFrame.add(scorePanel);

        organizePanel = new JPanel();
        organizePanel.setBackground(Color.gray);
        organizePanel.setPreferredSize(new Dimension(750, 600));
        organizePanel.setLayout(new GridLayout(4, 1));
        myFrame.add(organizePanel);

        dicePanel = new JPanel();
        dicePanel.setBackground(Color.gray);
        organizePanel.add(dicePanel);

        scoreLabels = new JLabel[18];

        scoreBox = new JComboBox<String>();
        scoreBox.setFont(new Font("Dialog", Font.PLAIN, 25));
        //model = new DefaultComboBoxModel<String>(scoreStrings);

        organizePanel.add(scoreBox);

        this.hand = new Hand();
        this.yahtzee = new Yahtzee();
        CreateHand(hand);
        CreateScores();
        hand.Roll();
        UpdateHand(hand);

        model = new DefaultComboBoxModel<String>(yahtzee.Play(hand));
        scoreBox.setModel(model);

        rollButton = new JButton("Roll");
        rollButton.setPreferredSize(new Dimension(100, 50));
        rollButton.setFont(new Font("Dialog", Font.PLAIN, 50));
        rollButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (rollCount < 2)
                {
                    hand.RollSelected(diceButtons[0].isBorderPainted(), diceButtons[1].isBorderPainted(), diceButtons[2].isBorderPainted(), diceButtons[3].isBorderPainted(), diceButtons[4].isBorderPainted());
                    UpdateHand(hand);
                    model = new DefaultComboBoxModel<String>(yahtzee.Play(hand));
                    scoreBox.setModel(model);
                    rollCount++;
                }
            }
        });
        organizePanel.add(rollButton);

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 50));
        submitButton.setFont(new Font("Dialog", Font.PLAIN, 50));
        submitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (!yahtzee.IsUsed(scoreBox.getSelectedIndex()))
                {
                yahtzee.SubmitScore(scoreBox.getSelectedIndex());
                rollCount = 0;
                hand.Roll();
                for(int i = 0; i < 5; i++)
                    diceButtons[i].setBorderPainted(false);
                UpdateHand(hand);
                model = new DefaultComboBoxModel<String>(yahtzee.GetTempScores(hand));
                scoreBox.setModel(model);
                UpdateScores(yahtzee.GetScores());
                }
                if (yahtzee.isFull())
                {
                    JOptionPane.showMessageDialog(myFrame, "Game Over, play again?");
                    yahtzee.ResetYahtzee();
                    ResetSimple();
                }
            }
        });
        organizePanel.add(submitButton);

        myFrame.setSize(1000, 700);
        myFrame.setLayout(new FlowLayout());
        myFrame.setVisible(true);
    }

    public void ResetSimple()
    {
        hand.Roll();
        for(int i = 0; i < 5; i++)
            diceButtons[i].setBorderPainted(false);
        ResetScoreLabels();
        model = new DefaultComboBoxModel<String>(yahtzee.GetTempScores(hand));
        scoreBox.setModel(model);
        UpdateScores(yahtzee.GetScores());
        UpdateHand(hand);
    }

    public void CreateScores()
    {
        scoreLabels[0] = new JLabel("3K                      0");
        scoreLabels[1] = new JLabel("4K                      0");
        scoreLabels[2] = new JLabel("FH                      0");
        scoreLabels[3] = new JLabel("SS                      0");
        scoreLabels[4] = new JLabel("LS                      0");
        scoreLabels[5] = new JLabel("Y                      0");
        scoreLabels[6] = new JLabel("C                      0");

        scoreLabels[7] = new JLabel("Sub Total         0");
        scoreLabels[8] = new JLabel("Bonus               0");
        scoreLabels[9] = new JLabel("Upper Total      0");

        scoreLabels[10] = new JLabel("1                      0");
        scoreLabels[11] = new JLabel("2                      0");
        scoreLabels[12] = new JLabel("3                      0");
        scoreLabels[13] = new JLabel("4                      0");
        scoreLabels[14] = new JLabel("5                      0");
        scoreLabels[15] = new JLabel("6                      0");

        scoreLabels[16] = new JLabel("Lower Total     0");
        scoreLabels[17] = new JLabel("Grand Total      0");

        for (int i = 0; i < 18; i++)
            scorePanel.add(scoreLabels[i]);
        scorePanel.setVisible(true);
    }

    public void ResetScoreLabels()
    {
        scoreLabels[0].setText("3K                      0");
        scoreLabels[1].setText("4K                      0");
        scoreLabels[2].setText("FH                      0");
        scoreLabels[3].setText("SS                      0");
        scoreLabels[4].setText("LS                      0");
        scoreLabels[5].setText("Y                      0");
        scoreLabels[6].setText("C                      0");

        scoreLabels[7].setText("Sub Total         0");
        scoreLabels[8].setText("Bonus               0");
        scoreLabels[9].setText("Upper Total      0");

        scoreLabels[10].setText("1                      0");
        scoreLabels[11].setText("2                      0");
        scoreLabels[12].setText("3                      0");
        scoreLabels[13].setText("4                      0");
        scoreLabels[14].setText("5                      0");
        scoreLabels[15].setText("6                      0");

        scoreLabels[16].setText("Lower Total     0");
        scoreLabels[17].setText("Grand Total      0");
    }

    public void UpdateScores(String[] scores)
    {
        for (int i = 0; i < 18; i++)
            scoreLabels[i].setText(scores[i]);
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
                    for(int i = 0; i < 5; i++)
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
            diceButtons[i].setIcon(images[hand.GetDie(i)-1]);
        }
    }
}