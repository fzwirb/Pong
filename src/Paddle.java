
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Paddle extends Rectangle {

    int id;
    int ySpeed = 15;
    int velocity;

    Paddle(int xpos, int ypos, int paddle_width, int paddle_height, int id){
        super(xpos, ypos, paddle_width, paddle_height);
        this.id = id;
    }

    public void keyPressed(KeyEvent e){
        switch (id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                setYDirection(-ySpeed);
                move();
            }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(ySpeed);
                    move();
            }
            break;

            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYDirection(-ySpeed);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(ySpeed);
                    move();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e){
        switch (id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(0);
                    move();
                }
                break;

            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(0);
                    move();
                }
                break;
        }
    }

    public void setYDirection(int yDir){
        velocity = yDir;

    }

    public void move(){
        y = y + velocity;
    }

    public void draw(Graphics graphics){
        //player one
        if(id==1){
            graphics.setColor(Color.orange);
        }
        //player 2
        else{
            graphics.setColor(Color.blue);
        }
        graphics.fillRect(x,y,width,height);
    }
}
