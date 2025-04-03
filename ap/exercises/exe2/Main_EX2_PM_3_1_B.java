package ap.exercises.exe2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Main_EX2_PM_3_1_B extends JFrame implements KeyListener {

    Point pacmanPoint = new Point();
    final int width = 300, height = 300, boxSize = 5;
    static int direction = 1;
    final int LEFT = 1, RIGHT = 2, TOP = 3, BOTTOM = 4;
    int score = 0;
    Point dotPoint = new Point();

    public Main_EX2_PM_3_1_B() {
        addKeyListener(this);
        pacmanPoint.setLocation((width / boxSize) / 2, (height / boxSize) / 2);
        getNewDotPointLocation();
        setSize(width, height);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g.clearRect(0, 0, width, height);
        logic();
        drawPacman(g2D);
        drawDotPoint(g2D);
        drawScore(g2D, score);
        setVisible(true);
    }

    private void drawPacman(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect(pacmanPoint.x * boxSize, pacmanPoint.y * boxSize, boxSize, boxSize);
    }

    private void drawDotPoint(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillRect(dotPoint.x * boxSize, dotPoint.y * boxSize, boxSize, boxSize);
    }

    private void drawScore(Graphics2D g2d, int score) {
        System.out.println("Score: " + score);
        g2d.setColor(Color.MAGENTA);
        g2d.drawString("Score: ", 25, 50);
        g2d.setColor(Color.BLACK);
        g2d.drawString(String.valueOf(score),70, 50);
    }

    private void logic() {
        if (dotPoint.x == pacmanPoint.x && dotPoint.y == pacmanPoint.y) {
            getNewDotPointLocation();
            score++;
        }
        movePacman();
    }

    private void movePacman() {
        int xMovement = 1;
        int yMovement = 0;
        switch (direction) {
            case LEFT:
                xMovement = -1;
                yMovement = 0;
                break;
            case RIGHT:
                xMovement = 1;
                yMovement = 0;
                break;
            case TOP:
                xMovement = 0;
                yMovement = -1;
                break;
            case BOTTOM:
                xMovement = 0;
                yMovement = 1;
                break;
            default:
                xMovement = yMovement = 0;
                break;
        }
        pacmanPoint.setLocation(pacmanPoint.x + xMovement, pacmanPoint.y + yMovement);
        handleCrossBorder();
    }

    private void getNewDotPointLocation() {
        Random rand = new Random();
        int delta = boxSize * 2;
        dotPoint.setLocation(rand.nextInt(width / boxSize - 2 * delta) + delta, rand.nextInt(height / boxSize - 2 * delta) + delta);
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP)
            direction = 3;
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            direction = 4;
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            direction = 1;
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            direction = 2;
        else if (e.getKeyCode() == KeyEvent.VK_P)
            direction = 0;
        else
            direction = -1;

        System.out.println("direction:" + direction + "    <- e.getKeyCode()=" + e.getKeyCode());

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void handleCrossBorder() {
        //loop for x
        if(pacmanPoint.x < 0){
            pacmanPoint.x = (width / boxSize) - 1;
        }
        else if (pacmanPoint.x >= width / boxSize ){
            pacmanPoint.x = 0 ;
        }

        //loop for y
        if(pacmanPoint.y < 0){
            pacmanPoint.y = (height / boxSize) - 1;
        }
        else if (pacmanPoint.y >= height / boxSize ){
            pacmanPoint.y = 0 ;
        }
    }


    public static void main(String[] args) {
        Main_EX2_PM_3_1_B frame = new Main_EX2_PM_3_1_B();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
