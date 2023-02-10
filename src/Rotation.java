/* Rotation.java

    Creates Rotation using the Die class.

    Built in CS2
    By Jackson Deutch
    Sept. 2022 @ Menlo School
*/

import javax.swing.*;
import java.awt.*;
import java.lang.String;
import java.util.Scanner;

public class Rotation
{
    /** Instance Variables **/
    private Die d1;
    private Die d2;
    private int val1;
    private int val2;
    private int sum;
    private Image die1;
    private Image die2;
    private Image die3;
    private Image die4;
    private Image die5;
    private Image die6;
    private Image[] dieImages;
    private boolean[] rolls;
    private boolean isGameWon;
    boolean isMinBonusUsed;
    boolean isMaxBonusUsed;
    private RotationViewer observer;

    public Rotation()
    {
        // Creates 2 default 6 sided die
        d1 = new Die();
        d2 = new Die();

        // Game variables
        isGameWon = false;
        isMinBonusUsed = false;
        isMaxBonusUsed = false;

        // Array holding which numbers have been rolled
        rolls = new boolean[12];
        for (int i = 0; i < 11; i++)
        {
            rolls[i] = false;
        }

        // Initialize viewer/observer
        observer = new RotationViewer(this);

        // Initialize the die images in an array
        dieImages = new Image[6];

        die1 = new ImageIcon("Resources/dice1.png").getImage();
        die2 = new ImageIcon("Resources/dice2.png").getImage();
        die3 = new ImageIcon("Resources/dice3.png").getImage();
        die4 = new ImageIcon("Resources/dice4.png").getImage();
        die5 = new ImageIcon("Resources/dice5.png").getImage();
        die6 = new ImageIcon("Resources/dice6.png").getImage();

        dieImages[0] = die1;
        dieImages[1] = die2;
        dieImages[2] = die3;
        dieImages[3] = die4;
        dieImages[4] = die5;
        dieImages[5] = die6;
    }

    // Prints instructions into console
    public void printInstructions()
    {
        System.out.println("Welcome to Rotation! The game is simple.");
        System.out.println("The objective of the game is to roll 2 die " +
                "and get all 11 combinations possible when " +
                "taking their sum with as few rolls as " +
                "possible.");
        System.out.println("If you need to get a high or low number, " +
                "you can also get a bonus by typing in " +
                "either 'max' or 'min'. You get one of each. " +
                "Use them wisely!");
        System.out.println("That's it! Make sure to compare your score with " +
                "your friends. Good rolling!");
        System.out.println();
        System.out.println("-----");
        System.out.println();
    }

    /**
     * Counts the number of 'false' elements in rolls
     * Returns true if counter is 0 (all elements in array are true so game over)
     * Returns false if counter > 0 (at least one element still false so game not over)
     */
    public boolean isGameOver()
    {
        int counter = 0;

        // Check if game has been won by counting number of 'false' in rolls
        for (int i = 0; i < 11; i++)
        {
            if (rolls[i] == false)
            {
                counter++;
            }
        }
        if (counter == 0)
        {
            isGameWon = true;
            return true;
        }
        return false;
    }

    /**
     * Sets up regular round without bonuses used
     * Rolls dice, calculates sum, then determines if sum has been found
     */
    public void playRound()
    {

        System.out.println();

        // Determine what round it is
        int roundNumber = d1.getTimesRolled() + 1;
        System.out.println("Round " + roundNumber);

        // Roll dice and print sum
        System.out.println("Rolling the first die...");
        System.out.println("Rolling the second die...");
        val1 = d1.roll();
        val2 = d2.roll();
        sum = val1 + val2;
        System.out.println("The sum is " + sum);

        // Determine if sum has been found or not, then update rolls
        if (rolls[sum - 2] == false)
        {
            System.out.println("There is an open spot! You filled a sum!");
        }
        else
        {
            System.out.println("Uh oh! Looks like you have already rolled " +
                    "this number. Try again!");
        }
        rolls[sum - 2] = true;
    }

    /**
     * Sets up round with minimum bonus used
     * Rolls dice 4 times, then takes minimum of the rolls
     * Calculates sum and determines if found or not
     */
    public void playMinRound()
    {
        System.out.println();

        // Determine what round it is
        int roundNumber = d1.getTimesRolled() + 1;
        System.out.println("Round " + roundNumber);

        // Roll dice 4x and get minimum
        System.out.println("Rolling the first die 4x...");
        System.out.println("Rolling the second die 4x...");
        val1 = d1.getMinRoll(4);
        val2 = d2.getMinRoll(4);
        int minSum = val1 + val2;
        System.out.println("The sum is " + minSum);

        // Determine if sum has been found or not, then update rolls
        if (rolls[minSum - 2] == false)
        {
            System.out.println("There is an open spot! You filled a sum!");
        }
        else
        {
            System.out.println("Uh oh! Looks like you have already rolled " +
                    "this number. Try again!");
        }
        rolls[minSum - 2] = true;

        // Update available uses of bonus
        isMinBonusUsed = true;
    }

