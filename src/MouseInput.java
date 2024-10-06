import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        // Check if Play button is clicked
        if (mx >= (Panel.GAME_WIDTH / 2) + 120 && mx <= (Panel.GAME_WIDTH / 2) + 220) {
            if (my >= 150 && my <= 200) {
                Panel.State = Panel.STATE.GAME;  // Switch to GAME state when Play is clicked
            }
        }

        // Check if Quit button is clicked
        if (mx >= (Panel.GAME_WIDTH / 2) + 120 && mx <= (Panel.GAME_WIDTH / 2) + 220) {
            if (my >= 350 && my <= 400) {
                System.exit(1);  // Exit game when Quit is clicked
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
