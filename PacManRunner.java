import javax.swing.*;

public class PacManRunner extends JFrame {

    private static final int WIDTH = 360;
    private static final int HEIGHT = 360;

    public PacManRunner(){
        super("Pac-Man");

        setSize(WIDTH,HEIGHT);


        setUndecorated(true);
        setLocationRelativeTo(null);
        add(new PacMan());
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        PacManRunner run = new PacManRunner();
    }


}
