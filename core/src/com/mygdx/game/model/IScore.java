package com.mygdx.game.model;


/**
 * An interface for one player's score.
 * @author Arvid
 * 
 * Uses: ScoreListener
 * 
 * Used by: HitManager, Hud, ScoreScreen, IPlayer, Score, Player
 *
 */
public interface IScore {
	
	void hitNote(boolean previouslyHit);
	
	void missedNote();
	
	void wrongNote();
	
	int getMultiplier();
	
	int getStreak();
	
	int getBestStreak();
	
	int getScore();
	
	void addListener(ScoreListener sl);
	
	void removeListener(ScoreListener sl);

}
