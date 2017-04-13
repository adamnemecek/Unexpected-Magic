package com.mygdx.game.model;

import java.util.ArrayList;

import com.mygdx.game.model.song.Song;
/**
 * A class that represents a game round.
 */
public class Round {
	Song song;
	ArrayList<Player> players;
	
	public Round(Song song, ArrayList<Player> players){
		this.song = song;
		this.players = players;
	}
}
