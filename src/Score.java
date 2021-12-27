import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle{

    static int game_width;
    static int game_height;
    int p1;
    int p2;

    Score(int width, int height){
        Score.game_width = width;
        Score.game_height = height;
    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("", Font.PLAIN, 60));
        g.drawLine(game_width / 2, 0, game_width / 2, game_height);
        g.drawString(String.valueOf(p1),  (game_width / 2) - 55, 50);
        g.drawString(String.valueOf(p2),  (game_width / 2) + 20, 50);
    }
}
