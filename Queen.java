// Cheung Joshua November 2017
// Queen.java

import java.util.*;
import java.io.*;
import java.lang.*;
class Queen
{
  int col;
  int row;
  public Queen(int column, int r)
  {
    col = column;
    row = r;
  }
  public boolean isAttacking(Stack<Queen> s)
  {
    for(Queen i : s)
    {
      if(this.col == i.col || this.row == i.row)
      {
        return true;
      }
      else if(Math.abs(this.col - i.col) == Math.abs(this.row - i.row))
      {
        return true;
      }
    }
    return false;
  }
}
