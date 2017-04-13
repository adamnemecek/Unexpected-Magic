package com.mygdx.game.model;

import com.mygdx.game.model.song.Voice;

/**
 * A class that represents an in-game player.
 */

public class Player {
	String name;
	Voice voice;
	Score score;
	public Player(String name){} //TODO for testing
	public Player(String name, Voice voice, Score score){
		this.voice = voice;
		this.score = score;
	}

}
