import javax.swing.*;
import java.awt.*;

public class PacManRunner extends JFrame {

    private static final int WIDTH = 408;
    private static final int HEIGHT = 408;

    public PacManRunner(){
        super("Pac-Man");

        setSize(WIDTH,HEIGHT);
        setBackground(Color.BLACK);

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
