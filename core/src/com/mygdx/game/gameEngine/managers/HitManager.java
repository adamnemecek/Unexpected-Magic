package com.mygdx.game.gameEngine.managers;

/**
 * @author Arvid
 */

import java.util.List;

import com.mygdx.game.model.Player;
import com.mygdx.game.model.Ticker;
import com.mygdx.game.model.song.ISong;

public class HitManager {
	private Ticker ticker;
	//private ISong song;
	private List<Player> players;
	private SoundManager soundManager;
	private boolean hasHit = false;
	
	public HitManager(Ticker ticker, List<Player> players, SoundManager soundManager){
		this.ticker = ticker;
		this.players = players;
		this.soundManager = soundManager;
	}
	
	public void notePlayStart(int lane){
		for (Player p : players){
			//compares the note being played with the notes each player is currently supposed to play
			int activePitch = p.getVoice().pitchAtTick(ticker.getTick()); 
			if (lane == activePitch%12 && activePitch != -1){ 
				p.getScore().hitNote(false);
				soundManager.noteOn(activePitch);
				hasHit = true;
			}
		}
		if (!hasHit){
			soundManager.noteOn(lane);
		}
	}
	
	public void notePlayStop(int lane){
		for (int i = lane%12; i <= 127; i += 12){ //goes through all notes associated with that key and turns them off
			soundManager.noteOff(i);
		}
	}

}
