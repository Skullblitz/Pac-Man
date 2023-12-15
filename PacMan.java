import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PacMan extends JPanel implements KeyListener{

    private int WIDTH , HEIGHT;
    private boolean inGame = false;
    private boolean dying = false;

    private int block_size = 24;
    private int n_blocks = 15;
    private int screen_size = n_blocks*block_size;
    private int pacman_speed = 6;
    private int[] pacMan = {1,1};

    private int lives = 3;
    private int score;
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed, ghostType;

    private int[][] levelData = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1},
        {1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1},
        {1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1},
        {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
        {1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public PacMan() {

        setSize(screen_size, screen_size);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paint(Graphics window){

        for (int i = 0; i < levelData.length; i++) {
            for (int j = 0; j < levelData[i].length; j++) {
                if(levelData[j][i] == 1) {
                    window.setColor(Color.BLUE);
                    window.fillRect(i*block_size,j*block_size,block_size,block_size);
                }else{
                    window.setColor(Color.BLACK);
                    window.fillRect(i*block_size,j*block_size,block_size,block_size);
                }
            }
        }
        window.setColor(Color.YELLOW);
        window.fillArc(pacMan[0]*block_size,pacMan[1]*block_size,block_size,block_size,20,330);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int newPacManX = pacMan[0];
        int newPacManY = pacMan[1];

        switch (keyCode) {
            case KeyEvent.VK_UP:
                newPacManY--;
                break;
            case KeyEvent.VK_DOWN:
                newPacManY++;
                break;
            case KeyEvent.VK_LEFT:
                newPacManX--;
                break;
            case KeyEvent.VK_RIGHT:
                newPacManX++;
                break;
        }

        if (isValidMove(newPacManX, newPacManY)) {
            pacMan[0] = newPacManX;
            pacMan[1] = newPacManY;
            repaint();
        }
    }

    private boolean isValidMove(int newPacManX, int newPacManY) {
        return (newPacManX > -1 && newPacManX < levelData.length &&  newPacManY > -1 && newPacManY < levelData[newPacManX].length && levelData[newPacManY][newPacManX] != 1);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
