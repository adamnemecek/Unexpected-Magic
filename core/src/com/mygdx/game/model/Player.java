package com.mygdx.game.model;

import com.mygdx.game.model.song.IVoice;

/**
 * A class that represents an in-game player.
 * @author soflarb
 */

public class Player {
	public final String name;
	public final IVoice voice;
	public final Score score;
	//public Player(String name){} //TODO for testing
	public Player(String name, IVoice voice) {
		this(name, voice, new Score());
	}
	public Player(String name, IVoice voice, Score score){
		this.name = name;
		this.voice = voice;
		this.score = score;
	}
}
