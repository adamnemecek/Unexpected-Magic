package com.mygdx.game.model;

import com.mygdx.game.model.song.IVoice;

/**
 * An interface for a player.
 * @author rarvid
 */
public interface IPlayer {
	
	IVoice getVoice();
	
	IScore getScore();
	
	String getName();

}
