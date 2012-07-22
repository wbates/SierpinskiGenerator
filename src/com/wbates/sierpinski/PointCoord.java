package com.wbates.sierpinski;

public class PointCoord {

  private int x_val, y_val;
  
  public PointCoord(int x, int y) {
    setPoint(x, y);
  }
  
  public void setPoint(int x, int y) {
    x_val = x;
    y_val = y;
  }
  
  public int[] getPoint() {
    int[] a = {x_val,y_val};
    return a;
  }
  
  public int getX() {
    return x_val;
  }
  
  public int getY() {
    return y_val;
  }

}
