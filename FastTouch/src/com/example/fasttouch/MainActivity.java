package com.example.fasttouch;

import java.util.Random;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * This class custom the Activity on the game screen.
 * It contains actions to add Images, custom levels and
 * make the countdown.
 * 
 * Date: 21.12.2014
 * 
 * @author Sayan.Vaaheesan
 *
 */
public class MainActivity extends Activity {
	
	/**
	 * The TextView Level
	 */
	private TextView textViewLevel;
	
	/**
	 * The TextView Score
	 */
	private TextView textViewScore;
	
	/**
	 * The TextView Timer
	 */
	private TextView textViewTimer;

	/**
	 * The ImageView BirdOne
	 */
	private ImageView imageViewBirdOne;
	
	/**
	 * The ImageView BirdTwo
	 */
	private ImageView imageViewBirdTwo;
	
	/**
	 * The ImageView BirdThree
	 */
	private ImageView imageViewBirdThree;
	
	/**
	 * The level
	 */
	private int level;
	
	/**
	 * The score
	 */
	private int score;
	
	/**
	 * The Counterclass
	 */
	CounterClass timer;
	
	/**
	 * The Animation FadeIn
	 */
	Animation animationFadeIn;
	
	/**
	 * The Relativelayout
	 */
	RelativeLayout relativeLayout;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Make View Fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
		// Initialise level
		level = 1;
		textViewLevel = (TextView) findViewById(R.id.textViewLevel);
		SpannableString content = new SpannableString("Level: " + level);
		content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
		textViewLevel.setText("Level: " + level);
		
		// Initialise score
		score = 0;
		textViewScore = (TextView) findViewById(R.id.textViewScore);
		textViewScore.setText(score + "");
		
		// Initialise timer
		textViewTimer = (TextView) findViewById(R.id.textViewTimer);		

		// Animations
		animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutGame);
		
	    AnimationDrawable animationOne = new AnimationDrawable();
	    animationOne.addFrame(getResources().getDrawable(R.drawable.bird_up), 500);
	    animationOne.addFrame(getResources().getDrawable(R.drawable.bird_down), 500);
	    animationOne.setOneShot(false);
	    imageViewBirdOne = (ImageView) findViewById(R.id.imageViewGameBirdOne);
	    imageViewBirdOne.setBackgroundDrawable(animationOne);
	    
	    AnimationDrawable animationTwo = new AnimationDrawable();
	    animationTwo.addFrame(getResources().getDrawable(R.drawable.bird_down), 500);
	    animationTwo.addFrame(getResources().getDrawable(R.drawable.bird_up), 500);
	    animationTwo.setOneShot(false);
		imageViewBirdTwo = (ImageView) findViewById(R.id.imageViewGameBirdTwo);
		imageViewBirdTwo.setBackgroundDrawable(animationTwo);
		
	    AnimationDrawable animationThree = new AnimationDrawable();
	    animationThree.addFrame(getResources().getDrawable(R.drawable.bird_down), 500);
	    animationThree.addFrame(getResources().getDrawable(R.drawable.bird_up), 500);
	    animationThree.setOneShot(false);
	    imageViewBirdThree = (ImageView) findViewById(R.id.imageViewGameBirdThree);
	    imageViewBirdThree.setBackgroundDrawable(animationThree);
		
		animationOne.start();
		animationTwo.start();
		animationThree.start();
		
		// start game
		startGame();
	}
	
	/**
	 * Start the game
	 */
	private void startGame() {	
		if (level > 1) {
			// play music
			final MediaPlayer mp_nextlvl = MediaPlayer.create(this, R.raw.next_lvl);
			mp_nextlvl.start();
		}
		
		// Add Images
		addImages();
		
		// Start the Countdown
		startTimer();
	}
	
	/**
	 * Start the timer
	 */
	private void startTimer() {
		timer = new CounterClass(6000, 1000);
		timer.start();
	}
	
	
	
	/**
	 * Add images
	 */
	@SuppressLint("NewApi")
	private void addImages() {			
		for (int i=0; i<level; i++) {
			// Add image
			final ImageView imageView = new ImageView(this);
			imageView.setImageResource(R.drawable.game_face);
			
			imageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					relativeLayout.removeViewInLayout(imageView);
					
					// count score
					score++;
					textViewScore.setText(score + "");
					
					// check if all images are clicked
					if (relativeLayout.getChildCount() == 6) {				
						level++;
						textViewLevel.setText("Level: " + level);
						
						timer.cancel();
						
						// next level
						startGame();
					}
				}
			});
			
			// Display Size
			Random random = new Random();
			float x = random.nextInt(720);
			float y = random.nextInt(1280);
			
			// Set image position
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			imageView.setLayoutParams(layoutParams);
			imageView.setX(x);
			imageView.setY(y);
			
			RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
			lp.topMargin = 300;
			relativeLayout.addView(imageView);
			
			if (level > 1) {
				relativeLayout.startAnimation(animationFadeIn);
			}
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
			if (seconds == 1) {
				
			}
			
			textViewTimer.setText((millisUntilFinished / 1000) + "s");
		}
	}
	
	@Override
	public void onBackPressed() {
		timer.cancel();
		Intent intent = new Intent(MainActivity.this, StartAcitivity.class);
		startActivity(intent);
	}
}
