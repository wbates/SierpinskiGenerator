package com.wbates.android.sierpinski.generator;

import com.wbates.sierpinski.*;

import android.view.View;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Canvas;

@SuppressLint("ViewConstructor")
public class SierpinskiCanvas extends View {
  public Sierpinski shape = new Sierpinski();
  private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
  private Bitmap drawcache = null;

  public SierpinskiCanvas(Context context, int x1, int y1, int x2, int y2, int x3, int y3) {
    super(context);
    setDrawingCacheEnabled(true);
    
    shape.setPoints(new PointCoord(x1,y1), 
        new PointCoord(x2,y2), 
        new PointCoord(x3,y3));
    mPaint.setColor(0xFF00AA00);
  }
  
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    for(int i=0;i<500000;i++) {
      PointCoord p = shape.getNext();
      canvas.drawPoint((float)p.getX(), (float)p.getY(), mPaint);
    }
    drawcache = this.getDrawingCache();
  }
  
  public Bitmap getDrawCache() {
    return drawcache;
  }
}
