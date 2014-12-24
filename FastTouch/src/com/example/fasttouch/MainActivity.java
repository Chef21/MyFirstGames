package com.example.fasttouch;

import java.util.Random;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TextView textViewLevel;
	private TextView textViewScore;
	private TextView textViewTimer;
	
	private int level;
	private int score;
	
	CounterClass timer;
	
	Animation animationFadeIn;
	
	RelativeLayout relativeLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
		level = 1;
		textViewLevel = (TextView) findViewById(R.id.textViewLevel);
		textViewLevel.setText("Level: " + level);
		
		score = 0;
		textViewScore = (TextView) findViewById(R.id.textViewScore);
		textViewScore.setText(score + "");
		
		textViewTimer = (TextView) findViewById(R.id.textViewTimer);		

		animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		
		relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutGame);
		
		startGame();
	}
	
	/**
	 * Start the game
	 */
	private void startGame() {	
		if (level > 1) {
			final MediaPlayer mp_nextlvl = MediaPlayer.create(this, R.raw.next_lvl);
			mp_nextlvl.start();
		}
		addImages();
		startTimer();
	}
	
	/**
	 * Start the timer
	 */
	private void startTimer() {
		timer = new CounterClass(5000, 1000);
		timer.start();
	}
	
	
	
	/**
	 * Add images
	 */
	@SuppressLint("NewApi")
	private void addImages() {			
		for (int i=0; i<level; i++) {
			final ImageView imageView = new ImageView(this);
			imageView.setImageResource(R.drawable.game_face);
			
			imageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					relativeLayout.removeViewInLayout(imageView);
					score++;
					textViewScore.setText(score + "");
					if (relativeLayout.getChildCount() == 3) {				
						level++;
						textViewLevel.setText("Level: " + level);
						
						timer.cancel();
						startGame();
					}
				}
			});
			Random random = new Random();
			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			float x = random.nextInt(750);
			float y = random.nextInt(1200);
			
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			imageView.setLayoutParams(layoutParams);
			imageView.setX(x);
			imageView.setY(y);
			
			RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
			lp.topMargin = 300;
			
			relativeLayout.addView(imageView);
			relativeLayout.startAnimation(animationFadeIn);
		}
	}


	/**
	 * Timer class
	 * This class controlls the timer
	 * @author sayan.vaaheesan
	 * 
	 */
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	public class CounterClass extends CountDownTimer {
		public CounterClass(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			textViewTimer.setText("0s");
			Intent intent = new Intent(MainActivity.this, GameOverActivity.class);
			intent.putExtra("score", score + "");			
			startActivity(intent);
		}

		@SuppressLint({ "NewApi", "DefaultLocale", "ResourceAsColor" })
		@TargetApi(Build.VERSION_CODES.GINGERBREAD)
		@Override
		public void onTick(long millisUntilFinished) {
			// Timer in Seconds
			long millis = millisUntilFinished;
			int seconds = (int) (millis / 1000);
			textViewTimer.setText(String.format("%2d", seconds) + "s");
		}
	}
}
