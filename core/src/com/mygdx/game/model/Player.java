package com.mygdx.game.model;

import com.mygdx.game.model.song.IVoice;

/**
 * A class that represents an in-game player.
 * @author soflarb
 * Revised by rarvid
 * 
 * Uses: IVoice, IPlayer, Score, IScore
 * 
 * Used by: None
 */

public class Player implements IPlayer{
	private final String name;
	private final IVoice voice;
	private final Score score;
	public Player(String name, IVoice voice) {
		this(name, voice, new Score());
	}
	public Player(String name, IVoice voice, Score score){
		this.name = name;
		this.voice = voice;
		this.score = score;
	}
	
	public IVoice getVoice(){
		return voice;
	}
	public IScore getScore(){
		return score;
	}
	public String getName(){
		return name;
	}

}
