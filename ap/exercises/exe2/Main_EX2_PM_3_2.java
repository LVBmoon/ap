package ap.exercises.exe2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Main_EX2_PM_3_2 {
    private static int k, c;

    public static void main(String[] args) {
        getMeasure();
        SwingUtilities.invokeLater(() -> {
            pacmanGUI frame = new pacmanGUI(c, k);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public static void getMeasure() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a number between 1 and 10: ");
            k = sc.nextInt();
            if (k > 0 && k <= 10)
                break;
            else
                System.out.println("Invalid input. Try again.");
        }
        while (true) {
            System.out.print("Enter the number of points: ");
            c = sc.nextInt();
            if (c <= (k * k) && c > 0)
                break;
            else
                System.out.println("Invalid input. Try again.");
        }
        sc.close();
    }
}

class pacmanGUI extends JFrame implements KeyListener {
    Point pacmanPoint = new Point();
    int c, k;
    int width, height, boxSize = 5;
    static int direction = 1;
    final int LEFT = 1, RIGHT = 2, TOP = 3, BOTTOM = 4;
    int score = 0;
    List<Point> dotPoints = new ArrayList<>();
    long startTime = System.currentTimeMillis();

    public pacmanGUI(int c, int k) {
        this.c = c;
        this.k = k;
        width = height = 100 * k;
        addKeyListener(this);
        pacmanPoint.setLocation((width / boxSize) / 2, (height / boxSize) / 2);
        getNewDotPoints();
        setSize(width, height);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        elapsedTime(g2d);
        g.clearRect(0, 0, width, height);
        logic();
        drawPacman(g2d);
        drawDotPoints(g2d);
        drawScore(g2d, score);
    }

    private void drawPacman(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect(pacmanPoint.x * boxSize, pacmanPoint.y * boxSize, boxSize, boxSize);
    }

    private void drawDotPoints(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        for (Point p : dotPoints) {
            g2d.fillRect(p.x * boxSize, p.y * boxSize, boxSize, boxSize);
        }
    }

    private void drawScore(Graphics2D g2d, int score) {
        if (score == c) {
            g2d.setColor(Color.GREEN);
            g2d.drawString("YOU WON!", 120, 150);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) { }
            System.exit(0);
        }
        System.out.println("Score: " + score);
        g2d.setColor(Color.MAGENTA);
        g2d.drawString("Score: ", 25, 50);
        g2d.setColor(Color.BLACK);
        g2d.drawString(String.valueOf(score), 70, 50);
    }

    private void logic() {
        dotPoints.removeIf(p -> p.equals(pacmanPoint));
        score = c - dotPoints.size();
        movePacman();
    }

    private void movePacman() {
        int xMovement = 0, yMovement = 0;
        switch (direction) {
            case LEFT:
                xMovement = -1;
                break;
            case RIGHT:
                xMovement = 1;
                break;
            case TOP:
                yMovement = -1;
                break;
            case BOTTOM:
                yMovement = 1;
                break;
            default:
                break;
        }
        pacmanPoint.setLocation(pacmanPoint.x + xMovement, pacmanPoint.y + yMovement);
        handleCrossBorder();
    }

    private void getNewDotPoints() {
        Random rand = new Random();
        dotPoints.clear();
        while (dotPoints.size() < c) {
            int x = rand.nextInt(width / boxSize);
            int y = rand.nextInt(height / boxSize);
            Point newPoint = new Point(x, y);
            if (!newPoint.equals(pacmanPoint) && !dotPoints.contains(newPoint)) {
                dotPoints.add(newPoint);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'q') {
            System.out.println("Exiting the game");
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP)
            direction = TOP;
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            direction = BOTTOM;
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            direction = LEFT;
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            direction = RIGHT;
        else
            direction = 0;

        System.out.println("direction: " + direction + "    <- e.getKeyCode()=" + e.getKeyCode());
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    private void handleCrossBorder() {
        if (pacmanPoint.x < 0)
            pacmanPoint.x = (width / boxSize) - 1;
        else if (pacmanPoint.x >= width / boxSize)
            pacmanPoint.x = 0;

        if (pacmanPoint.y < 0)
            pacmanPoint.y = (height / boxSize) - 1;
        else if (pacmanPoint.y >= height / boxSize)
            pacmanPoint.y = 0;
    }

    private void elapsedTime(Graphics2D g2d) {
        long elapsedMillis = System.currentTimeMillis() - startTime;
        long seconds = (elapsedMillis / 1000) % 60;
        long minutes = (elapsedMillis / (1000 * 60)) % 60;
        if (minutes == 2) {
            System.out.println("OUT OF TIME \nCLOSING THE GAME...");
            g2d.setColor(Color.RED);
            g2d.drawString("OUT OF TIME...", 120, 150);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            System.exit(0);
        } else {
            g2d.setColor(Color.GRAY);
            g2d.drawString(String.format("Time: %02d:%02d", minutes, seconds), 300, 210);
            System.out.printf("Elapsed time: %02d:%02d%n", minutes, seconds);
        }
    }
}