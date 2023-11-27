import javax.swing.*;

public class PacManRunner extends JFrame{

    private static final int WIDTH = 720;
    private static final int HEIGHT = 720;
    public PacManRunner(){
        super("Pac-Man");

        setSize(WIDTH,HEIGHT);

        //This line loads the BreakOut game
        getContentPane().add( new PacMan() );

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        PacManRunner  run = new PacManRunner();
    }
}
