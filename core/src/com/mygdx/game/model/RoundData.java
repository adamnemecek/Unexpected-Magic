package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.model.song.ISong;

/**
 * A class that represents a game round.
 * @author soflarb
 * 
 * Uses: ISong, IPlayer
 * 
 * Used by: RoundManager, GameScreen, RoundTest
 */
public class RoundData {
	public final ISong song;
	private final List<IPlayer> players;

	public RoundData(ISong song, List<IPlayer> players2){
		this.song = song;
		this.players = players2;

	}
	public List<IPlayer> getPlayers(){
		return new ArrayList<>(players);
	}
}

