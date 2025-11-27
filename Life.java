// Nadia Iskandar
// APCS F
// HW 21

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Life {

    private boolean[][] currentGen;
    private boolean[][] nextGen;
    private final int generations;
    private final int rows;
    private final int cols;
    private int genNum;


    public Life(int generations, int row, int col, String filename) {
        this.currentGen = new boolean[row][col];
        this.nextGen = new boolean[row][col];
        this.generations = generations;
        this.rows = row;
        this.cols = col;
        read(filename);
    }


    public int getGenerations() {
        return this.generations;
    }

    public int getRow() {
        return this.rows;
    }

    public int getCol() {
        return this.cols;
    }

    public int generations() {
        return this.genNum;
    }

    public boolean isInBounds(int row, int col) {
        return (row >= 0 && row <= rows) && (col >= 0 && col <= cols);
    } 

    public boolean isLive(int row, int col) {
        return  isInBounds(row, col) && currentGen[row][col];
    }

    public void live(int row, int col) {
        currentGen[row][col] = true;
    }

    public void dead(int row, int col) {
        currentGen[row][col] = false;
    }

    public void toggle(int row, int col) {
        if (isLive(row, col)) {
            dead(row, col);
        } else {
            live(row, col);
        }
    }
    public int getNeighbors(int row, int col) {
        //hw 19 getNeighbors
        int aliveNeighbors = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if ((i + row >= 0 && i + row < rows) && (j + col >= 0 && j + col < cols)) {
                    if (currentGen[i + row][j + col] && (i != 0 || j != 0)) {
                        aliveNeighbors++;
                    }
                }
            }
        }

        return aliveNeighbors;
    }

    public void next() {
        // hw 19 next method
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isLive(i, j) && (getNeighbors(i, j) == 2 || getNeighbors(i, j) == 3)) {
                    //Any live cell with two or three live neighbors survives in the next generation.
                    nextGen[i][j] = true;
                } else if ((!isLive(i, j)) && getNeighbors(i, j) == 3) {
                    //Any dead cell with three live cells becomes a live cell.
                    nextGen[i][j] = true;
                } else {
                    nextGen[i][j] = false;
                    //All other live cells die in the next generation. Similarly, all other dead cells remain dead.
                }
            }

        }

        //swap
        boolean[][] temp = currentGen;
        currentGen = nextGen;
        nextGen = temp;

        genNum++;

    }



    public int getNeighbors(int row, int col, int gridType) {
        //hw 20 getNeighbors
        int aliveNeighbors = 0;
        if (gridType == 1) {
            // rod
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {

                    if(j + col >= 0 && j + col < cols){
                        if (currentGen[(i + row+rows )%rows][(j + col+cols)%cols] && (i != 0 || j != 0)) {
                            aliveNeighbors++;
                        }

                    }

/*
                    if (i + row < 0 && (j + col >= 0 && j + col < cols)) {
                        if (currentGen[rows - 1][j + col]) {

                            aliveNeighbors++;
                        }
                    } else if (i + row >= rows && (j + col >= 0 && j + col < cols)) {
                        if (currentGen[0][j + col]) {
                            aliveNeighbors++;
                        }
                    }

                    if ((i + row >= 0 && i + row < rows) && (j + col >= 0 && j + col < cols)) {
                        if (currentGen[i + row][j + col] && (i != 0 || j != 0)) {
                            aliveNeighbors++;
                        }
                    } */
                }
            }

            return aliveNeighbors;

        } else if (gridType == 2) {

            // cylinder
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                     if(row + i >= 0 && row + i < rows){
                        if (currentGen[(i + row+rows )%rows][(j + col+cols)%cols] && (i != 0 || j != 0)) {
                            aliveNeighbors++;
                        }
                    }

/*
                    if (j + col < 0 && (i + row >= 0 && i + row < rows)) {

                        if (currentGen[i + row][cols - 1]) {

                            aliveNeighbors++;

                        }
                    } else if (j + col >= cols && (i + row >= 0 && i + row < rows)) {
                        if (currentGen[i + row][0]) {
                            aliveNeighbors++;

                        }
                    }

                    if ((i + row >= 0 && i + row < rows) && (j + col >= 0 && j + col < cols)) {
                        if (currentGen[i + row][j + col] && (i != 0 || j != 0)) {
                            aliveNeighbors++;
                        }
                    }*/
                }
            }
            return aliveNeighbors;
        } else if (gridType == 3) {
            // torus
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (currentGen[(i + row+rows )%rows][(j + col+cols)%cols] && (i != 0 || j != 0)) {
                        aliveNeighbors++;
                    }

                  /*  if (i + row < 0 && (j + col >= 0 && j + col < cols)) {
                        if (currentGen[rows - 1][j + col]) {
                            aliveNeighbors++;
                        }
                    } else if (i + row >= rows && (j + col >= 0 && j + col < cols)) {
                        if (currentGen[0][j + col]) {
                            aliveNeighbors++;
                        }
                    } else if (j + col < 0 && (i + row >= 0 && i + row < rows)) {
                        if (currentGen[i + row][cols - 1]) {
                            aliveNeighbors++;
                        }
                    } else if (j + col >= cols && (i + row >= 0 && i + row < rows)) {
                        if (currentGen[i + row][0]) {
                            aliveNeighbors++;
                        }
                    }

                    if ((i + row >= 0 && i + row < rows) && (j + col >= 0 && j + col < cols)) {
                        if (currentGen[i + row][j + col] && (i != 0 || j != 0)) {
                            aliveNeighbors++;
                        }
                    } */


                }
            }


        } else {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {

                    if ((i + row >= 0 && i + row < rows) && (j + col >= 0 && j + col < cols)) {
                        if (currentGen[i + row][j + col] && (i != 0 || j != 0)) {
                            aliveNeighbors++;
                        }
                    }
                }
            }

        }


        return aliveNeighbors;
    }

    public void next(int gridType) {
        // hw 20 next
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isLive(i, j) && (getNeighbors(i, j, gridType) == 2 || getNeighbors(i, j, gridType) == 3)) {
                    //Any live cell with two or three live neighbors survives in the next generation.
                    nextGen[i][j] = true;
                } else if ((!isLive(i, j)) && getNeighbors(i, j, gridType) == 3) {
                    //Any dead cell with three live cells becomes a live cell.
                    nextGen[i][j] = true;
                } else {
                    nextGen[i][j] = false;
                    //All other live cells die in the next generation. Similarly, all other dead cells remain dead.
                }
            }

        }

        //swap
        boolean[][] temp = currentGen;
        currentGen = nextGen;
        nextGen = temp;

        genNum++;

    }

    public void read(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            int lines = 0;
            while (scanner.hasNextLine() && lines < rows) {
                String line = scanner.nextLine();
                if (line.length() > cols) {
                    break;
                }
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == 'O' || line.charAt(i) == '*') {
                        live(lines, i);
                    }
                }
                lines++;

            }
        } catch (IOException e) {
            System.out.println("Could not read file");
        }

    }


    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (currentGen[i][j]) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void paint(Canvas canvas, int pixel) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (currentGen[i][j]) {
                    canvas.fill(i * pixel, i * pixel + pixel, j * pixel, j * pixel + pixel);
                }
            }
        }

    }

    public void center() {
        int smallRow = Integer.MAX_VALUE;
        int bigRow = Integer.MIN_VALUE;
        int smallCol = Integer.MAX_VALUE;
        int bigCol = Integer.MIN_VALUE;

        int centerCol;
        int centerRow;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (currentGen[i][j]) {
                    if (i < smallRow) {
                        smallRow = i;
                    }

                    if (i > bigRow) {
                        bigRow = i;
                    }

                    if (j < smallCol) {
                        smallCol = j;
                    }

                    if (j > bigCol) {
                        bigCol = j;
                    }
                }
            }
        }

        centerCol = (bigCol + smallCol) / 2;
        centerRow = (bigRow + smallRow) / 2;

        int offSetR = (rows / 2) - centerRow;
        int offSetC = (cols / 2) - centerCol;

        boolean[][] temp = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (currentGen[i][j]) {
                    temp[i + offSetR][j + offSetC] = true;
                }
            }
        }

        currentGen = temp;

    }


}





