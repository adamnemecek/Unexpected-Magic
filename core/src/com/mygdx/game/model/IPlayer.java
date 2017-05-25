package com.mygdx.game.model;

import com.mygdx.game.model.song.IVoice;

/**
 * 
 * @author Arvid
 *
 */
public interface IPlayer {
	
	IVoice getVoice();
	
	IScore getScore();
	
	String getName();

}
