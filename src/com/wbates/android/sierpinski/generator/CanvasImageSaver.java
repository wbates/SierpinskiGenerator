package com.wbates.android.sierpinski.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.widget.Toast;

public class CanvasImageSaver {
  
  private SierpinskiCanvas canvas;
  private Context context;
  
  public CanvasImageSaver(SierpinskiCanvas ca, Context co) {
    canvas = ca;
    context = co;
  }
  
  public void saveToImage() {
    Toast toast;
    int duration = Toast.LENGTH_SHORT;
    CharSequence text = "";
    Date date = new Date();
    long savetime = date.getTime();

    File folder = new File(Environment.getExternalStorageDirectory() + "/sierpinski");
    boolean success = false;
    String imagefilename = "sier_" + savetime;
    String imagefileextension = "png";

    if (!folder.exists()) {
      success = folder.mkdir();
    } else {
      success = true;
    }
    
    if (success) {
      String imagefullpathfilename = folder.getAbsolutePath() + "/" + imagefilename + "." + imagefileextension;

      try {
        canvas.getDrawCache().compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(new File(imagefullpathfilename)));
      } catch (Exception e) {
        e.printStackTrace();
      } 
      
      text = "Image saved to " + imagefullpathfilename;
            
      saveMediaEntry(imagefullpathfilename,imagefilename,"",savetime,0,null);
    } else {
      text = "Unable to create folder: " + folder.getAbsolutePath(); 
    }  
    toast = Toast.makeText(context, text, duration);
    toast.show();
  }
  
  private Uri saveMediaEntry(String imagePath,String title,String description,long dateTaken,int orientation,Location loc) {
    ContentValues v = new ContentValues();
    v.put(Images.Media.TITLE, title);
    v.put(Images.Media.DISPLAY_NAME, title);
    v.put(Images.Media.DESCRIPTION, description);
    v.put(Images.Media.DATE_ADDED, dateTaken);
    v.put(Images.Media.DATE_TAKEN, dateTaken);
    v.put(Images.Media.DATE_MODIFIED, dateTaken) ;
    v.put(Images.Media.MIME_TYPE, "image/png");
    v.put(Images.Media.ORIENTATION, orientation);

    File f = new File(imagePath) ;
    File parent = f.getParentFile() ;
    String path = parent.toString().toLowerCase() ;
    String name = parent.getName().toLowerCase() ;
    v.put(Images.ImageColumns.BUCKET_ID, path.hashCode());
    v.put(Images.ImageColumns.BUCKET_DISPLAY_NAME, name);
    v.put(Images.Media.SIZE,f.length()) ;
    f = null ;
    
    if( loc != null ) {
      v.put(Images.Media.LATITUDE, loc.getLatitude());
      v.put(Images.Media.LONGITUDE, loc.getLongitude());
    }
    v.put("_data",imagePath) ;
    ContentResolver c = context.getContentResolver() ;
    return c.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, v);
  }
}
