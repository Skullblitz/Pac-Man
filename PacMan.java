import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.ArrayList;


public class PacMan extends JPanel implements Runnable, KeyListener {

    private int[][] map;
    private int width, height;

    private ArrayList<Tile> tiles;
    public PacMan(int level){
        setBackground(Color.BLACK);
        tiles = new ArrayList<>();
        /*
        Key
        1 == top left corner
        2 == top edge
        3 == top right corner
        4 == right edge
        5 == bottom right corner
        6 == bottom edge
        7 == bottom left corner
        8 == left edge
        9 ==  cup opening left
        10 == cup opening right
        11 == cup opening up
        12 == cup opening down
        13 == box
        14 == tunnel up
        15 == tunnel right
        0 == open
        -1 == door left
        -2 == door right
        -3 == door top
        -4 == door bottom
         */
        //Level 1
        if(level == 1){
            width = 8*64;
            height = 8*64;
            map = new int[][]{
                {1,2,-3,2,2,2,2,3},
                {8,12,0,10,15,15,9,4},
                {8,14,0,0,0,0,0,4},
                {8,7,9,0,10,3,0,4},
                {-1,0,0,0,0,7,9,-2},
                {8,12,0,12,0,0,0,4},
                {8,11,0,7,9,0,13,4},
                {7,6,-4,6,6,6,6,5}
            };
        }

        //Making level
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                tiles.add(new Tile(map[col][row],row*64, col*64));
            }
        }
        addKeyListener( this );   	//
        setFocusable( true );		// Do NOT DELETE these three lines
        new Thread(this).start();	//
    }

    public void paint(Graphics window){
        window.setColor(Color.BLACK);
        window.fillRect( 0,0, width, height);
        for(Tile x : tiles){
            x.paint(window);
        }
    }
    public void run()
    {
        try
        {
            while( true )
            {
                Thread.sleep( 10 );
                repaint();
            }
        }
        catch( Exception e )
        {
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}