package com.mygdx.game.model;

/**
 * Interface for score to alert listeners when it is updated
 * @author rarvid
 * 
 * Uses: None
 * 
 * Used By: Hud, Score, IScore
 *
 */

public interface ScoreListener {
	
	void newScore(int score);

}
