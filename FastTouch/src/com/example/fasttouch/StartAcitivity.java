package com.example.fasttouch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

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
	
	/**
	 * The ImageView bird one
	 */
	private ImageView imageViewBirdOne;
	
	/**
	 * The ImageView bird two
	 */
	private ImageView imageViewBirdTwo;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Make View FullScreen
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_start);	
		
		// Set Bird Animations
	    AnimationDrawable animationOne = new AnimationDrawable();
	    animationOne.addFrame(getResources().getDrawable(R.drawable.bird_up), 500);
	    animationOne.addFrame(getResources().getDrawable(R.drawable.bird_down), 500);
	    animationOne.setOneShot(false);
	    imageViewBirdOne = (ImageView) findViewById(R.id.imageViewBirdOne);
	    imageViewBirdOne.setBackgroundDrawable(animationOne);
	    
	    AnimationDrawable animationTwo= new AnimationDrawable();
	    animationTwo.addFrame(getResources().getDrawable(R.drawable.bird_down), 500);
	    animationTwo.addFrame(getResources().getDrawable(R.drawable.bird_up), 500);
	    animationTwo.setOneShot(false);
		imageViewBirdTwo = (ImageView) findViewById(R.id.imageViewBirdTwo);
		imageViewBirdTwo.setBackgroundDrawable(animationTwo);
		
		animationOne.start();
		animationTwo.start();
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
	 * This Method custom what happens when the user press the back button 
	 */
	public void onBackPressed() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		
	}
}
