package com.mygdx.game.model;


/**
 * An interface for one player's score.
 * @author Arvid
 */
public interface IScore {
	
	void hitNote(boolean previouslyHit);
	
	void missedNote();
	
	void wrongNote();
	
	int getMultiplier();
	
	int getStreak();
	
	int getScore();
	
	void addListener(ScoreListener sl);
	
	void removeListener(ScoreListener sl);

}
