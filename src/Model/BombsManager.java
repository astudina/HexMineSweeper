package Model;

import java.util.Random;

public class BombsManager {
    private int row;
    private int col;
    private int bomb;
    private int[][] bombs;

    public BombsManager(int bomb) {
        this.row = 0;
        this.col = 0;
        this.bomb = bomb;

    }

    public void generate(int x, int y) {
        bombs = new int[row][col];
        Random r = new Random();
        int count = 0;
        //генерация самих бомб
        while (count < bomb) {
            int i = r.nextInt(row);
            int j = r.nextInt(col);
            if (bombs[i][j] != -1 & (i != x | j != y)) {
                bombs[i][j] = -1;
                count++;
            }
        }
        //генерируем числа вокруг бомб
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (bombs[i][j] != -1) {
                    bombs[i][j] = countNeighborsBomb(i, j);
                }
            }
        }
    }

    private int countNeighborsBomb(int i, int j) {
        int count = 0;
        if (isBomb(i - 1, j)) count++;
        if (isBomb(i + 1, j)) count++;

        if (isBomb(i , j - 1)) count++;
        if (isBomb(i, j + 1)) count++;

        if(j % 2 == 0){
            if (isBomb(i - 1, j - 1)) count++;
            if (isBomb(i - 1, j + 1)) count++;
        } else {
            if (isBomb(i + 1, j - 1)) count++;
            if (isBomb(i + 1, j + 1)) count++;
        }




        return count;
    }

    public boolean isBomb(int i, int j) {
        return i >= 0 && i < row && j >= 0 && j < col && bombs[i][j] == -1;
    }


    public int[][] getBombs() {
        return bombs;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getBomb() {
        return bomb;
    }

    public void setBomb(int bomb) {
        this.bomb = bomb;
    }
}
