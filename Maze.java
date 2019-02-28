import java.util.*;
import java.io.*;
public class Maze {
  private char[][] board;
  private boolean ani = false;
  public Maze(String fn) throws FileNotFoundException {
    File f = new File(fn);
    Scanner sc = new Scanner(f);
    int rv = 0;
    int cv = 0;
    boolean cco = true;
    while(sc.hasNextLine()) { //row count and col count
      String tem = sc.nextLine();
      if(cco) { //count length of columns only first time
        cco = false;
        for(int q = 0; q < tem.length(); q++) {
          cv++;
        }
      }
      rv++;
    }
    board = new char[rv][cv]; //initializes board with row and col
    Scanner snc = new Scanner(f);
    int trco = 0;
    while(snc.hasNextLine()) { //fills board with chars
      String ert = snc.nextLine();
      for(int w = 0; w < ert.length(); w++) {
        board[trco][w] = ert.charAt(w);
      }
      trco++;
    }
    boolean stf = false;
    boolean enf = false;
    for(int qd = 0; qd < board.length; qd++) { //checks if S and E in board
      for(int wd = 0; wd < board[qd].length; wd++) {
        if(board[qd][wd] == 'S') {stf = true;}
        if(board[qd][wd] == 'E') {enf = true;}
      }
    }
    if(!(stf && enf)) { //throws exception when !(stf && enf)
      throw new IllegalStateException("Board must contain S and E");
    }
  }
  private void wait(int millis) {
    try {
       Thread.sleep(millis);
    }
    catch (InterruptedException e) {}
  }
  public void clearTerminal() {
    System.out.println("\033[2J\033[1;1H");
  }
  public void setAnimate(boolean b) {
    ani = b;
  }
  public String toString() {
    String s = "";
    for(int q = 0; q < board.length; q++) {
      for(int w = 0; w < board[q].length; w++) {
        s += board[q][w];
      }
      s += "\n";
    }
    return s;
  }
  public int solve() {
    int sr = 0;
    int sc = 0;
    for(int q = 0; q < board.length; q++) {
      for(int w = 0; w < board[q].length; w++) {
        if(board[q][w] == 'S') {sr = q; sc = w;}
      }
    }
    return sh(sr,sc);
  }
  private int sh(int r, int c) {
    if(ani){
        clearTerminal();
        System.out.println(this);
        wait(20);
    }
    if(board[r][c] == 'E') { //if hit end
      int atc = 0;
      for(int q = 0; q < board.length; q++) {
        for(int w = 0; w < board[q].length; w++) {
          if(board[q][w] == '@') {
            atc++;
          }
        }
      }
      return atc;
    }
    else if(cmRight(r,c)) { //can move right

    }
    else if(cmDown(r,c)) { //can move down

    }
    else if(cmLeft(r,c)) { //can move left

    }
    else if(cmUp(r,c)) { //can move up

    }
    else { //went everywhere but didn't find solution
      return -1;
    }
  }
  private boolean cmRight(int r, int c) {
    if(c >= board[0].length) {return false;}
    return board[r][c + 1] == ' ' || board[r][c + 1] == '@';
  }
  private boolean cmDown(int r, int c) {
    if(r >= board.length) {return false;}
    return board[r + 1][c] == ' ' || board[r + 1][c] == '@';
  }
  private boolean cmLeft(int r, int c) {
    if(c < 0) {return false;}
    return board[r][c - 1] == ' ' || board[r][c - 1] == '@';
  }
  private boolean cmUp(int r, int c) {
    if(r < 0) {return false;}
    return board[r - 1][c] == ' ' || board[r - 1][c] == '@';
  }
  public static void main(String[] asdf) {
    try {
      Maze test = new Maze("test.txt");
      test.setAnimate(true);
      System.out.println(test);
    }
    catch(Exception e) {
      System.out.println("file not found or S or E missing");
    }
  }
}
