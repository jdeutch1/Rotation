import javax.swing.*;
import java.awt.*;


public class RotationViewer extends JFrame
{
    /** Instance Variables **/
    private Image backgroundImage;
    private Rotation game;
    public RotationViewer(Rotation game)
    {
        this.game = game;
        backgroundImage = new ImageIcon("Resources/background.png").getImage();

        // Make window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("ROTATION");
        this.setSize(700, 700);
        this.setVisible(true);

    }

    public void paint(Graphics g)
    {
        // Draw background image, then draw game
        g.drawImage(backgroundImage, 0, 0, 700, 700, this);
        game.draw(g);
    }
}
