package com.example.fasttouch;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

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
	 * The ImageButton AudioOnOff
	 */
	private ImageButton imageButtonAudioOnOff;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Make View FullScreen
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_start);	
		
		imageButtonAudioOnOff = (ImageButton) findViewById(R.id.imageButtonAudioOnOff);	
		SharedPreferences sharedPreferences = getSharedPreferences("audio", MODE_PRIVATE); 
		String audioState = sharedPreferences.getString("mute", null);
		if (audioState == null) {
			SharedPreferences.Editor editor = getSharedPreferences("audio", MODE_PRIVATE).edit();
			editor.putString("mute", "unmute");
			editor.commit();
			imageButtonAudioOnOff.setImageResource(R.drawable.audio_on);
		} else if (audioState.equals("mute")) {
			imageButtonAudioOnOff.setImageResource(R.drawable.audio_off);
		} else if (audioState.equals("unmute")) {
			imageButtonAudioOnOff.setImageResource(R.drawable.audio_on);
		}
	}
	
	/**
	 * This Method start the game
	 * @param view
	 */
	public void startGame(View view) {
		Intent intent = new Intent(StartAcitivity.this, MainActivity.class);
		
		startActivity(intent);
	}
	
	public void audioOnOff(View view) {
		SharedPreferences prefs = getSharedPreferences("audio", MODE_PRIVATE); 
		String audioState = prefs.getString("mute", null);
		if (audioState.equals("mute")) {
			SharedPreferences.Editor editor = getSharedPreferences("audio", MODE_PRIVATE).edit();
			editor.putString("mute", "unmute");
			editor.commit();
			imageButtonAudioOnOff.setImageResource(R.drawable.audio_on);
		} else if (audioState.equals("unmute")) {
			SharedPreferences.Editor editor = getSharedPreferences("audio", MODE_PRIVATE).edit();
			editor.putString("mute", "mute");
			editor.commit();
			imageButtonAudioOnOff.setImageResource(R.drawable.audio_off);
		}
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
