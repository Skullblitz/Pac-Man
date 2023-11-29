import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pac extends JPanel implements KeyListener
{
    private int x, y, w, h, speed;  //these are instance variables
    boolean u,r,d,l;


    //this is the constructor
    public Pac( int ex, int wy, int wd, int ht, int speed)
    {
        x = ex;
        y = wy;
        w = wd;
        h = ht;
        this.speed = speed;

        addKeyListener( this ); /*all keyListeners must have this in the constructor*/

        setFocusable( true );
    }

    public void paintComponent( Graphics window )
    {


        //this rectangle shows you the boundaries of what you are drawing
//        window.setColor( Color.RED );
//        window.drawRect( x, y, w, h );
        //this allows us to put a png,jpg, or gif
        window.setColor(Color.black);
        window.fillRect(0,0,1000,1000);

        Graphics2D g2 = (Graphics2D) window;
//        Image img1 = Toolkit.getDefaultToolkit().getImage("right.png"); /*the image cannot be in the SRC folder*/
//        g2.drawImage(img1, x , y , w , h , this);
        drawPac(window);

        r = false;
        d = false;
        l = false;
        u = false;
    }
    public void keyTyped(KeyEvent e) {
    }

    /*2*/
    public void keyPressed(KeyEvent e)
    {
        /*KeyEvent key codes: https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html */

        if(e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            //Image img1 = Toolkit.getDefaultToolkit().getImage("right.png"); /*the image cannot be in the SRC folder*/
            right();
            repaint();
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT ) {
            //Image img1 = Toolkit.getDefaultToolkit().getImage("left.png"); /*the image cannot be in the SRC folder*/
            left();
            repaint();
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP ) {
            //Image img1 = Toolkit.getDefaultToolkit().getImage("left.png"); /*the image cannot be in the SRC folder*/
            up();
            repaint();
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN ) {
            //mage img1 = Toolkit.getDefaultToolkit().getImage("left.png"); /*the image cannot be in the SRC folder*/
            down();
            repaint();
        }
//        repaint();
    }

    /*3*/
    public void keyReleased(KeyEvent e) {}

    /*these allow us to see the dimensions */
    public int getX() { return x; }
    public int getY() { return y; }
    public int getW() { return w; }
    public int getH() { return h; }
    public void right(){
        x+=speed; r = true;
    }
    public void up(){
        y-=speed;u = true;
    }
    public void left(){
        x-=speed; l = true;
    }
    public void down(){
        y+=speed; d = true;
    }

    public void drawPac(Graphics window){
        Image img1;
        Graphics2D g2 = (Graphics2D) window;
        if(r){
            img1 = Toolkit.getDefaultToolkit().getImage("right.png"); /*the image cannot be in the SRC folder*/
            g2.drawImage(img1, x , y , w , h , this);
            r = false;
            //repaint();
        }
        else if(l){
            img1 = Toolkit.getDefaultToolkit().getImage("left.png"); /*the image cannot be in the SRC folder*/
            g2.drawImage(img1, x , y , w , h , this);
            l = false;
            //repaint();
        }
        else if(u){
            img1 = Toolkit.getDefaultToolkit().getImage("up.png"); /*the image cannot be in the SRC folder*/
            g2.drawImage(img1, x , y , w , h , this);
            u = false;
            //repaint();
        }
        else if(d){
            img1 = Toolkit.getDefaultToolkit().getImage("down.png"); /*the image cannot be in the SRC folder*/
            g2.drawImage(img1, x , y , w , h , this);
            d = false;
            //repaint();
        }
    }

}