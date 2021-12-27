
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle{

    Random random;
    int xVelocity;
    int yVelocity;
    int speed = 5;

    Ball(int xPos, int yPos, int ballWidth, int ballHeight) {
        super(xPos, yPos, ballWidth, ballHeight);
        random = new Random();
        int randX = random.nextInt(2);
        if(randX == 0) {
            randX--;
        }
            randX = randX * speed;
            setX(randX);
        int randY = random.nextInt(2);
        if(randY == 0) {
            randY--;
        }
            randY = randY * speed;
            setY(randY);
    }

    public void setX(int randDir){
        xVelocity = randDir;
    }

    public void setY(int randDir){
        yVelocity = randDir;
    }

    public void move(){
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics graphics){
        graphics.setColor(Color.white);
        graphics.fillOval(x, y, height, width);
    }
}
