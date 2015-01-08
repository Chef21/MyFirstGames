package com.example.fasttouch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;

/**
 * This class custom the Activity on the main screens.
 * It contains an action, which starts the game and set the animations
 * for the background birds. 
 * 
 * Date: 20.12.2014
 * 
 * @author Sayan.Vaaheesan
 *
 */
public class StartAcitivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Make View FullScreen
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_start);	
	}
	
	/**
	 * This Method start the game
	 * @param view
	 */
	public void startGame(View view) {
		Intent intent = new Intent(StartAcitivity.this, MainActivity.class);
		startActivity(intent);
	}
	

	/**
	 * Show Settings
	 */
	@SuppressWarnings("deprecation")
	public void showSettings(View view) {
		//We need to get the instance of the LayoutInflater, use the context of this activity
		LayoutInflater inflater = (LayoutInflater) StartAcitivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		//Inflate the view from a predefined XML layout (no need for root id, using entire layout)
		View layout = inflater.inflate(R.layout.setting_popup, null);
		
		//Get the devices screen density to calculate correct pixel sizes
	    float density=StartAcitivity.this.getResources().getDisplayMetrics().density;
	    
	    // create a focusable PopupWindow with the given layout and correct size
	    final PopupWindow pw = new PopupWindow(layout, (int)density*240, (int)density*285, true);
	    
	    //Button to close the pop-up  
	    ((ImageView) layout.findViewById(R.id.imageViewClose)).setOnClickListener(new OnClickListener() {
	    	public void onClick(View v) {
	    		pw.dismiss();
	        }
	    });
	    
	    //Set up touch closing outside of pop-up
	    pw.setBackgroundDrawable(new BitmapDrawable());
	    pw.setTouchInterceptor(new OnTouchListener() {
	    	public boolean onTouch(View v, MotionEvent event) {
	    		if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
	    			pw.dismiss();
	    			return true;
	    		}
	    		return false;
	    	}
	    });
	    pw.setOutsideTouchable(true);
	    
	    // display the pop-up in the center
	    pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
	}

	/**
	 * This Method custom what happens when the user press the back button 
	 */
	public void onBackPressed() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		
	}
}
