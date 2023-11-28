import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class SpriteSheet {

    private BufferedImage spriteSheet;
    private final int spriteWidth = 15; // Width of each individual sprite
    private final int spriteHeight = 15; // Height of each individual sprite

    public SpriteSheet() {
        try {
            // Load the sprite sheet image
            spriteSheet = ImageIO.read(new File("Arcade - Pac-Man - General Sprites.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void paint(Graphics window, int x, int y, int col, int row){
        window.drawImage(
                spriteSheet.getSubimage(col * spriteWidth, row * spriteHeight, spriteWidth, spriteHeight),
                x, y, null
        );
    }
}
