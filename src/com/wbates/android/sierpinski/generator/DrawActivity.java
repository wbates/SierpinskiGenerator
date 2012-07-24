package com.wbates.android.sierpinski.generator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
// TODO add menu options
import android.widget.Toast;

public class DrawActivity extends Activity {

  TextView tv;
  SierpinskiCanvas canvas;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_draw);

    Intent i = getIntent();
    // TODO consider removing these lines of code and accessing the intents directly in SierpinskiCanvas call
    int sb1x = i.getIntExtra("sb1x",0);
    int sb1y = i.getIntExtra("sb1y",0);
    int sb2x = i.getIntExtra("sb2x",0);
    int sb2y = i.getIntExtra("sb2y",0);
    int sb3x = i.getIntExtra("sb3x",0);
    int sb3y = i.getIntExtra("sb3y",0);
    
    FrameLayout main = (FrameLayout) findViewById(R.id.sierpinski_draw_view);
    main.addView(new SierpinskiCanvas(this, sb1x,sb1y,sb2x,sb2y,sb3x,sb3y));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    menu.add(1,1,0,R.string.menu_save_image);
    menu.add(1,2,1,R.string.menu_share_fb);
    return true;
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    Context context = getApplicationContext();
    CharSequence text = "";
    int duration = Toast.LENGTH_SHORT;
    Toast toast;

    switch(item.getItemId()) {
    case 1:  text = "Future feature: Save as PNG image in Gallery";
                                toast = Toast.makeText(context, text, duration);
                                toast.show();
                                return true;
    case 2:    text = "Future feature: Share image to Facebook";
                                toast = Toast.makeText(context, text, duration);
                                toast.show();
                                return true;
    }
    
    return false;
  }
}
