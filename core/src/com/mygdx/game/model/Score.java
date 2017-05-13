package com.mygdx.game.model;

public class Score {

	/**
	 * A class that holds score.
	 * Modified by Arunvik
	 * Primitive, mostly made for me to test testing
	 */

	//TODO if score is only part of model should it calculate things?
	private double score;
	private int streak;
	private int multiplier; //higher score if higher streak

	public Score(){
		score = 0;
		streak = 0;
		multiplier = 1; //TODO this could perhaps work
	}
	//first draft, score calculation? Keep track of percentage of note hit?
	public void hitNote(boolean previouslyHit){
		score +=1*multiplier;
		if(!previouslyHit){
			streak++;
			multiplier = 1 + streak/50;
		}
	}


	public void missedNote(){
		streak=0;
		multiplier = 1;
	}
	public int getMultiplier(){
		return multiplier;
	}

	public int getStreak(){
		return streak;
	}
	public int getScore(){
		return (int)score;
	}

}

