package com.mygdx.game.model;

import com.mygdx.game.model.song.Voice;

/**
 * A class that represents an in-game player.
 */

public class Player {
	public final String name;
	public final Voice voice;
	public final Score score;
	//public Player(String name){} //TODO for testing
	public Player(String name, Voice voice, Score score){
		this.name = name;
		this.voice = voice;
		this.score = score;
	}
}
