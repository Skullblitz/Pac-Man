import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PacMan extends JPanel implements KeyListener{

    private int WIDTH , HEIGHT;
    private boolean inGame = false;
    private boolean dying = false;

    private final int block_size = 24;
    private final int n_blocks = 15;
    private int screen_size = block_size * (n_blocks + 2);

    private int[] pacMan = {7,6};

    private int lives = 3;
    private int remaining;
    private int score;
    private int[] ghost_x, ghost_y, ghostType;
    private int[][] RedGhost = new int[n_blocks][n_blocks];
    private int[][] shadow = new int[n_blocks][n_blocks];

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
        remaining = 0;
        for (int x = 0; x < levelData.length; x++) {
            for (int y = 0; y < levelData[x].length; y++) {
                if(levelData[y][x] == 1) {
                    window.setColor(Color.BLUE);
                    window.fillRect( x * block_size + block_size,y * block_size + block_size ,block_size ,block_size );
                }else if(levelData[y][x] == 0){
                    remaining++;
                    window.setColor(Color.BLACK);
                    window.fillRect(x * block_size + block_size,y * block_size + block_size, block_size ,block_size );
                    window.setColor(Color.YELLOW); // Change color for points
                    window.fillOval(x * block_size + block_size / 2 - 2 + block_size, y * block_size + block_size / 2 - 2 + block_size, 5, 5);
                }else {
                    window.setColor(Color.BLACK);
                    window.fillRect(x * block_size + block_size,y * block_size + block_size, block_size, block_size);
                }
                if(levelData[pacMan[1]][pacMan[0]] == 0){
                    levelData[pacMan[1]][pacMan[0]] = -1;
                    score++;
                    remaining--;
                }
            }
        }
        if(remaining == 0)
            System.out.println("YOU GOT ALL THE POINTS");
        window.setColor(Color.YELLOW);
        window.fillArc(pacMan[0] * block_size + block_size,pacMan[1]*block_size + block_size, block_size, block_size,20,330);
    }


    public void RedGhost(int index, int x, int y){
        if(x > - 1 && x < n_blocks && y > - 1 && y < n_blocks){
            if(pacMan[0] == x && pacMan[1] == y){

            }
        }
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
            case KeyEvent.VK_W:
                newPacManY--;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                newPacManY++;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                newPacManX--;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                newPacManX++;
                break;
            case KeyEvent.VK_SPACE:
                System.out.println(score);
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
