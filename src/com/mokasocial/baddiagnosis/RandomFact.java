package com.mokasocial.baddiagnosis;

public class RandomFact {
	public int id;
	public String fact_text;
	public int given_count;

	public RandomFact(int id, String fact_text, int given_count) {
		this.id = id;
		this.fact_text = fact_text;
		this.given_count = given_count;
	}

	public RandomFact(int id, String fact_text) {
		this.id = id;
		this.fact_text = fact_text;
	}
}