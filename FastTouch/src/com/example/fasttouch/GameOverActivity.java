package com.example.fasttouch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * This class custom the Actitivty on the game over screen
 * It contains an actions to restart the game und show the
 * score and highscore.
 * 
 * Date: 22.12.2014
 * 
 * @author Sayan.Vaaheean
 *
 */
public class GameOverActivity extends Activity {

	/**
	 * The TexView ScoreValue
	 */
	private TextView textViewFinalScoreValue;

	/**
	 * The TextView HighScoreValue
	 */
	private TextView textViewHighScoreValue;
	
	/**
	 * The FinalScore
	 */
	private String finalScore;
	
	/**
	 * The MediaPlayer GameOverSound
	 */
	public MediaPlayer mp_gameover;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Make View Fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_gameover);
		
		// start gameover Melodie
		mp_gameover = MediaPlayer.create(getApplicationContext(),R.raw.gameover);
		mp_gameover.start();
		
		// get score		
		Intent intent = getIntent();
		finalScore = intent.getStringExtra("score");
		textViewFinalScoreValue = (TextView) findViewById(R.id.textViewScore);
		textViewFinalScoreValue.setText(finalScore);
		
		// get highscore
		SharedPreferences sharedPreferences = this.getSharedPreferences("highScore", Context.MODE_PRIVATE);
		int oldScore = sharedPreferences.getInt("score", 0);
		if (Integer.parseInt(finalScore) > oldScore) {
			Editor editor = sharedPreferences.edit();
			editor.putInt("score", Integer.parseInt(finalScore));
			editor.commit();			
		}
		
		textViewHighScoreValue = (TextView) findViewById(R.id.textViewHighScore);
		textViewHighScoreValue.setText(sharedPreferences.getInt("score", 0) + "");
	}
	
	/**
	 * Restart the game
	 * @param view
	 */
	public void restart(View view) {
		Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
		startActivity(intent);
	}
	
	@Override
	public void onBackPressed() {
		mp_gameover.stop();
		Intent intent = new Intent(GameOverActivity.this, StartAcitivity.class);
		startActivity(intent);
	}
}
