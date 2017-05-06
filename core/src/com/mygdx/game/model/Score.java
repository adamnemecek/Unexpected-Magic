package com.mygdx.game.model;

import com.mygdx.game.gameEngine.scenes.PianoRoll;
import com.mygdx.game.model.song.Note;

public class Score {
	
	/**
	 * A class that holds score.
	 * Modified by Arunvik
	 * Primitive, mostly made for me to test testing
	 */
	
	private int score;
	private int streak;
	
	
	public Score(){
		score = 0;
		streak = 0;	
	}
	//first draft, score calculation? Keep track of percentage of note hit?
	public void hitNote(){
		score += 1;
		System.out.println("SCORE: " + score);
		//streak++;
	}


	public void missedNote(){
		streak=0;
	}
	
	public int getStreak(){
		return streak;
	}
	public int getScore(){
		return score;
	}

    }

