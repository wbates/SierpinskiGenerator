package com.wbates.android.sierpinski.generator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class CanvasSocialShare {
  
  private Uri savedimagefile;
  private Activity activity;
  
  public CanvasSocialShare(SierpinskiCanvas ca, Context co, Activity a) {
    CanvasImageSaver c = new CanvasImageSaver(ca, co, false);
    activity = a;
    savedimagefile = c.saveToImage();
  }
  
  public void shareImage() {
    Intent share = new Intent(Intent.ACTION_SEND);
    share.setType("image/png");
    share.putExtra(Intent.EXTRA_STREAM,savedimagefile);
    share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    activity.startActivity(Intent.createChooser(share, "Share Image"));
  }

}
