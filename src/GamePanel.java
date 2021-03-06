
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    static final int WINDOW_WIDTH = 1000;
    static final int WINDOW_HEIGHT = 800;
    static final Dimension SCREEN_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 20;
    static final int PADDLE_HEIGHT = 100;
    Thread gt;
    Image img;
    Graphics graphics;
    Random random;
    Paddle p1;
    Paddle p2;
    Ball ball;
    BoxBarrier box;
    BoxBarrier box1;
    Score score;

    GamePanel(){
        /**
         * create paddle and ball objects in our game
         */
        setPaddles();
        setBall();
        setBoxes();
        score = new Score(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());
        this.setPreferredSize(SCREEN_SIZE);

        //create and start the game thread
        gt = new Thread(this);
        gt.start();
    }
    public void setBoxes(){
        box = new BoxBarrier(250, 200, 100, 100);
//        box = new BoxBarrier(850, 100, 100, 100);
    }

    public void setBall(){
//        random = new Random();
        ball = new Ball((WINDOW_WIDTH/2) - (BALL_DIAMETER/2), (WINDOW_HEIGHT/2) - (BALL_DIAMETER/2), BALL_DIAMETER, BALL_DIAMETER);
    }

    public void setPaddles(){
        p1 = new Paddle(0, (WINDOW_HEIGHT /2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        p2 = new Paddle(WINDOW_WIDTH - PADDLE_WIDTH, (WINDOW_HEIGHT /2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);


    }

    public void paint(Graphics g){
        img = createImage(getWidth(), getHeight());
        graphics = img.getGraphics();
        draw(graphics);
        g.drawImage(img, 0, 0, this);
    }

    public void draw(Graphics g){
        p1.draw(g);
        p2.draw(g);
        ball.draw(g);
        score.draw(g);
        box.draw(g);
    }

    public void move(){
        p1.move();
        p2.move();;
        ball.move();
    }

    public void checkCollisions(){
        //paddle collision
        if(p1.y <= 0 ){
            p1.y = 0;
        }
        if(p1.y >= (WINDOW_HEIGHT - PADDLE_HEIGHT)){
            p1.y = WINDOW_HEIGHT - PADDLE_HEIGHT;
        }
        if(p2.y <= 0 ){
            p2.y = 0;
        }
        if(p2.y >= (WINDOW_HEIGHT - PADDLE_HEIGHT)){
            p2.y = WINDOW_HEIGHT - PADDLE_HEIGHT;
        }

        //ball bounce
        if (ball.y <= 0) {
            ball.setY(-ball.yVelocity);
        }
        if (ball.y >= WINDOW_HEIGHT - BALL_DIAMETER){
            ball.setY(-ball.yVelocity);
        }

        //ball bounce off paddles

        //p1
        if(ball.intersects(p1)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.setX(ball.xVelocity);
        }
        //p2
        if(ball.intersects(p2)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.setX(-ball.xVelocity);
        }

        //scoring
        if(ball.x <= 0) {
            score.p2++;
            setPaddles();
            setBall();
            System.out.println("Player 2 scored! score: " + score.p2);
        }
        if(ball.x >= WINDOW_WIDTH-BALL_DIAMETER) {
            score.p1++;
            setPaddles();
            setBall();
            System.out.println("Player 1 scored! score: " + score.p1);
        }

        //box collision
        //x1, y1, x2, y2

        //bottom
        if(ball.intersectsLine(box.getMinX(), box.getMaxY() - (BALL_DIAMETER/2), box.getMaxX(), box.getMaxY() + (BALL_DIAMETER/2))){
            ball.setY(-ball.yVelocity);
            System.out.println("HIT BOTTOM");
        }
        //top
        if(ball.intersectsLine(box.getMinX(), box.getMinY() + (BALL_DIAMETER/2), box.getMaxX(), box.getMinY() + (BALL_DIAMETER/2))){
            ball.setY(-ball.yVelocity);
            System.out.println("HIT TOP");

        }
        //left
        if(ball.intersectsLine(box.getMinX(), box.getMinY(), box.getMinX(), box.getMaxY())){
            ball.setX(-ball.xVelocity);
            System.out.println("HIT LEFT");

        }
        //right
        if(ball.intersectsLine(box.getMaxX(), box.getMinY(), box.getMaxX(), box.getMaxY())){
            ball.setX(-ball.xVelocity);
            System.out.println("HIT right");

        }


    }

    public void run(){
        /**
         * game loop
         */
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 /amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                move();
                checkCollisions();
                repaint();
                delta--;

//                System.out.println("TEST");
            }
        }

    }

    /**
     * Event listeners for key presses
     * while the key is pressed we want to move the paddle
     * On key release, we want to stop the movement
     *
     * The paddle can only move up or down
     */

    public class ActionListener extends KeyAdapter{

        public void keyPressed(KeyEvent e){
            p1.keyPressed(e);
            p2.keyPressed(e);

        }

        public void keyReleased(KeyEvent e){
            p1.keyReleased(e);
            p2.keyReleased(e);
        }
    }

}
