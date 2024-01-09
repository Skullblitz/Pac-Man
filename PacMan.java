import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PacMan extends JPanel implements KeyListener{
    // Screen / Map Variables
    private final int block_size = 24;
    private final int n_blocks = 15;
    private int screen_size = block_size * (n_blocks + 2);

    // Character Variables
    private int[] pacMan = {7,6};
    private int[][] ghosts;
    private Image pacmanImage;
    private Image[] ghostImages;


    // Game UI Variables
    private int remaining;
    private int score;

    // 2D Array that constructs the map.
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

    // Constructor
    public PacMan() {
        setSize(screen_size, screen_size);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        // Initialize ghosts
        ghosts = new int[][]{
                {7, 7},
                {7, 8},
                {8, 7},
                {8, 8}
        };
        try {
            pacmanImage = loadImage("pacright.png");
            ghostImages = new Image[]{
                    loadImage("ghost1.png"),
                    loadImage("ghost2.png"),
                    loadImage("ghost3.png"),
                    loadImage("ghost4.png")
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
        startGhostTimer();
    }
    private Image loadImage(String fileName) throws IOException {
        URL imageUrl = getClass().getResource(fileName);
        if (imageUrl != null) {
            return ImageIO.read(imageUrl);
        } else {
            throw new IOException("Image file not found: " + fileName);
        }
    }
    private void startGhostTimer() {
        Timer ghostTimer = new Timer();
        ghostTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                moveGhosts();
                SwingUtilities.invokeLater(() -> repaint());
            }
        }, 0, 500); // Adjust the period for ghost movement speed
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
        // Draw ghosts
        for (int i = 0; i < ghosts.length; i++) {
            if (ghostImages[i] != null) {
                window.drawImage(ghostImages[i], ghosts[i][0] * block_size + block_size, ghosts[i][1] * block_size + block_size, block_size, block_size, null);
            }
        }
        // Checks if the game was won
        if(remaining == 0)
            System.out.println("You got all the points!");
        // Draws the PacMan
        if (pacmanImage != null) {
            window.drawImage(pacmanImage, pacMan[0] * block_size + block_size, pacMan[1] * block_size + block_size, block_size, block_size, null);
        }
    }

    // Deals with the movement of the PacMan and key strokes.
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int newPacManX = pacMan[0];
        int newPacManY = pacMan[1];

        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                newPacManY--;
                try {
                    pacmanImage = loadImage("PacMan Up.png");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                newPacManY++;
                try {
                    pacmanImage = loadImage("PacMan Down.png");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                newPacManX--;
                try {
                    pacmanImage = loadImage("PacMan Left.png");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                newPacManX++;
                try {
                    pacmanImage = loadImage("PacMan Right.png");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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

    // Checks to see if PacMan is not out of bounds.
    private boolean isValidMove(int newPacManX, int newPacManY) {
        return (newPacManX > -1 && newPacManX < levelData.length &&  newPacManY > -1 && newPacManY < levelData[newPacManX].length && levelData[newPacManY][newPacManX] != 1);
    }

    // function to move the ghosts in a random manner.
    public void moveGhosts() {
        for (int[] ghost : ghosts) {
            // Random movement for the ghosts
            Random random = new Random();
            int direction = random.nextInt(4); // 0: up, 1: down, 2: left, 3: right

            switch (direction) {
                case 0:
                    if (isValidMove(ghost[0], ghost[1] - 1)) {
                        ghost[1]--;
                    }
                    break;
                case 1:
                    if (isValidMove(ghost[0], ghost[1] + 1)) {
                        ghost[1]++;
                    }
                    break;
                case 2:
                    if (isValidMove(ghost[0] - 1, ghost[1])) {
                        ghost[0]--;
                    }
                    break;
                case 3:
                    if (isValidMove(ghost[0] + 1, ghost[1])) {
                        ghost[0]++;
                    }
                    break;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

}
