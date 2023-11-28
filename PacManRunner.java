import javax.swing.*;

public class PacManRunner extends JFrame{

    private static int WIDTH = 64;
    private static int HEIGHT = 64;
    public PacManRunner(int level){
        super("Pac-Man");
        int  r = 8;
        int  c = 8;


        for(int i = 1; i < level; i++){
           r+=2;
           c+=2;
        }

        setSize(WIDTH * r,HEIGHT * c);
        setUndecorated(true);
        setLocationRelativeTo(null);

        //This line loads the BreakOut game
        getContentPane().add( new PacMan(level) );

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        PacManRunner  run = new PacManRunner(1);
    }
}