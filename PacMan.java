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
    private boolean running;
    private final int block_size = 24;
    private final int n_blocks = 15;
    private int screen_size = block_size * (n_blocks + 2);

    // Character Variables
    //7,6
    private int[] pacMan = {1,1};
    private final int[][] ghosts;
    private final int[] spghost = {6, 13};
    private Image pacmanImage;
    private final Image[] ghostImages;


    // Game UI Variables
    private int remaining;
    private int score;

    // 2D Array that constructs the map.
    private final int[][] levelData = {
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
                {7, 8},
                {8, 7},
                {8, 8}
        };

        pacmanImage = Toolkit.getDefaultToolkit().getImage("pac-right.png");
        ghostImages = new Image[]{
        Toolkit.getDefaultToolkit().getImage("ghost2.png"),
        Toolkit.getDefaultToolkit().getImage("ghost3.png"),
        Toolkit.getDefaultToolkit().getImage("ghost4.png")
        };
        startGhostTimer();
        running =true;
    }
//    private Image loadImage(String fileName) throws IOException {
//        URL imageUrl = getClass().getResource(fileName);
//        if (imageUrl != null) {
//            return ImageIO.read(imageUrl);
//        } else {
//            throw new IOException("Image file not found: " + fileName);
//        }
//    }
    private void startGhostTimer() {
        Timer ghostTimer = new Timer();
        ghostTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                moveGhosts();
                moveGhostShortestPath();
                SwingUtilities.invokeLater(() -> repaint());
            }
        }, 0, 500); // Adjust the period for ghost movement speed
    }
    public void paint(Graphics window){
        if (running){
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
                        clearScoreArea(window);
                        drawScore(window);
                    }
                }
            }
            // Draw ghosts
            for (int i = 0; i < ghosts.length; i++) {
                if (ghostImages[i] != null) {
                    window.drawImage(ghostImages[i], ghosts[i][0] * block_size + block_size, ghosts[i][1] * block_size + block_size, block_size, block_size, null);
                }
            }
            window.drawImage(Toolkit.getDefaultToolkit().getImage("ghost1.png"),spghost[0]*block_size+block_size,spghost[1] * block_size + block_size, block_size, block_size, null );
            // Checks if the game was won
            if(remaining == 0){
                System.out.println("You got all the points!");
                running  = false;
            }
            // Draws the PacMan
            if (pacmanImage != null) {
                window.drawImage(pacmanImage, pacMan[0] * block_size + block_size, pacMan[1] * block_size + block_size, block_size, block_size, null);
            }

            // Draws score, had to create a clear score area method bcz the paint method is dumb and the numbers were overlapping.
            private void clearScoreArea(Graphics window) {
                window.setColor(Color.BLACK);
                window.fillRect(65, screen_size - 20, 35, 30);
            }
            private void drawScore(Graphics window) {
                window.setColor(Color.GREEN);
                window.setFont(new Font("Arial", Font.BOLD, 14));
                window.drawString("Score: " + score, 20, screen_size - 10);
            }
            for (int[] ghost : ghosts) {
                if (pacMan[0] == ghost[0] && pacMan[1] == ghost[1]) {
                    window.setColor(Color.lightGray);
                    window.setFont(new Font("Ravie", Font.BOLD, 15));
                    window.drawString("YOU DIED!!! YOU SUCK!", 55, 20);
                    running = false;
                }
            }
            if(spghost[0]==pacMan[0] && spghost[1]==pacMan[1]){
                window.setColor(Color.lightGray);
                window.setFont(new Font("Ravie", Font.BOLD, 15));
                window.drawString("YOU DIED!!! YOU SUCK!", 55, 20);
                running = false;
            }
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
                pacmanImage = Toolkit.getDefaultToolkit().getImage("PacMan Up.png");
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                newPacManY++;
                pacmanImage = Toolkit.getDefaultToolkit().getImage("PacMan Down.png");
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                newPacManX--;
                pacmanImage = Toolkit.getDefaultToolkit().getImage("PacMan Left.png");
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                newPacManX++;
                pacmanImage = Toolkit.getDefaultToolkit().getImage("PacMan Right.png");
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

    // function to move the ghosts in a random manner and shortest path.
    public void moveGhostShortestPath(){
        if(spghost[0]<pacMan[0] && isValidMove(spghost[0]+1,spghost[1])){
            spghost[0]++;
        }
        else if(spghost[0]>pacMan[0] && isValidMove(spghost[0]-1,spghost[1])){
            spghost[0]--;
        }
        else if(spghost[1]<pacMan[1] && isValidMove(spghost[0],spghost[1]+1)){
            spghost[1]++;
        }
        else if(spghost[1]>pacMan[1] && isValidMove(spghost[0],spghost[1]-1)){
            spghost[1]--;
        }

    }
    public void moveGhosts() {
        for (int[] ghost : ghosts) {
            // Random movement for the ghosts
            Random random = new Random();
            int direction = random.nextInt(4); // 0: up, 1: down, 2: left, 3: right

            switch (direction) {
                case 0 -> {if (isValidMove(ghost[0], ghost[1] - 1)) {
                        ghost[1]--;
                    }}
                case 1 ->{if (isValidMove(ghost[0], ghost[1] + 1)) {
                        ghost[1]++;
                    }}
                case 2 ->{if (isValidMove(ghost[0] - 1, ghost[1])) {
                        ghost[0]--;
                    }}

                case 3-> {if (isValidMove(ghost[0] + 1, ghost[1])) {
                        ghost[0]++;
                    }}
            }
        }
    }
    // @Override
    // public void keyReleased(KeyEvent e) {

    // }
    // @Override
    // public void keyTyped(KeyEvent e) {

    // }

}
