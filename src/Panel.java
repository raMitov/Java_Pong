import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Panel extends JPanel implements Runnable {
    //tennis game has a 9-5 ratio:D
    static final int GAME_WIDTH = 1000;
    static final int GAME_HIGHT = 700;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    private final Menu menu;
    public enum STATE{
        MENU, GAME
    }
    public static STATE State = STATE.MENU;

    Thread gameThread;
    Image image;
    Graphics graphic;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score scor;


        Panel() {
        newPaddes();
        newBall();

        scor = new Score(GAME_WIDTH, GAME_HIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        menu = new Menu();
        this.addMouseListener(new MouseInput());
        gameThread = new Thread(this);
        gameThread.start();
    }
        public void run () {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            // Only run the game logic if the state is GAME
            if (State == STATE.GAME) {
                if (delta >= 1) {
                    move();
                    checkCollision();
                    repaint();
                    delta--;
                }
            }
            else{
                repaint();
            }
        }
    }
        public void newBall () {
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }
        public void newPaddes () {
        paddle1 = new Paddle(0, (GAME_HIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle((GAME_WIDTH - PADDLE_WIDTH), (GAME_HIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }
        public void paint (Graphics g){
        image = createImage(getWidth(), getHeight());
        graphic = image.getGraphics();
        draw(graphic);
        g.drawImage(image, 0, 0, this);
    }
        public void draw (Graphics g){
        if (State == STATE.GAME) {
            paddle1.draw(g);
            paddle2.draw(g);
            ball.draw(g);
            scor.draw(g);
        } else if (State == STATE.MENU) {
            menu.draw(g);
        }
    }
        public void move () {
        //maybe if state game here??
        if (State == STATE.GAME) {
            paddle1.move();
            paddle2.move();
            ball.move();
        }
    }
        public void checkCollision () {
        if (State == STATE.GAME) {
            if (ball.y <= 0) {
                ball.setYDirection(-ball.yVelocity);
            }
            if (ball.y >= GAME_HIGHT - BALL_DIAMETER) {
                ball.setYDirection(-ball.yVelocity);
            }
            if (ball.intersects(paddle1)) {
                ball.xVelocity = Math.abs(ball.xVelocity);
                ball.xVelocity++;
                if (ball.yVelocity > 0)
                    ball.yVelocity++;
                ball.setXDirection(ball.xVelocity);
                ball.setYDirection(ball.yVelocity);
            }
            if (ball.intersects(paddle2)) {
                ball.xVelocity = Math.abs(ball.xVelocity);
                ball.xVelocity++;
                if (ball.yVelocity > 0)
                    ball.yVelocity++;
                ball.setXDirection(-ball.xVelocity);
                ball.setYDirection(ball.yVelocity);
            }
            if (paddle1.y <= 0)
                paddle1.y = 0;
            if (paddle1.y >= (GAME_HIGHT - PADDLE_HEIGHT)) {
                paddle1.y = GAME_HIGHT - PADDLE_HEIGHT;
            }
            if (paddle2.y <= 0)
                paddle2.y = 0;
            if (paddle2.y >= (GAME_HIGHT - PADDLE_HEIGHT)) {
                paddle2.y = GAME_HIGHT - PADDLE_HEIGHT;
            }
            if (ball.x <= GAME_WIDTH - (GAME_WIDTH + BALL_DIAMETER)) {
                Score.player2++;
                newPaddes();
                newBall();
            }
            if (ball.x >= GAME_WIDTH) {
                Score.player1++;
                newPaddes();
                newBall();
            }
        }
    }


        public class AL extends KeyAdapter {
            public void keyPressed(KeyEvent e) {
                paddle1.keyPressed(e);
                paddle2.keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                paddle1.keyReleased(e);
                paddle2.keyReleased(e);
            }
        }

}
