package com.mygdx.game.model;

import java.util.ArrayList;

import com.mygdx.game.model.song.Song;
/**
 * A class that represents a game round.
 */
public class Round {
	public final Song song;
	public final ArrayList<Player> players;
	private Ticker ticker;
	
	//public Round(){}//TODO for testing only.
	
	public Round(Song song, ArrayList<Player> players){
		this.song = song;
		this.players = players;
		ticker = new Ticker(song);
	}
	public void updateTick(float delta){
		ticker.updateTick(delta);
	}
	public int getTick(){
		return ticker.getTick();
	}
}

