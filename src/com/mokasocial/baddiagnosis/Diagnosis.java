package com.mokasocial.baddiagnosis;

public class Diagnosis {
	public int id;
	public String diagnosis_text;
	public int given_count;

	public Diagnosis(int id, String diagnosis_text, int given_count) {
		this.id = id;
		this.diagnosis_text = diagnosis_text;
		this.given_count = given_count;
	}

	// we can make one with no count, also
	public Diagnosis(int id, String diagnosis_text) {
		this.id = id;
		this.diagnosis_text = diagnosis_text;
		this.given_count = 0;
	}
}
