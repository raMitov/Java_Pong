import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Menu{
    public Rectangle playButton = new Rectangle(Panel.GAME_WIDTH/2 + 120, 150, 100,50);
    public Rectangle helpButton = new Rectangle(Panel.GAME_WIDTH/2 + 120, 250, 100,50);
    public Rectangle quitButton = new Rectangle(Panel.GAME_WIDTH/2 + 120, 350, 100,50);
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("P-O-N-G", Panel.GAME_WIDTH/ 2, 100);
        Font fnt = new Font("arial", Font.PLAIN, 30);
        g.setFont(fnt);

        g.setColor(Color.blue);
        g.drawString("Play", playButton.x + 30, playButton.y + 33);
        g.setColor(Color.red);
        g.drawString("Help", helpButton.x + 30 , helpButton.y + 33);
        g.setColor(Color.white);
        g.drawString("Quit :(", quitButton.x + 15 , quitButton.y + 33);
        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);

    }

}
