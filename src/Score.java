import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Score extends Rectangle {
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    static int player1;
    static int player2;
    Score(int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Ariel", Font.PLAIN, 60));
        g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
        g.drawString(String.valueOf(player1), GAME_WIDTH/2 - 80, 50);
        g.drawString(String.valueOf(player2), GAME_WIDTH/2 + 45, 50);
    }
}
