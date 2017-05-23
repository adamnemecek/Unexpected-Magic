package com.mygdx.game.model;

import java.util.List;

import com.mygdx.game.model.song.ISong;
import com.mygdx.game.model.song.IVoice;

/**
 * A class that represents a game round.
 * @author soflarb
 */
public class Round {
	public final ISong song;
	public final List<Player> players;
	public final List<IVoice> nonPlayerVoices;
	
	public Round(ISong song, List<Player> players, List<IVoice> nonPlayerVoices){
		this.song = song;
		this.players = players;
		this.nonPlayerVoices = nonPlayerVoices;
		
	}
	public List<Player> getPlayers(){
		return players;
	}
}

