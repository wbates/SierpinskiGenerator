package com.wbates.android.sierpinski.generator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
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
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.activity_draw, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    Context context = getApplicationContext();
//    CharSequence text = "";
//    int duration = Toast.LENGTH_SHORT;
//    Toast toast;

    switch(item.getItemId()) {
    case R.id.menu_save_image:  CanvasImageSaver imagesaver = new CanvasImageSaver(canvas, context);
    imagesaver.saveToImage();
    return true;
    case R.id.menu_share_fb:  CanvasSocialShare socialshare = new CanvasSocialShare(canvas,context,this);
      socialshare.shareImage();
//    toast = Toast.makeText(context, text, duration);
//    toast.show();
    return true;
    }

    return false;
  }

}
