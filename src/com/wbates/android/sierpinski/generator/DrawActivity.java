package com.wbates.android.sierpinski.generator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.FrameLayout;
import android.widget.TextView;

public class DrawActivity extends Activity {

  TextView tv;
  SierpinskiCanvas canvas;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_draw);

    Intent i = getIntent();
    int sb1x = i.getIntExtra("sb1x",0);
    int sb1y = i.getIntExtra("sb1y",0);
    int sb2x = i.getIntExtra("sb2x",0);
    int sb2y = i.getIntExtra("sb2y",0);
    int sb3x = i.getIntExtra("sb3x",0);
    int sb3y = i.getIntExtra("sb3y",0);
    
    FrameLayout main = (FrameLayout) findViewById(R.id.sierpinski_draw_view);
    main.addView(new SierpinskiCanvas(this, sb1x,sb1y,sb2x,sb2y,sb3x,sb3y));
  }

}
