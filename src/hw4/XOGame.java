package hw4;

import java.util.Random;
import java.util.Scanner;

public class XOGame {
    static final int SIZE = 3;
    static final int DOTS_TO_WIN =3;
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';
    static char[][] map;
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWinLin(DOT_X, DOTS_TO_WIN)){
                System.out.println("YOU WINNER");
                break;
            }
            if (isFull()){
                System.out.println("Draw");
                break;
            }
            aiTurn();
            printMap();
            if (checkWinLin(DOT_O, DOTS_TO_WIN)){
                System.out.println("AI WINNER");
            }
            if (isFull()){
                System.out.println("Draw");
                break;
            }
        }
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }

        }

    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <=SIZE ; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%c ", map[i][j]);
            }
            System.out.println();
        }
    }
    public static void humanTurn(){
        int x, y;
        do {
            System.out.println("Введите X,Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        }
        while (!isCellValid(y,x));
        map[y][x] = DOT_X;

    }

    public static boolean isCellValid(int y, int x){
        if (y<0 || x < 0 || y >= SIZE || x >= SIZE){
            return false;
        }
        return map[y][x] == DOT_EMPTY;

    }
    public static void aiTurn(){
        int x, y;
        do {
           y = random.nextInt(SIZE);
           x = random.nextInt(SIZE);
        }
        while (!isCellValid(y,x));
        map[y][x] = DOT_O;

    }
    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
    static boolean checkLine(int cy, int cx, int vy, int vx, char dot, int dotsToWin) {
        if (cx + vx * (dotsToWin - 1) > SIZE - 1 || cy + vy * (dotsToWin - 1) > SIZE - 1 ||
                cy + vy * (dotsToWin - 1) < 0) {
            return false;
        }

        for (int i = 0; i < dotsToWin; i++) {
            if (map[cy + i * vy][cx + i * vx] != dot) {
                return false;
            }
        }
        return true;
    }

    static boolean checkWinLin(char dot, int dotsToWin) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkLine(i, j, 0, 1, dot, dotsToWin) ||
                        checkLine(i, j, 1, 0, dot, dotsToWin) ||
                        checkLine(i, j, 1, 1, dot, dotsToWin) ||
                        checkLine(i, j, -1, 1, dot, dotsToWin)) {
                    return true;
                }
            }
        }
        return false;
    }

}


