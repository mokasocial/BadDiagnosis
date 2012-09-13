package com.mokasocial.baddiagnosis;

public class Answer {
	public int question_id;
	public int diagnosis_id;
	public int answer_value;
	static String YES = "Y";
	static String NO = "N";

	public Answer(int diagnosis_id, int question_id, String answer_value) {
		this.diagnosis_id = diagnosis_id;
		this.question_id = question_id;
		this.answer_value = (answer_value == YES ? 1 : 0);
	}
}