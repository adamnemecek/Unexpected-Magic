package com.mygdx.game.model;

import com.mygdx.game.gameEngine.scenes.PianoRoll;
import com.mygdx.game.model.song.Note;

public class Score {
	
	/**
	 * A class that holds score.
	 * Modified by Arunvik
	 * Primitive, mostly made for me to test testing
	 */

	//TODO if score is only part of model should it calculate things??
	
	private int score;
	private int streak;
	private int multiplier; //higher score if higher streak
	
	public Score(){
		score = 0;
		streak = 0;
		multiplier = Math.round(((float)(score)/10)); //TODO this could perhaps work
	}
	//first draft, score calculation? Keep track of percentage of note hit?
	public void hitNote(){
		score += 1;
		streak++;
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

