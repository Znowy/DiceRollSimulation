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

    Color Brown;
    Color DarkRed;
    Color DarkGreen;

    Simple()
    {
        myFrame = new JFrame();
        diceButtons = new JButton[5];

        Brown = new Color(60, 30, 10);
        DarkRed = new Color(150, 0, 0);
        DarkGreen = new Color(0, 150, 0);

        scorePanel = new JPanel();
        scorePanel.setBackground(DarkRed);
        scorePanel.setPreferredSize(new Dimension(150, 600));
        scorePanel.setLayout(new GridLayout(18, 2));
        scorePanel.setBorder(BorderFactory.createLineBorder(Brown, 5));
        myFrame.add(scorePanel);

        organizePanel = new JPanel();
        organizePanel.setBackground(Color.gray);
        organizePanel.setPreferredSize(new Dimension(750, 600));
        organizePanel.setLayout(new GridLayout(4, 1));
        myFrame.add(organizePanel);

        dicePanel = new JPanel();
        dicePanel.setBackground(DarkRed);
        dicePanel.setBorder(BorderFactory.createLineBorder(Brown, 5));
        dicePanel.setLayout(new GridBagLayout());
        organizePanel.add(dicePanel);

        scoreLabels = new JLabel[36];

        scoreBox = new JComboBox<String>();
        scoreBox.setFont(new Font("Dialog", Font.PLAIN, 25));

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
                    UpdateScoreBox();
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
                UpdateScoreBox();
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

        myFrame.getContentPane().setBackground(Color.DARK_GRAY);
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
        UpdateScoreBox();
        UpdateScores(yahtzee.GetScores());
        UpdateHand(hand);
    }

    public void CreateScores()
    {
        scoreLabels[0] = new JLabel("1");
        scoreLabels[1] = new JLabel("0");
        scoreLabels[2] = new JLabel("2");
        scoreLabels[3] = new JLabel("0");
        scoreLabels[4] = new JLabel("3");
        scoreLabels[5] = new JLabel("0");
        scoreLabels[6] = new JLabel("4");
        scoreLabels[7] = new JLabel("0");
        scoreLabels[8] = new JLabel("5");
        scoreLabels[9] = new JLabel("0");
        scoreLabels[10] = new JLabel("6");
        scoreLabels[11] = new JLabel("0");

        scoreLabels[12] = new JLabel("Sub Total");
        scoreLabels[13] = new JLabel("0");
        scoreLabels[14] = new JLabel("Bonus");
        scoreLabels[15] = new JLabel("0");
        scoreLabels[16] = new JLabel("Upper Total");
        scoreLabels[17] = new JLabel("0");

        scoreLabels[18] = new JLabel("3K");
        scoreLabels[19] = new JLabel("0");
        scoreLabels[20] = new JLabel("4K");
        scoreLabels[21] = new JLabel("0");
        scoreLabels[22] = new JLabel("FH");
        scoreLabels[23] = new JLabel("0");
        scoreLabels[24] = new JLabel("SS");
        scoreLabels[25] = new JLabel("0");
        scoreLabels[26] = new JLabel("LS");
        scoreLabels[27] = new JLabel("0");
        scoreLabels[28] = new JLabel("Y");
        scoreLabels[29] = new JLabel("0");
        scoreLabels[30] = new JLabel("C");
        scoreLabels[31] = new JLabel("0");

        scoreLabels[32] = new JLabel("Lower Total");
        scoreLabels[33] = new JLabel("0");
        scoreLabels[34] = new JLabel("Grand Total");
        scoreLabels[35] = new JLabel("0");

        for (int i = 1; i < 36; i += 2)
            scoreLabels[i].setHorizontalAlignment(SwingConstants.RIGHT);

        for (int i = 0; i < 36; i++)
        {
            scoreLabels[i].setForeground(Color.WHITE);
            scorePanel.add(scoreLabels[i]);
        }
        scorePanel.setVisible(true);
    }

    public void ResetScoreLabels()
    {
        for (int i = 1; i < 36; i += 2)
            scoreLabels[i].setText("0");
    }

    public void UpdateScores(String[] scores)
    {
        for (int i = 1, j = 0; i < 36; i += 2, j++)
            scoreLabels[i].setText(scores[j]);
    }

    public void UpdateScoreBox()
    {
        int selectedIndex = scoreBox.getSelectedIndex();
        model = new DefaultComboBoxModel<String>(yahtzee.GetTempScores(hand));
        scoreBox.setModel(model);
        scoreBox.setSelectedIndex(selectedIndex);
    }

    public void CreateHand(Hand hand)
    {
        for (int i = 0; i < 5; i++)
        {
            diceButtons[i] = new JButton(images[hand.GetDie(i)-1]);
            diceButtons[i].setPreferredSize(new Dimension(100, 100));
            diceButtons[i].setBorder(BorderFactory.createLineBorder(DarkGreen, 3));
            diceButtons[i].setBorderPainted(false);
            diceButtons[i].setVerticalAlignment(SwingConstants.CENTER);
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