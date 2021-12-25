package PongProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

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
    Score score;

    GamePanel(){
        /**
         * create paddle and ball objects in our game
         */
        setPaddles();
        setBall();
        score = new Score(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());
        this.setPreferredSize(SCREEN_SIZE);

        //create and start the game thread
        gt = new Thread(this);
        gt.start();
    }

    public void setBall(){

    }

    public void setPaddles(){
        p1 = new Paddle(0, (g))

    }

    public void paint(Graphics g){
        img = createImage(getWidth(), getHeight());
        graphics = img.getGraphics();
        draw(graphics);
        g.drawImage(img, 0, 0, this);
    }

    public void draw(Graphics graphics){

    }

    public void move(){

    }

    public void checkCollations(){

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
                checkCollations();
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

        }

        public void keyReleased(KeyEvent e){

        }
    }

}
