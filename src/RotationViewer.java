import javax.swing.*;
import java.awt.*;


public class RotationViewer extends JFrame {

    private Image backgroundImage;
    private Rotation game;
    public RotationViewer(Rotation game)
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // the graphics window.
        this.setTitle("ROTATION");
        this.setSize(700, 700);
        this.setVisible(true);
        this.game = game;

        backgroundImage = new ImageIcon("Resources/background.png").getImage();
    }

    public void paint(Graphics g)
    {
        g.drawImage(backgroundImage, 0, 0, 700, 700, this);
        game.draw(g);
    }
}
