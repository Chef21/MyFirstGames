package com.example.fasttouch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class StartAcitivity extends Activity {
	
	private ImageView imageViewBirdOne;
	private ImageView imageViewBirdTwo;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_start);	
		
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
	
	public void startGame(View view) {
		Intent intent = new Intent(StartAcitivity.this, MainActivity.class);
		startActivity(intent);
	}

	public void onBackPressed(){
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		
	}
}
