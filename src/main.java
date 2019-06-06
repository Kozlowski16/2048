import java.util.Arrays;
import java.util.Random;

public class main {
    public static int[][] board;
    public static int size;
    public static Random r;
    static boolean up;
    static boolean down;
    static boolean left;
    static boolean right;

    public static void main(String[] args){

        size=3;
        r= new Random();
        board = new int[size][size];
        for(int a=0;a<10;a++){
            generateNumber();
        }
        new gui();
        for (int i=0;i<0;i++){
            int w=r.nextInt(4);
            if(w==0)
                move('a',board);
            else if(w==1)
                move('s',board);
            else if (w==2)
                move('d',board);
            else if(w==3){
                move('w',board);
            }
        }
        display();
        System.out.println("done");

    }
    public static void runCheck(){
        int[][] copy;
        copy=main.getBoardCopy();
        move('w',copy);
        up=same(copy,board);

        copy=getBoardCopy();
        move('a',copy);
        left=same(copy,board);

        copy=getBoardCopy();
        main.move('s',copy);
        down=same(copy,board);

        copy=getBoardCopy();
        move('d',copy);
        right=same(copy,board);

        System.out.println("up: " + up);
        System.out.println("down: " + down);
        System.out.println("left: " + left);
        System.out.println("right: " + right);

    }
    private static boolean same(int[][] a, int[][] b) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (a[y][x] != b[y][x])
                    return false;
            }
        }
        return true;
    }

    public static void turn(char movement){
        switch (movement) {
            case 'w':
                if(!up) {
                    move(movement, board);
                    someShit();
                }
                break;
            case 'd':
                if(!right) {
                    move(movement, board);
                    someShit();
                }
                break;
            case 's':
                if(!down){
                    move(movement,board);
                    someShit();}
                break;
            case 'a':
                if(!left) {
                    move(movement, board);
                    someShit();
                }
                break;

        }

    }
    private static void someShit(){
        generateNumber();
        display();
        runCheck();
        if(up&&down&&left&&right)
            System.out.println("GAME OVER");
    }
    private static int openSpaces(){
        int count=0;
        for(int[]a:board){
            for(int b:a)
                if(b==0)
                    count++;
        }
        return count;
    }
    public static void generateNumber(){
        int count=openSpaces();

        if(count!=0) {
            int number= r.nextInt(10)==9? 4:2;
            count = r.nextInt(count) + 1;
            for (int y = 0; y < size; y++) {
                if (count == 0) break;
                for (int x = 0; x < size; x++) {
                    if (board[y][x] == 0) {
                        count--;
                        if (count == 0) {
                            board[y][x] = number;
                            break;
                        }
                    }
                }
            }
        }
    }
    public static void display(){
        for (int[] a: board){
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }
    public static void move(char direction,int[][] Board){
        switch (direction) {
            case 'w':
                moveUp(Board);
                combineUp(0,0,Board);
                moveUp(Board);
                break;
            case 'd':
                moveLeft(size-1,0,Board);
                combineLeft(size-1,0,Board);
                moveLeft(size-1,0,Board);
                break;
            case 's':
                moveDown(0,size-1,Board);
                combineDown(0,size-1,Board);
                moveDown(0,size-1,Board);

                break;
            case 'a':
                moveRight(0,0,Board);
                combineRight(0,0,Board);
                moveRight(0,0,Board);
                break;

        }

    }
    public static void moveUp(int[][] Board){
        moveUp(0,0,Board);
    }
    public static void moveUp(int x, int y,int[][] Board){
        if(y!=size-1) {
            if (Board[y][x] != 0)
                moveUp(x, y + 1,Board);
            else {
                for (int y2 = y + 1; y2 < size; y2++) {
                    if (Board[y2][x] != 0) {
                        Board[y][x] = Board[y2][x];
                        Board[y2][x] = 0;
                        break;
                    }
                }
                moveUp(x, y + 1,Board);
            }
        }
        else if(x!=size-1){
            moveUp(x+1,0,Board);
        }
    }
    public static void moveRight(int x, int y, int [][] Board){
        if(x!=size-1) {
            if (Board[y][x] != 0)
                moveRight(x+1,y,Board);
            else {
                for (int x2 = x +1; x2 <size; x2++) {
                    if (Board[y][x2] != 0) {
                        Board[y][x] = Board[y][x2];
                        //System.out.println("moved (" + x2 + ", " + y + ") (" +x + ", " + y + ")");
                        Board[y][x2] = 0;
                        break;
                    }
                }
                moveRight(x+1, y,Board);
            }
        }
        else if(y!=size-1){
            moveRight(0,y+1,Board);
        }
    }
    public static void moveLeft(int x, int y, int[][] Board){
        if(x!=0) {
            if (Board[y][x] != 0)
                moveLeft(x-1, y,Board);
            else {
                for (int x2 = x - 1; x2 >= 0; x2--) {
                    if (Board[y][x2] != 0) {
                        Board[y][x] = Board[y][x2];
                        //System.out.println("moved (" + x2 + ", " + y + ") (" +x + ", " + y + ")");
                        Board[y][x2] = 0;
                        break;
                    }
                }
                moveLeft(x-1, y,Board);
            }
        }
        else if(y!=size-1){
            moveLeft(size-1,y+1,Board);
        }
    }
    public static void moveDown(int x, int y, int[][] Board){
        if(y!=0) {
            if (Board[y][x] != 0)
                moveDown(x, y - 1,Board);
            else {
                for (int y2 = y - 1; y2 >= 0; y2--) {
                    if (Board[y2][x] != 0) {
                        Board[y][x] = Board[y2][x];
                        //System.out.println("moved (" + x + ", " + y2 + ") (" +x + ", " + y + ")");
                        Board[y2][x] = 0;
                        break;
                    }
                }
                moveDown(x, y -1,Board);
            }
        }
        else if(x!=size-1){
            moveDown(x+1,size-1,Board);
        }
    }
    public static void combineUp(int x, int y, int[][] Board){
        if(y!=size-1){
            if (Board[y][x] !=0&& Board[y][x] == Board[y + 1][x]) {
                Board[y][x] = Board[y][x] * 2;
                Board[y + 1][x] = 0;
            }
            combineUp(x,y+1,Board);
        }
        else if(x!=size-1){

            combineUp(x+1,0,Board);
        }
    }
    public static void combineRight(int x, int y, int[][] Board){
        if(x!=size-1){
            if (Board[y][x] !=0&& Board[y][x] == Board[y][x+1]) {
                Board[y][x] = Board[y][x] * 2;
                Board[y ][x+1] = 0;
            }
            combineRight(x+1,y,Board);
        }
        else if(y!=size-1){

            combineRight(0,y+1,Board);
        }
    }
    public static void combineDown(int x, int y, int[][] Board){
        if(y!=0){
            if (Board[y][x] !=0&& Board[y][x] == Board[y -1][x]) {
                Board[y][x] = Board[y][x] * 2;
                Board[y -1][x] = 0;
            }
            combineDown(x,y-1,Board);
        }
        else if(x!=size-1){
            combineDown(x+1,size-1,Board);
        }
    }
    public static void combineLeft(int x, int y, int[][] Board){
        if(x!=0){
            if (Board[y][x] !=0&& Board[y][x] == Board[y][x-1]) {
                Board[y][x] = Board[y][x] * 2;
                Board[y][x-1] = 0;
            }
            combineLeft(x-1,y,Board);
        }
        else if(y!=size-1){
            combineLeft(size-1,y+1,Board);
        }
    }
    public static int[][] getBoardCopy(){
        int [][] copy = new int[size][];
        for(int i=0;i<size;i++){
            copy[i]=Arrays.copyOf(board[i],size);
        }
        return copy;
    }
}
