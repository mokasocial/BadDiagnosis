package com.mokasocial.baddiagnosis;

public class Question {
	public int id;
	public String question_text;
	public int asked_count;

	public Question(int id, String question_text, int asked_count) {
		this.id = id;
		this.question_text = question_text;
		this.asked_count = asked_count;
	}

	public Question(int id, String question_text) {
		this.id = id;
		this.question_text = question_text;
	}
}