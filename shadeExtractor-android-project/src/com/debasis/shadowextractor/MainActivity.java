package com.debasis.shadowextractor;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.debasis.shadowextractor.R;
public class MainActivity extends Activity {

	Button b1, b2, b3;
	   ImageView im;
	   
	   private Bitmap bmp;
	   private Bitmap shadow;
	   
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_main);
	      
	      b1 = (Button) findViewById(R.id.button);
	      b2 = (Button) findViewById(R.id.button2);
	      b3 = (Button) findViewById(R.id.button3);
	      im = (ImageView) findViewById(R.id.imageView);
	      
	      BitmapDrawable abmp = (BitmapDrawable) im.getDrawable();
	      bmp = abmp.getBitmap();
	   }
	   public double brightness(int R, int G, int B,int A)
	   {
		   if(A == 0){
			   return 255.00;
		   }
		   double v = (
			   		R * R * .241 +
			   		G * G * .691 +
			   		B * B * .068);
	   	return  Math.sqrt(v);
	   }
	   public Bitmap replaceColour(Bitmap _bmp,int _r, int _g, int _b ){
		   shadow = Bitmap.createBitmap(_bmp.getWidth(),_bmp.getHeight(), _bmp.getConfig());
		   Bitmap bg = Bitmap.createBitmap(_bmp.getWidth(),_bmp.getHeight(), _bmp.getConfig());
		   for (int i = 0; i < _bmp.getWidth(); i++) {
		         for (int j = 0; j < _bmp.getHeight(); j++) {		        	 
		        	int p = _bmp.getPixel(i, j);	            
		            int r = Color.red(p);
		            int g = Color.green(p);
		            int b = Color.blue(p);
		            int a = Color.alpha(p);
		            double bt =  brightness(r,g,b,a);		          
		            int _a =(int) (255 - bt);
		            shadow.setPixel(i, j, Color.argb(_a, 0, 0, 0));		        	 
		            int __a = 255;
		            if(_a == 0){
		            	__a = 0;
		            }		            
		            bg.setPixel(i, j, Color.argb(__a, _r, _g, _b));
		         }
		      }
		   Bitmap resultBitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);
		      Canvas c = new Canvas(resultBitmap);
		      c.drawBitmap(bg, 0, 0, null);
		      Paint p = new Paint();
		      p.setAlpha(255);
		      c.drawBitmap(shadow, 0, 0, p);
		      return resultBitmap;
	   }
	   public void red(View view) {		  
	      Log.d("test -- ", "call the function");
	      Bitmap __bmp = replaceColour(bmp,121,0,0);
	      im.setImageBitmap(__bmp);
	   }
	   
	   public void green(View view){
		      Bitmap __bmp = replaceColour(bmp,0,141,54);
		      im.setImageBitmap(__bmp);
	   }
	   
	   public void white(View view){
		      Bitmap __bmp = replaceColour(bmp,255,255,255);
		      im.setImageBitmap(__bmp);
	   }
	   
	   public void yellow(View view) {
		      Bitmap __bmp = replaceColour(bmp,255,244,0);
		      im.setImageBitmap(__bmp);
	   }
	   
	   public void orrange(View view){
		      Bitmap __bmp = replaceColour(bmp,242,101,32);
		      im.setImageBitmap(__bmp);
	   }
	   
	   public void blue(View view){
		      Bitmap __bmp = replaceColour(bmp,0,114,188);
		      im.setImageBitmap(__bmp);
	   }
}
