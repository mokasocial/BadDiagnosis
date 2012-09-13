package com.mokasocial.baddiagnosis;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database {

	/** GLOBAL DATABASE DEFINITIONS */
	private static final String DATABASE_NAME = "baddiagnosis.db";
	private static final int DATABASE_VERSION = 1;

	/** TABLE DEFINITIONS */
	private static final String TABLE_DIAGNOSES = "diagnoses";
	private static final String TABLE_QUESTIONS = "questions";
	private static final String TABLE_ANSWERS = "answers";
	private static final String TABLE_RANDOMFACTS = "facts";
	private static final String TABLE_CURRENT_QUESTIONS = "current_questions";

	/** COLUMN DEFINITIONS */
	public static final String COLUMN_DIAGNOSIS_ID = "diagnosis_id";
	public static final String COLUMN_DIAGNOSIS_TEXT = "diagnosis_text";
	public static final String COLUMN_DIAGNOSIS_COUNT = "diagnosis_count";
	public static final String COLUMN_QUESTION_ID = "question_id";
	public static final String COLUMN_QUESTION_TEXT = "question_text";
	public static final String COLUMN_QUESTION_COUNT = "question_count";
	public static final String COLUMN_ANSWER_VALUE = "answer_value";
	public static final String COLUMN_RANDOMFACT_COUNT = "fact_count";
	public static final String COLUMN_RANDOMFACT_TEXT = "fact_text";
	public static final String COLUMN_RANDOMFACT_ID = "fact_id";

	private final DatabaseHelper mOpenHelper;

	public Database(Context context) {
		mOpenHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_DIAGNOSES + " (" + COLUMN_DIAGNOSIS_ID + " INT PRIMARY KEY, " + COLUMN_DIAGNOSIS_TEXT + " TEXT, " + COLUMN_DIAGNOSIS_COUNT + " INT);");
			db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_QUESTIONS + " (" + COLUMN_QUESTION_ID + " INT PRIMARY KEY, " + COLUMN_QUESTION_TEXT + " TEXT, " + COLUMN_QUESTION_COUNT + " INT);");
			db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_RANDOMFACTS + " (" + COLUMN_RANDOMFACT_ID + " INT PRIMARY KEY, " + COLUMN_RANDOMFACT_TEXT + " TEXT, " + COLUMN_RANDOMFACT_COUNT + " INT);");
			db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_ANSWERS + " (" + COLUMN_QUESTION_ID + " INT, " + COLUMN_DIAGNOSIS_ID + " INT, " + COLUMN_ANSWER_VALUE + " INT);");
			db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CURRENT_QUESTIONS + " (" + COLUMN_QUESTION_ID + " INT, " + COLUMN_ANSWER_VALUE + " INT);");

			Data data = new Data();

			for (Answer answer : data.answers) {
				db.execSQL("INSERT INTO " + TABLE_ANSWERS + " (" + COLUMN_QUESTION_ID + ", " + COLUMN_DIAGNOSIS_ID + ", " + COLUMN_ANSWER_VALUE + ") VALUES (" + answer.question_id + ", " + answer.question_id + ", " + answer.answer_value + ");");
			}

			for (Question question : data.questions) {
				db.execSQL("INSERT INTO  " + TABLE_QUESTIONS + " (" + COLUMN_QUESTION_ID + ", " + COLUMN_QUESTION_TEXT + ", " + COLUMN_QUESTION_COUNT + ") VALUES (" + question.id + ", " + DatabaseUtils.sqlEscapeString(question.question_text + "") + ", 0);");
			}

			for (Diagnosis diagnosis : data.diagnoses) {
				db.execSQL("INSERT INTO  " + TABLE_DIAGNOSES + " (" + COLUMN_DIAGNOSIS_ID + ", " + COLUMN_DIAGNOSIS_TEXT + ", " + COLUMN_DIAGNOSIS_COUNT + ") VALUES (" + diagnosis.id + ", " + DatabaseUtils.sqlEscapeString(diagnosis.diagnosis_text + "") + ", 0);");
			}

			for (RandomFact fact : data.facts) {
				db.execSQL("INSERT INTO  " + TABLE_RANDOMFACTS + " (" + COLUMN_RANDOMFACT_ID + ", " + COLUMN_RANDOMFACT_TEXT + ", " + COLUMN_RANDOMFACT_COUNT + ") VALUES (" + fact.id + ", " + DatabaseUtils.sqlEscapeString(fact.fact_text + "") + ", 0);");
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w("Database", "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIAGNOSES);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSWERS);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_RANDOMFACTS);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURRENT_QUESTIONS);
			onCreate(db);
		}
	}

	public ArrayList<Answer> fetchAllAnswers(Context context) throws NotFoundException {

		SQLiteDatabase db = mOpenHelper.getReadableDatabase();

		Cursor cursor = db.query(TABLE_ANSWERS, new String[] { COLUMN_QUESTION_ID, COLUMN_DIAGNOSIS_ID, COLUMN_ANSWER_VALUE }, null, null, null, null, COLUMN_DIAGNOSIS_ID + " asc");

		ArrayList<Answer> answers = new ArrayList<Answer>();

		if (cursor.moveToFirst()) {
			do {
				int diagnosis_id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DIAGNOSIS_ID));
				int question_id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUESTION_ID));
				int answer_value = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ANSWER_VALUE));
				String answer_string = (answer_value == 1) ? Answer.YES : Answer.NO;
				Answer answer = new Answer(diagnosis_id, question_id, answer_string);
				answers.add(answer);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}

		db.close();

		return answers;
	}

	public ArrayList<Diagnosis> fetchAllDiagnoses(Context context) throws NotFoundException {

		SQLiteDatabase db = mOpenHelper.getReadableDatabase();

		Cursor cursor = db.query(TABLE_DIAGNOSES, new String[] { COLUMN_DIAGNOSIS_ID, COLUMN_DIAGNOSIS_TEXT, COLUMN_DIAGNOSIS_COUNT }, null, null, null, null, COLUMN_DIAGNOSIS_ID + " asc");

		ArrayList<Diagnosis> diagnoses = new ArrayList<Diagnosis>();

		if (cursor.moveToFirst()) {
			do {
				int diagnosis_id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DIAGNOSIS_ID));
				String diagnosis_text = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIAGNOSIS_TEXT));
				int diagnosis_count = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DIAGNOSIS_COUNT));
				Diagnosis diagnosis = new Diagnosis(diagnosis_id, diagnosis_text, diagnosis_count);
				diagnoses.add(diagnosis);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}

		db.close();

		return diagnoses;
	}

	public ArrayList<Question> fetchAllQuestions(Context context) throws NotFoundException {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();

		Cursor cursor = db.query(TABLE_QUESTIONS, new String[] { COLUMN_QUESTION_ID, COLUMN_QUESTION_TEXT, COLUMN_QUESTION_COUNT }, null, null, null, null, COLUMN_QUESTION_ID + " asc");

		ArrayList<Question> questions = new ArrayList<Question>();

		if (cursor.moveToFirst()) {
			do {
				int question_id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUESTION_ID));
				String question_text = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUESTION_TEXT));
				int question_count = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUESTION_COUNT));
				Question question = new Question(question_id, question_text, question_count);
				questions.add(question);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}

		db.close();

		return questions;
	}

	public ArrayList<RandomFact> fetchAllRandomFacts(Context context) throws NotFoundException {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();

		Cursor cursor = db.query(TABLE_RANDOMFACTS, new String[] { COLUMN_RANDOMFACT_ID, COLUMN_RANDOMFACT_TEXT, COLUMN_RANDOMFACT_COUNT }, null, null, null, null, COLUMN_RANDOMFACT_ID + " asc");

		ArrayList<RandomFact> facts = new ArrayList<RandomFact>();

		if (cursor.moveToFirst()) {
			do {
				int fact_id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RANDOMFACT_ID));
				String fact_text = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RANDOMFACT_TEXT));
				int fact_count = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RANDOMFACT_COUNT));
				RandomFact fact = new RandomFact(fact_id, fact_text, fact_count);
				facts.add(fact);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}

		db.close();

		return facts;
	}

	public ArrayList<Integer[]> fetchCurrentQuestions(Context context) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CURRENT_QUESTIONS, new String[] { COLUMN_QUESTION_ID, COLUMN_ANSWER_VALUE }, null, null, null, null, COLUMN_QUESTION_ID + " asc");

		ArrayList<Integer[]> current_questions = new ArrayList<Integer[]>();

		if (cursor.moveToFirst()) {
			do {
				Integer question_id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUESTION_ID));
				Integer answer_id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ANSWER_VALUE));
				Integer[] intArray = new Integer[] { question_id, answer_id };
				current_questions.add(intArray);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}

		db.close();

		return current_questions;
	}

	public int saveAnswer(Context context, Answer answer) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();

		final ContentValues values = new ContentValues();

		values.put(COLUMN_ANSWER_VALUE, answer.answer_value);
		values.put(COLUMN_QUESTION_ID, answer.question_id);
		values.put(COLUMN_DIAGNOSIS_ID, answer.diagnosis_id);

		int rowId = (int) db.replace(TABLE_ANSWERS, null, values);

		db.close();

		return rowId;
	}

	public int saveDiagnosis(Context context, Diagnosis diagnosis) {

		SQLiteDatabase db = mOpenHelper.getWritableDatabase();

		final ContentValues values = new ContentValues();

		values.put(COLUMN_DIAGNOSIS_ID, diagnosis.id);
		values.put(COLUMN_DIAGNOSIS_TEXT, diagnosis.diagnosis_text + "");
		values.put(COLUMN_DIAGNOSIS_COUNT, diagnosis.given_count);

		int rowId = (int) db.replace(TABLE_DIAGNOSES, null, values);

		db.close();

		return rowId;
	}

	public int saveQuestion(Context context, Question question) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();

		final ContentValues values = new ContentValues();

		values.put(COLUMN_QUESTION_ID, question.id);
		values.put(COLUMN_QUESTION_TEXT, question.question_text + "");
		values.put(COLUMN_QUESTION_COUNT, question.asked_count);

		int rowId = (int) db.replace(TABLE_QUESTIONS, null, values);

		db.close();

		return rowId;
	}

	public int saveRandomFact(Context context, RandomFact fact) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();

		final ContentValues values = new ContentValues();

		values.put(COLUMN_RANDOMFACT_ID, fact.id);
		values.put(COLUMN_RANDOMFACT_TEXT, fact.fact_text + "");
		values.put(COLUMN_RANDOMFACT_COUNT, fact.given_count);

		int rowId = (int) db.replace(TABLE_RANDOMFACTS, null, values);

		db.close();

		return rowId;
	}

	public int saveCurrentQuestion(Integer[] integers) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();

		final ContentValues values = new ContentValues();

		values.put(COLUMN_QUESTION_ID, integers[0]);
		values.put(COLUMN_ANSWER_VALUE, integers[1]);

		int rowId = (int) db.insert(TABLE_CURRENT_QUESTIONS, null, values);

		db.close();

		return rowId;
	}

	public void flushCurrentQuestions() {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();

		db.delete(TABLE_CURRENT_QUESTIONS, null, null);

		db.close();
	}

	public Question fetchNewQuestion(Context mContext) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();

		Cursor cursor = db.query(TABLE_QUESTIONS, new String[] { COLUMN_QUESTION_ID, COLUMN_QUESTION_TEXT, COLUMN_QUESTION_COUNT }, null, null, null, null, COLUMN_QUESTION_COUNT + " ASC, RANDOM()");

		cursor.moveToFirst();
		int question_id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUESTION_ID));
		String question_text = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUESTION_TEXT));
		int question_count = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUESTION_COUNT));
		Question question = new Question(question_id, question_text, question_count);

		db.close();

		return question;
	}

	public RandomFact fetchNewRandomFact(Context mContext) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();

		Cursor cursor = db.query(TABLE_RANDOMFACTS, new String[] { COLUMN_RANDOMFACT_ID, COLUMN_RANDOMFACT_TEXT, COLUMN_RANDOMFACT_COUNT }, null, null, null, null, COLUMN_RANDOMFACT_COUNT + " asc");

		cursor.moveToFirst();
		int fact_id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RANDOMFACT_ID));
		String fact_text = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RANDOMFACT_TEXT));
		int fact_count = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RANDOMFACT_COUNT));
		RandomFact fact = new RandomFact(fact_id, fact_text, fact_count);

		db.close();

		return fact;
	}

}