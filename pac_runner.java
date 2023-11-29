import java.awt.*;
import javax.swing.*;

public class pac_runner extends JFrame
{
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;

    public pac_runner()
    {
        super("Pac");

        setSize(WIDTH,HEIGHT);


        Pac p = new Pac(100,100, 50,50, 20);

        add( p );



        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main( String args[] )
    {
        pac_runner run = new pac_runner();
    }
}