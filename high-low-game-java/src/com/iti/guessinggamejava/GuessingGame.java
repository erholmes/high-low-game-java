package com.iti.guessinggamejava;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class GuessingGame extends Activity {

	//this sets the possible card values
	int[] photos = { R.drawable.a0, R.drawable.a1, R.drawable.a2,
			R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6,
			R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10,
			R.drawable.a11, R.drawable.a12 };

	//initializes the number to guess, score and number of mistakes
	int numberToGuess = (int) (Math.random() * 13);
	int score = 0;
	int mistake = 0;
	ImageButton btnClosePopup;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		//set up the original game scree
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guess);

		ImageView randomFrontView = (ImageView) findViewById(R.id.randomFrontView2);
		randomFrontView.setImageResource(R.drawable.front);
		ObjectAnimator anim = ObjectAnimator.ofFloat(randomFrontView,
				"translationY", -500, 0);
		anim.setDuration(400);
		anim.start();

		ImageView randomView = (ImageView) findViewById(R.id.randomView);
		randomView.setImageResource(photos[numberToGuess]);
		ObjectAnimator anim1 = ObjectAnimator.ofFloat(randomView,
				"translationX", 0, -440);
		anim1.setDuration(400);
		anim1.start();

		scoreView();

		Button high = (Button) findViewById(R.id.high);
		Button low = (Button) findViewById(R.id.low);

		//if users clicks high button, check if next card is higher or lower, increment correct/incorrect count,
		//show correct/incorrect message and update score
		high.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v){ 
				int newNumberToGuess = (int) (Math.random() * 13);

				if (newNumberToGuess >= numberToGuess) {

					score++;

					popupCorrect();

					hide();

					numberToGuess = newNumberToGuess;

					show();

					scoreView();

				} else {
					
					mistake ++;
					
					popupWrong(mistake, score);

					hide();

					numberToGuess = newNumberToGuess;

					show();

					scoreView();
				}

			}

		});

		//if users clicks low button, check if next card is higher or lower, increment correct/incorrect count,
		//show correct/incorrect message and update score
		low.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				int newNumberToGuess = (int) (Math.random() * 13);

				if (newNumberToGuess <= numberToGuess) {

					score++;

					popupCorrect();

					hide();

					numberToGuess = newNumberToGuess;

					show();

					scoreView();

				} else {

					mistake ++;
					
					popupWrong(mistake, score);

					hide();

					numberToGuess = newNumberToGuess;

					show();
					
					scoreView();
				}
			}
		});
	}

	//update card views and display correct message
	public void popupCorrect() {

		//change card view
		LayoutInflater inflater = getLayoutInflater();

		View layout = inflater.inflate(R.layout.screen_popup,
				(ViewGroup) findViewById(R.id.popup_element));
		
		ImageView randomFrontView = (ImageView) findViewById(R.id.randomFrontView2);
		randomFrontView.setImageResource(R.drawable.front);
		randomFrontView.setVisibility(View.VISIBLE);
		ObjectAnimator anim = ObjectAnimator.ofFloat(randomFrontView,
				"translationY", -500, 0);
		anim.setDuration(400);
		anim.start();

		ImageView randomView2 = (ImageView) findViewById(R.id.randomView2);
		randomView2.setImageResource(photos[numberToGuess]);
		randomView2.setVisibility(View.INVISIBLE);

		ImageView randomView = (ImageView) findViewById(R.id.randomView);
		randomView.setImageResource(photos[numberToGuess]);
		ObjectAnimator anim1 = ObjectAnimator.ofFloat(randomView,
				"translationX", 0, -440);
		anim1.setDuration(400);
		anim1.start();
		
		
		//display correct message
		Context context = getApplicationContext();
		CharSequence text = "Correct!";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	//update card views and display incorrect message
	public void popupWrong(int mistake, int score) {
		
		ImageView randomFrontView = (ImageView) findViewById(R.id.randomFrontView2);
		randomFrontView.setImageResource(R.drawable.front);
		randomFrontView.setVisibility(View.VISIBLE);
		ObjectAnimator anim = ObjectAnimator.ofFloat(randomFrontView,
				"translationY", -500, 0);
		anim.setDuration(400);
		anim.start();

		ImageView randomView2 = (ImageView) findViewById(R.id.randomView2);
		randomView2.setImageResource(photos[numberToGuess]);
		randomView2.setVisibility(View.INVISIBLE);

		ImageView randomView = (ImageView) findViewById(R.id.randomView);
		randomView.setImageResource(photos[numberToGuess]);
		ObjectAnimator anim1 = ObjectAnimator.ofFloat(randomView,
				"translationX", 0, -440);
		anim1.setDuration(400);
		anim1.start();
		
		//if user has not yet made 3 mistakes, display error message and continue game
		if (mistake < 3){
			Context context = getApplicationContext();
			CharSequence text = "Wrong!";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();}
	    //if user has made 3 mistakes, display error message and end game
		else {
			Context context = getApplicationContext();
			CharSequence text = "Wrong!" + "\n" + "Final Score: " + score;
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		
			startActivity(new Intent(GuessingGame.this, GameOver.class));
		}
	}

	
	public void hide() {
		ImageView randomFrontView = (ImageView) findViewById(R.id.randomFrontView2);
		randomFrontView.setImageResource(R.drawable.front);
		randomFrontView.setVisibility(View.INVISIBLE);
	}

	public void show() {
		ImageView randomView2 = (ImageView) findViewById(R.id.randomView2);
		randomView2.setImageResource(photos[numberToGuess]);
		randomView2.setVisibility(View.VISIBLE);
	}

	//display scores and number of incorrect guesses so far
	public void scoreView() {
		TextView scoreView = (TextView) findViewById(R.id.scoreView);
		scoreView.setText("Score: " + score + "\nIncorrect: " + mistake);
	}
}
