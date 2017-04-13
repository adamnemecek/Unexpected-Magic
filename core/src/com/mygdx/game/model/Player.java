package com.mygdx.game.model;

import com.mygdx.game.model.song.Voice;

/**
 * A class that represents an in-game player.
 */

public class Player {
	Voice voice;
	Score score;
	public Player(Voice voice, Score score){
		this.voice = voice;
		this.score = score;
	}

}
