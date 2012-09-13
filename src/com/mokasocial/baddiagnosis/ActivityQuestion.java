package com.mokasocial.baddiagnosis;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class ActivityQuestion extends Activity {

	static int TOTAL_QUESTIONS = 3;

	public Context mContext;
	public Question mQuestion; // the one we've chosen
	public RandomFact mRandomFact;
	public int mQuestionNumber;
	public Database mDatabase;
	public GoogleAnalyticsTracker tracker;

	public ArrayList<Integer[]> mQuestionsAsked;

	/** Called with the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		tracker = GoogleAnalyticsTracker.getInstance();

		// Start the tracker in manual dispatch mode...
		tracker.start("UA-12331601-9", this);
		tracker.setCustomVar(1, "Navigation Type", "Button click", 2);

		mContext = this;
		mDatabase = new Database(mContext);
		mQuestionsAsked = mDatabase.fetchCurrentQuestions(mContext);
		mQuestionNumber = mQuestionsAsked.size() + 1;

		Database database = new Database(mContext);
		mQuestion = database.fetchNewQuestion(mContext);
		mRandomFact = database.fetchNewRandomFact(mContext);

		// Inflate our UI from its XML layout description.
		setContentView(R.layout.question);

		initProgressDots();

		// header
		TextView questionHeader = (TextView) findViewById(R.id.question_header);
		questionHeader.setText("Question " + String.valueOf(mQuestionNumber) + " of " + String.valueOf(TOTAL_QUESTIONS));

		// set ? text
		TextView questionText = (TextView) findViewById(R.id.question_text);
		questionText.setText(Html.fromHtml(mQuestion.question_text));

		// set random fact text
		TextView randomFactText = (TextView) findViewById(R.id.random_fact);
		randomFactText.setText(Html.fromHtml(mRandomFact.fact_text));

		initAnswerButtons();

		mQuestion.asked_count++;
		mDatabase.saveQuestion(mContext, mQuestion);

		mRandomFact.given_count++;
		mDatabase.saveRandomFact(mContext, mRandomFact);
	}

	private void initProgressDots() {
		// all these are initially set to 'future'
		ImageView q1 = (ImageView) findViewById(R.id.progress_question_1);
		ImageView q2 = (ImageView) findViewById(R.id.progress_question_2);
		ImageView q3 = (ImageView) findViewById(R.id.progress_question_3);
		// ImageView q4 = (ImageView) findViewById(R.id.progress_question_4);
		// ImageView q5 = (ImageView) findViewById(R.id.progress_question_5);
		switch (mQuestionNumber) {
		case 1:
			q1.setImageResource(R.drawable.progress_present);
			break;
		case 2:
			q1.setImageResource(R.drawable.progress_past);
			q2.setImageResource(R.drawable.progress_present);
			break;
		case 3:
			q1.setImageResource(R.drawable.progress_past);
			q2.setImageResource(R.drawable.progress_past);
			q3.setImageResource(R.drawable.progress_present);
			break;
		case 4:
			q1.setImageResource(R.drawable.progress_past);
			q2.setImageResource(R.drawable.progress_past);
			q3.setImageResource(R.drawable.progress_past);
			// q4.setImageResource(R.drawable.progress_present);
			break;
		case 5:
			q1.setImageResource(R.drawable.progress_past);
			q2.setImageResource(R.drawable.progress_past);
			q3.setImageResource(R.drawable.progress_past);
			// q4.setImageResource(R.drawable.progress_past);
			// q5.setImageResource(R.drawable.progress_present);
			break;
		default:
			break;
		}
	}

	private void initAnswerButtons() {
		Button yesButton = (Button) findViewById(R.id.answer_yes);
		yesButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				processAnswer(Answer.YES);
			}
		});

		Button noButton = (Button) findViewById(R.id.answer_no);
		noButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				processAnswer(Answer.NO);
			}
		});
	}

	protected void processAnswer(String answer) {

		// this is official now, so load it up
		if (answer == Answer.YES) {
			tracker.trackPageView("/AnsweredYes");
			mDatabase.saveCurrentQuestion(new Integer[] { mQuestion.id, Integer.valueOf(1) });
		} else {
			tracker.trackPageView("/AnsweredNo");
			mDatabase.saveCurrentQuestion(new Integer[] { mQuestion.id, Integer.valueOf(0) });
		}

		// we done here yet?
		final Intent intent;
		if (mQuestionNumber >= TOTAL_QUESTIONS) {
			intent = new Intent(mContext, ActivityDiagnosis.class);
		} else {
			intent = new Intent(mContext, ActivityQuestion.class);
		}

		tracker.dispatch();

		startActivity(intent);
		finish();
	}

	/**
	 * Called when the activity is about to start interacting with the user.
	 */
	@Override
	protected void onResume() {
		super.onResume();
	}

	/**
	 * Called when your activity's options menu needs to be created.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		return true;
	}
}