    /**
     * Sets up round with maximum bonus used
     * Rolls dice 4 times, then takes maximum of the rolls
     * Calculates sum and determines if found or not
     */
    public void playMaxRound()
    {
        System.out.println();

        // Determine what round it is
        int roundNumber = d1.getTimesRolled() + 1;
        System.out.println("Round " + roundNumber);

        // Roll dice 4x and get maximum
        System.out.println("Rolling the first die 4x...");
        System.out.println("Rolling the second die 4x...");
        val1 = d1.getMaxRoll(4);
        val2 = d2.getMaxRoll(4);
        int maxSum = val1 + val2;
        System.out.println("The sum is " + maxSum);

        // Determine if sum has been found or not, then update rolls
        if (rolls[maxSum - 2] == false)
        {
            System.out.println("There is an open spot! You filled a sum!");
        }
        else
        {
            System.out.println("Uh oh! Looks like you have already rolled " +
                    "this number. Try again!");
        }
        isMaxBonusUsed = true;

        // Update available uses of bonus
        rolls[maxSum - 2] = true;
    }

    /**
     * Contains main loop of game
     * Continues until the game has been won
     */
    public void playGame()
    {
        // Make scanner object
        Scanner s = new Scanner(System.in);

        // Print instructions
        printInstructions();
        //Check if game won
        while (isGameWon == false)
        {
            if (isGameOver())
            {
                System.out.println();
                System.out.println("GAME OVER! You had a score of "
                        + d1.getTimesRolled());
                break;
            }
            else
            {
                // Repaint at the beginning of each round
                observer.repaint();

                System.out.println();

                // Begin round
                System.out.println("Type 'roll' to start the next round");
                String userResponse = s.nextLine();

                // Regular roll
                if (userResponse.equals("roll"))
                {
                    playRound();
                }

                // Min bonus roll
                if (userResponse.equals("min") && isMinBonusUsed == false)
                {
                    playMinRound();
                }

                // Max bonus roll
                else if (userResponse.equals("max") && isMaxBonusUsed == false)
                {
                    playMaxRound();
                }

                observer.repaint();
            }
        }
    }

    public void draw(Graphics g)
    {
        // Figure out if bonus used or not
        // If bonus used, draw line through
        g.setColor(Color.RED);
        if (isMaxBonusUsed)
        {
            g.fillRect(220, 107, 65, 5);
        }

        if (isMinBonusUsed)
        {
            g.fillRect(310, 107, 65, 5);
        }

        // Draw number of turns
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.ITALIC, 28));
        g.drawString(String.valueOf(d1.getTimesRolled()), 110, 55);

        // Draw the die numbers that were rolled for the round
        if (d1.getTimesRolled() != 0)
        {
            g.drawImage(dieImages[val1 - 1], 100, 275, 100, 100, observer);
            g.drawImage(dieImages[val2 - 1], 250, 275, 100, 100, observer);
        }

        // Draw whether a number has been reached or not
        g.setColor(Color.GREEN);
        int XCorner = 575;
        int YCorner = 110;
        for (int i = 0; i < rolls.length; i++)
        {
            if (rolls[i])
            {
                g.drawString("YES", XCorner, YCorner);
            }
            YCorner += 55;
        }

        // Print the instructions of the game
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 18));
        g.drawString("RULES OF THE GAME", 10, 560);

        g.setFont(new Font("SansSerif", Font.BOLD, 12));
        g.drawString("-Objective is to roll 2 die and get all 11", 10, 585);
        g.drawString("possible sums in as few rolls as possible", 10, 600);
        g.drawString("-If you need a larger or smaller value,", 10, 615);
        g.drawString("use the min or max bonuses, which will help you out", 10, 630);
        g.drawString("-You can do this by either typing", 10, 645);
        g.drawString("'roll', 'max', or 'min' into the console", 10, 660);
        g.drawString("Happy Rolling!", 10, 685);

        // Draw game over screen
        if (isGameOver())
        {
            g.setColor(Color.WHITE);
            g.fillRect(0,0,700,700);
            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.BOLD, 55));
            g.drawString("GAME OVER!", 170, 350);
        }
    }

    public static void main(String[] args)
    {
        Rotation r = new Rotation();
        r.playGame();
    }
}