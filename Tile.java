import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class Tile {
    private int type;
    private int x,y;
    private Pac player;

    public Tile(int t,int r, int c){
        type = t;
        x = r;
        y = c;

    }
    public void paint(Graphics window){
        window.setColor(Color.BLUE);
        //-99 == Pac Man
        if(type == -99){
            if(player == null)
                player = new Pac(x+8,y+8,48,48,1);
            player.paintComponent(window);
        }
        //-1 == door left
        if(type == -1){
            window.setColor(Color.yellow);
            window.fillRect(x,y,5,64);
        }
        //-2 == door right
        if(type == -2){
            window.setColor(Color.yellow);
            window.fillRect(x+59,y,5,64);
        }
        //-3 == door top
        if(type == -3){
            window.setColor(Color.yellow);
            window.fillRect(x,y,64,5);
        }
        //-4 == door bottom
        if(type == -4){
            window.setColor(Color.yellow);
            window.fillRect(x, y + 59, 64, 5);
        }
        //1 == top left corner
        if(type == 1){
            window.fillRect(x,y,5,64);
            window.fillRect(x,y,64,5);
        }
        // 2 == top edge
        if(type == 2){
            window.fillRect(x,y,64,5);
        }
        //3 == top right corner
        if(type == 3){
            window.fillRect(x,y,64,5);
            window.fillRect(x+59,y,5,64);
        }
        //4 == right edge
        if(type == 4){
            window.fillRect(x+59,y,5,64);
        }
        //5 == bottom right corner
        if(type == 5){
            window.fillRect(x+59,y,5,64);
            window.fillRect(x,y+59,64,5);
        }
        //6 == bottom edge
        if(type == 6) {
            window.fillRect(x, y + 59, 64, 5);
        }
        //7 == bottom left corner
        if(type == 7){
            window.fillRect(x,y+59,64,5);
            window.fillRect(x,y,5,64);
        }
        //8 == left edge
        if(type == 8){
            window.fillRect(x,y,5,64);
        }
        //9 ==  cup opening left
        if(type == 9){
            window.fillRect(x,y+59,64,5);
            window.fillRect(x,y,64,5);
            window.fillRect(x+59,y,5,64);
        }
        //10 == cup opening right
        if(type == 10){
            window.fillRect(x,y+59,64,5);
            window.fillRect(x,y,64,5);
            window.fillRect(x,y,5,64);
        }
        //11 == cup opening up
        if(type == 11){
            window.fillRect(x,y,5,64);
            window.fillRect(x+59,y,5,64);
            window.fillRect(x, y + 59, 64, 5);
        }
        //12 == cup opening down
        if(type == 12){
            window.fillRect(x,y,5,64);
            window.fillRect(x+59,y,5,64);
            window.fillRect(x,y,64,5);
        }
        //13 == box
        if(type == 13){
            window.fillRect(x,y,5,64);
            window.fillRect(x+59,y,5,64);
            window.fillRect(x,y,64,5);
            window.fillRect(x, y + 59, 64, 5);
        }
        //14 == tunnel up
        if(type == 14){
            window.fillRect(x,y,5,64);
            window.fillRect(x+59,y,5,64);
        }
        //15 == tunnel right
        if(type == 15){
            window.fillRect(x,y+59,64,5);
            window.fillRect(x,y,64,5);
        }


    }
}
