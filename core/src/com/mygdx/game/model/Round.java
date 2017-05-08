package com.mygdx.game.model;

import java.util.ArrayList;

import com.mygdx.game.model.song.ISong;
/**
 * A class that represents a game round.
 */
public class Round {
	public final ISong song;
	public final ArrayList<Player> players;

	
	public Round(ISong song, ArrayList<Player> players){
		this.song = song;
		this.players = players;
		
	}
}

