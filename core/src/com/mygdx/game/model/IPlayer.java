package com.mygdx.game.model;

import com.mygdx.game.model.song.IVoice;

/**
 * An interface for a player.
 * @author rarvid
 * 
 * Uses: IScore, IVoice
 * 
 * Used by: EntityFactory, HitManager, Hud, PianoRoll, GameScreen, ScoreScreen, Round, Player
 */
public interface IPlayer {
	
	IVoice getVoice();
	
	IScore getScore();
	
	String getName();

}
