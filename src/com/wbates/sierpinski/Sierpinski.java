package com.wbates.sierpinski;

import java.io.Serializable;
import java.util.Random;
import java.lang.Math;

public class Sierpinski implements Serializable {
  private PointCoord points[] = new PointCoord[3];
  private PointCoord last = null;
  private int target = 0;
  
  private Random rg = new Random();
  
  public Sierpinski() {
    last = new PointCoord(rg.nextInt(1000),rg.nextInt(1000));
  }
  
  public void setPoints(PointCoord a, PointCoord b, PointCoord c) {
    PointCoord tempSp[] = {a,b,c};
    setPoints(tempSp);
  }
  
  public void setPoints(PointCoord sp[]) {
    for(int i=0;i<3;i++) {
      points[i] = sp[i];
    }
  }
  
  public PointCoord[] getPoints() {
    return points;
  }
  
  public PointCoord getNext() {
    target = rg.nextInt(3);
    
    int x = Math.abs((points[target].getX() + last.getX()) / 2);
    int y = Math.abs((points[target].getY() + last.getY()) / 2);
    
    return last = new PointCoord(x,y);
  }
  
  public int getTarget() {
    return target;
  }
}
