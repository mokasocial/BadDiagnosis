package com.mokasocial.baddiagnosis;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class ActivityDiagnosis extends Activity {

	public Context mContext;
	public Diagnosis mDiagnosis;
	public Database mDatabase;
	public GoogleAnalyticsTracker tracker;

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

		Database database = new Database(mContext);

		ArrayList<Diagnosis> allDiagnoses = database.fetchAllDiagnoses(mContext);
		ArrayList<Answer> answers = mDatabase.fetchAllAnswers(mContext);
		ArrayList<Integer[]> currentQuestions = mDatabase.fetchCurrentQuestions(mContext);

		ArrayList<Integer> listOfNotDiagnoses = new ArrayList<Integer>();
		ArrayList<Integer> listOfGoodDiagnoses = new ArrayList<Integer>();

		Random prng = new Random();

		if (mDiagnosis == null) {

			// look for those that match up well
			// @todo: do this more efficiently!
			for (Integer[] questionWasAsked : currentQuestions) { // each
																	// question
																	// that was
																	// asked
				for (Answer answer : answers) { // all possible qa combinations
					if (questionWasAsked[0].equals(answer.question_id) && !questionWasAsked[1].equals(answer.answer_value)) {
						// this was a bad answer, so fucking get rid of the
						// possibility for this diagnosis
						listOfNotDiagnoses.add(answer.answer_value);
					} else if (questionWasAsked[0].equals(answer.question_id) && !questionWasAsked[1].equals(answer.answer_value)) {
						// this was a good answer!
						listOfGoodDiagnoses.add(answer.answer_value);
					}
				}
			}

			if (listOfGoodDiagnoses.size() > 0) {
				for (Diagnosis possible_diagnosis : allDiagnoses) {
					if (listOfGoodDiagnoses.contains(new Integer(possible_diagnosis.id))) {
						if (possible_diagnosis.given_count == 0) {
							// fresh! do it!
							mDiagnosis = possible_diagnosis;
						} else {
							// meh not fresh
						}
					}
				}
			}
			if (mDiagnosis == null) {
				// oh well, just pick one
				mDiagnosis = allDiagnoses.get(prng.nextInt(allDiagnoses.size()));
			}

			while (listOfNotDiagnoses.contains(new Integer(mDiagnosis.id))) {
				// keep looking until we at least find one not on this list
				mDiagnosis = allDiagnoses.get(prng.nextInt(allDiagnoses.size()));
			}
		}
		// Inflate our UI from its XML layout description.
		setContentView(R.layout.diagnosis);

		Button restartButton = (Button) findViewById(R.id.restart);
		restartButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				final Intent intent = new Intent(mContext, ActivityQuestion.class);
				startActivity(intent);
				finish();
			}
		});

		Button shareButton = (Button) findViewById(R.id.share);
		shareButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				tracker.trackPageView("/Sharebutton");
				Intent share = new Intent(Intent.ACTION_SEND);
				share.setType("text/plain");
				share.putExtra(Intent.EXTRA_TEXT, "My diagnosis: " + mDiagnosis.diagnosis_text.replaceAll("<(.|\n)*?>","") + " http://bit.ly/hIs4Ed");
				share.putExtra(Intent.EXTRA_SUBJECT, "My Bad Diagnosis");
				startActivity(Intent.createChooser(share, "Share the bad news"));
			}
		});
		TextView diagnosisText = (TextView) findViewById(R.id.diagnosis);
		diagnosisText.setText(Html.fromHtml(mDiagnosis.diagnosis_text));

		// ding
		mDiagnosis.given_count++;

		tracker.trackPageView("/GaveDiagnosis");
		tracker.dispatch();

		// save diagnosis
		database.saveDiagnosis(mContext, mDiagnosis);

		mDatabase.flushCurrentQuestions();
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
