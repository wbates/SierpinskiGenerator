package com.wbates.android.sierpinski.generator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.util.DisplayMetrics;

public class MainActivity extends Activity implements OnSeekBarChangeListener {

  SeekBar sb1x,sb1y,sb2x,sb2y,sb3x,sb3y;
  TextView tv1x,tv1y,tv2x,tv2y,tv3x,tv3y;
  Button btngen;
  int height,width;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    DisplayMetrics displaymetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

    height = displaymetrics.heightPixels;
    width = displaymetrics.widthPixels;

    sb1x = (SeekBar) findViewById(R.id.sb1x);
    sb1y = (SeekBar) findViewById(R.id.sb1y);
    sb1x.setOnSeekBarChangeListener(this);
    sb1y.setOnSeekBarChangeListener(this);

    sb2x = (SeekBar) findViewById(R.id.sb2x);
    sb2y = (SeekBar) findViewById(R.id.sb2y);
    sb2x.setOnSeekBarChangeListener(this);
    sb2y.setOnSeekBarChangeListener(this);

    sb3x = (SeekBar) findViewById(R.id.sb3x);
    sb3y = (SeekBar) findViewById(R.id.sb3y);
    sb3x.setOnSeekBarChangeListener(this);
    sb3y.setOnSeekBarChangeListener(this);

    tv1x = (TextView) findViewById(R.id.tv1x);
    tv1y = (TextView) findViewById(R.id.tv1y);    
    tv1x.setText("Point 1 X Value: " + progressToDim(sb1x.getProgress(),width));
    tv1y.setText("Point 1 Y Value: " + progressToDim(sb1y.getProgress(),height));

    tv2x = (TextView) findViewById(R.id.tv2x);
    tv2y = (TextView) findViewById(R.id.tv2y);    
    tv2x.setText("Point 2 X Value: " + progressToDim(sb2x.getProgress(),width));
    tv2y.setText("Point 2 Y Value: " + progressToDim(sb2y.getProgress(),height));

    tv3x = (TextView) findViewById(R.id.tv3x);
    tv3y = (TextView) findViewById(R.id.tv3y);    
    tv3x.setText("Point 3 X Value: " + progressToDim(sb3x.getProgress(),width));
    tv3y.setText("Point 3 Y Value: " + progressToDim(sb3y.getProgress(),height));

    btngen = (Button) findViewById(R.id.btngen);
    btngen.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, DrawActivity.class);
        i.putExtra("sb1x", progressToDim(sb1x.getProgress(),width));
        i.putExtra("sb1y", progressToDim(sb1y.getProgress(),height));
        i.putExtra("sb2x", progressToDim(sb2x.getProgress(),width));
        i.putExtra("sb2y", progressToDim(sb2y.getProgress(),height));
        i.putExtra("sb3x", progressToDim(sb3x.getProgress(),width));
        i.putExtra("sb3y", progressToDim(sb3y.getProgress(),height));
        Context context = getApplicationContext();
        CharSequence text = "Generating Sierpinski ... patience if it takes a while to see something.";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        MainActivity.this.startActivity(i);
      }
    });

  }

  @Override
  public void onProgressChanged(SeekBar bar, int progress, boolean fromUser)
  {
    int progress_w = progressToDim(progress,width);
    int progress_h = progressToDim(progress,height);

    switch(bar.getId()) {
    case R.id.sb1x: tv1x.setText("Point 1 X Value: " + progress_w);
    break;
    case R.id.sb1y: tv1y.setText("Point 1 Y Value: " + progress_h);
    break;
    case R.id.sb2x: tv2x.setText("Point 2 X Value: " + progress_w);
    break;
    case R.id.sb2y: tv2y.setText("Point 2 Y Value: " + progress_h);
    break;
    case R.id.sb3x: tv3x.setText("Point 3 X Value: " + progress_w);
    break;
    case R.id.sb3y: tv3y.setText("Point 3 Y Value: " + progress_h);
    break;
    default:        break;
    }
  }

  @Override
  public void onStartTrackingTouch(SeekBar seekBar)
  {
  }

  @Override
  public void onStopTrackingTouch(SeekBar seekBar)
  {
  }

  private int progressToDim(int progress, int dimension) {
    return (progress * dimension) / 1000;
  }
}
