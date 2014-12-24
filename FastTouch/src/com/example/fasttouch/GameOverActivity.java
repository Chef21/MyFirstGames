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

public class GameOverActivity extends Activity {
	
	private TextView textViewFinalScoreValue;
	private TextView textViewHighScoreValue;
	private String finalScore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_gameover);
		
		final MediaPlayer mp_gameover = MediaPlayer.create(getApplicationContext(),R.raw.gameover);
		mp_gameover.start();
		
		Intent intent = getIntent();
		finalScore = intent.getStringExtra("score");
		
		textViewFinalScoreValue = (TextView) findViewById(R.id.textViewFinalScoreValue);
		textViewFinalScoreValue.setText(finalScore);
		
		SharedPreferences sharedPreferences = this.getSharedPreferences("highScore", Context.MODE_PRIVATE);
		int oldScore = sharedPreferences.getInt("score", 0);
		if (Integer.parseInt(finalScore) > oldScore) {
			Editor editor = sharedPreferences.edit();
			editor.putInt("score", Integer.parseInt(finalScore));
			editor.commit();			
		}
		
		textViewHighScoreValue = (TextView) findViewById(R.id.textViewHighScoreValue);
		textViewHighScoreValue.setText(sharedPreferences.getInt("score", 0) + "");
	}
	
	public void restart(View view) {
		Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
		startActivity(intent);
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(GameOverActivity.this, StartAcitivity.class);
		startActivity(intent);
	}
}
