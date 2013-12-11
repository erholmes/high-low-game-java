package com.iti.guessinggamejava;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class GuessingGame extends Activity {

	int[] photos = { R.drawable.a0, R.drawable.a1, R.drawable.a2,
			R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6,
			R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10,
			R.drawable.a11, R.drawable.a12 };

	int numberToGuess = (int) (Math.random() * 13);
	int score = 0;
	int mistake = 0;
	ImageButton btnClosePopup;
	private PopupWindow pwindo;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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

	public void popupCorrect() {

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
		
		Context context = getApplicationContext();
		CharSequence text = "Correct!";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();



		/*LayoutInflater inflater = getLayoutInflater();

		View layout = inflater.inflate(R.layout.screen_popup,
				(ViewGroup) findViewById(R.id.popup_element));

		TextView text = (TextView) layout.findViewById(R.id.txtView1);
		text.setText("Correct !");

		pwindo = new PopupWindow(layout, 300, 250, true);
		pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

		btnClosePopup = (ImageButton) layout.findViewById(R.id.btn_close_popup);
		btnClosePopup.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pwindo.dismiss();

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
			}
		});*/
	}

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
		
		if (mistake < 3){
			Context context = getApplicationContext();
			CharSequence text = "Wrong!";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();}
	 
		else {
			Context context = getApplicationContext();
			CharSequence text = "Wrong!" + "\n" + "Final Score: " + score;
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		
			startActivity(new Intent(GuessingGame.this, GameOver.class));
		}
		
		/*LayoutInflater inflater = getLayoutInflater();

		View layout = inflater.inflate(R.layout.screen_popup,
				(ViewGroup) findViewById(R.id.popup_element));

		TextView text = (TextView) layout.findViewById(R.id.txtView2);
		text.setText("Wrong!!" + "\n" + "Final Score: " + score);

		pwindo = new PopupWindow(layout, 300, 250, true);
		pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

		btnClosePopup = (ImageButton) layout.findViewById(R.id.btn_close_popup);
		btnClosePopup.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pwindo.dismiss();

				startActivity(new Intent(GuessingGame.this, GameOver.class));
			}
		});*/
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

	public void scoreView() {
		TextView scoreView = (TextView) findViewById(R.id.scoreView);
		scoreView.setText("Score: " + score + "\nIncorrect: " + mistake);
	}
}
