package com.mygdx.game.model;

import java.util.List;

import com.mygdx.game.model.song.ISong;
/**
 * A class that represents a game round.
 * @author soflarb
 */
public class Round {
	public final ISong song;
	public final List<Player> players;

	
	public Round(ISong song, List<Player> players){
		this.song = song;
		this.players = players;
		
	}
}

