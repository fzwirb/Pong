import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * object to add that will generate random boxes each game for the ball to bounce off of
 */

public class BoxBarrier extends Rectangle {

    BoxBarrier(int x, int y, int width, int height){
        super(x, y, width, height);
    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillRect(x ,y, width, height);
    }
}
