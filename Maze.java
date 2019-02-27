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
}
