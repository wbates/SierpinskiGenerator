package com.wbates.android.sierpinski.generator;

import java.io.File;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
    canvas = new SierpinskiCanvas(
        this,
        i.getIntExtra("sb1x",0),
        i.getIntExtra("sb1y",0),
        i.getIntExtra("sb2x",0),
        i.getIntExtra("sb2y",0),
        i.getIntExtra("sb3x",0),
        i.getIntExtra("sb3y",0)
        );

    FrameLayout main = (FrameLayout) findViewById(R.id.sierpinski_draw_view);
    main.addView(canvas);
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
    case 1:  saveToImage();
    return true;
    case 2:  text = "Future feature: Share image to Facebook";
    toast = Toast.makeText(context, text, duration);
    toast.show();
    return true;
    }

    return false;
  }

  public void saveToImage() {
    // TODO image saving, but not to gallery
    Context context = getApplicationContext();
    Toast toast;
    int duration = Toast.LENGTH_SHORT;
    CharSequence text = "";

    File folder = new File(Environment.getExternalStorageDirectory() + "/sierpinski");
    boolean success = false;

    if (!folder.exists()) {
      success = folder.mkdir();
    } else {
      success = true;
    }
    
    if (success) {
      String imagefilename = folder.getAbsolutePath() + "/sier.png";
      text = "Image saved to " + imagefilename;

      try {
        canvas.getDrawCache().compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(new File(imagefilename)));
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } else {
      text = "Unable to create folder: " + folder.getAbsolutePath(); 
    }  
    toast = Toast.makeText(context, text, duration);
    toast.show();
  }
}
