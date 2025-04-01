package ap.exercises.exe2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main_EX2_PM_2_4 {
    public static void main(String[] args) {
        Random rand = new Random();
        int k = 9, c = 15;
        PacmanEngine pacmanEngine = new PacmanEngine(k, c, rand);

        if (!pacmanEngine.load()) {
            System.out.println("No saved game found. Starting new game.");
        }

        while (true) {
            pacmanEngine.printMatrix();
            pacmanEngine.printScore();
            pacmanEngine.printElapsedTime();

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            int direction = rand.nextInt(4);
            pacmanEngine.move(direction);
            pacmanEngine.save();
        }
    }

    static class PacmanEngine {
        private static final String SAVE_FILE = "save_game.txt";
        private char[][] points;
        private int score = 0;
        private final int k, c;
        private int x, y;
        private char[][] arr;
        private final Random rand;
        private long startTime;

        public PacmanEngine(int k, int c, Random rand) {
            this.k = k;
            this.c = c;
            this.rand = rand;
            this.x = 1;
            this.y = 1;
            this.startTime = System.currentTimeMillis();
            this.arr = new char[k + 2][k + 2];
            this.points = new char[k + 2][k + 2];
            initializeGame();
        }

        private void initializeGame() {
            this.points = createPoints(k, c, rand);
            createMatrix();
        }

        private char[][] createPoints(int k, int c, Random rand) {
            char[][] points = new char[k + 2][k + 2];
            for (char[] row : points) {
                Arrays.fill(row, ' ');
            }

            int filled = 0;
            while (filled < c) {
                int m = 1 + rand.nextInt(k);
                int n = 1 + rand.nextInt(k);

                if ((m != 1 || n != 1) && points[m][n] != '.') {
                    points[m][n] = '.';
                    filled++;
                }
            }
            return points;
        }

        private void createMatrix() {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (i == 0 || i == arr.length - 1 || j == 0 || j == arr[i].length - 1) {
                        arr[i][j] = '*';
                    } else {
                        arr[i][j] = points[i][j];
                    }
                }
            }
            arr[x][y] = 'X';
        }

        public boolean load() {
            File file = new File(SAVE_FILE);
            if (!file.exists()) {
                return false;
            }

            try (Scanner scanner = new Scanner(file)) {
                x = scanner.nextInt();
                y = scanner.nextInt();
                score = scanner.nextInt();
                long elapsedTime = scanner.nextLong();
                scanner.nextLine(); // consume newline

                points = new char[k + 2][k + 2];
                for (int i = 0; i < points.length && scanner.hasNextLine(); i++) {
                    String line = scanner.nextLine();
                    if (line.length() != k + 2) {
                        System.out.println("Invalid line length in save file");
                        return false;
                    }
                    points[i] = line.toCharArray();
                }

                createMatrix();
                startTime = System.currentTimeMillis() - elapsedTime;
                System.out.println("Game loaded successfully!");
                return true;
            } catch (Exception e) {
                System.out.println("Error loading game: " + e.getMessage());
                return false;
            }
        }
        public void printMatrix() {
            for (char[] row : arr) {
                System.out.println(new String(row));
            }
            System.out.println();
        }

        public void printScore() {
            System.out.println("Score: " + score);
        }

        public void printElapsedTime() {
            long elapsedMillis = System.currentTimeMillis() - startTime;
            long seconds = (elapsedMillis / 1000) % 60;
            long minutes = (elapsedMillis / (1000 * 60)) % 60;
            long hours = (elapsedMillis / (1000 * 60 * 60)) % 24;
            System.out.printf("Elapsed time: %02d:%02d:%02d%n", hours, minutes, seconds);
        }

        public void move(int direction) {
            int newX = x;
            int newY = y;

            switch (direction) {
                case 0 : newX--; // UP
                case 1 : newY++; // RIGHT
                case 2 : newY--; // LEFT
                case 3 : newX++; // DOWN
            }

            // Check boundaries
            if (newX < 1 || newX > k || newY < 1 || newY > k) {
                System.out.println("Hit the wall!");
                return;
            }

            // Check if we're collecting a point
            if (arr[newX][newY] == '.') {
                score++;
                points[newX][newY] = ' ';
            }

            // Update pacman position
            arr[x][y] = ' ';
            x = newX;
            y = newY;
            arr[x][y] = 'X';
        }

        public void save() {
            try (FileWriter writer = new FileWriter(SAVE_FILE)) {
                writer.write(x + "\n" + y + "\n" + score + "\n" + (System.currentTimeMillis() - startTime) + "\n");
                for (char[] row : points) {
                    writer.write(new String(row) + "\n");
                }
                System.out.println("Game saved successfully!");
            } catch (IOException e) {
                System.out.println("Error saving game: " + e.getMessage());
            }
        }
    }
}

