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

  SeekBar sb[][] = new SeekBar[2][3];
  TextView tv[][] = new TextView[2][3];
  String dimlabel[] = {"x","y"};
  Button btngen;
  int dimsize[] = {0,0};

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    DisplayMetrics displaymetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

    dimsize[0] = displaymetrics.widthPixels;
    dimsize[1] = displaymetrics.heightPixels;

    for(int dimension=0;dimension<2;dimension++) {
      for(int idval=0;idval<3;idval++) {
        int id = 0;
        try {
          id = (Integer) R.id.class.getField("sb"+(idval+1)+dimlabel[dimension]).get(null);
          //sbid[dimension][idval] = id;
          sb[dimension][idval] = (SeekBar) findViewById(id);
          sb[dimension][idval].setOnSeekBarChangeListener(this);
          
          id = (Integer) R.id.class.getField("tv"+(idval+1)+dimlabel[dimension]).get(null);
          tv[dimension][idval] = (TextView) findViewById(id);
          tv[dimension][idval].setText("Point " + idval + " " + dimlabel[dimension] + " Value: " + 
              progressToDim(sb[dimension][idval].getProgress(),dimsize[dimension]));
          
        } catch (IllegalArgumentException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (SecurityException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (NoSuchFieldException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    
    btngen = (Button) findViewById(R.id.btngen);
    btngen.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, DrawActivity.class);
        for(int dimension=0;dimension<2;dimension++) {
          for(int idval=0;idval<3;idval++) {
            i.putExtra("sb"+(idval+1)+dimlabel[dimension], progressToDim(sb[dimension][idval].getProgress(),dimsize[dimension]));
          }
        }
        Context context = getApplicationContext();
        CharSequence text = "Generating Sierpinski ... patience if it takes a while to see something.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        MainActivity.this.startActivity(i);
      }
    });

  }

  @Override
  public void onProgressChanged(SeekBar bar, int progress, boolean fromUser)
  {
    int dimprogress[] = {progressToDim(progress,dimsize[0]),progressToDim(progress,dimsize[1])};

    // TODO change this to more optimized logic
    switch(bar.getId()) {
    case R.id.sb1x: tv[0][0].setText("Point 1 X Value: " + dimprogress[0]);
    break;
    case R.id.sb1y: tv[1][0].setText("Point 1 Y Value: " + dimprogress[1]);
    break;
    case R.id.sb2x: tv[0][1].setText("Point 2 X Value: " + dimprogress[0]);
    break;
    case R.id.sb2y: tv[1][1].setText("Point 2 Y Value: " + dimprogress[1]);
    break;
    case R.id.sb3x: tv[0][2].setText("Point 3 X Value: " + dimprogress[0]);
    break;
    case R.id.sb3y: tv[1][2].setText("Point 3 Y Value: " + dimprogress[1]);
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
