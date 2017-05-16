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
	private int[] pitchAtLane; //keeps track of active pitch per lane
	
	public HitManager(Ticker ticker, List<Player> players, SoundManager soundManager){
		this.ticker = ticker;
		this.players = players;
		this.soundManager = soundManager;
		this.pitchAtLane = new int[12];
	}
	
	public void notePlayStart(int lane){
		for (Player p : players){
			//compares the note being played with the notes each player is currently supposed to play
			int activePitch = p.getVoice().pitchAtTick(ticker.getTick()); 
			if (lane == activePitch%12 && activePitch != -1){ 
				p.getScore().hitNote(false);
				soundManager.noteOn(activePitch);
				pitchAtLane[lane] = activePitch;
				hasHit = true;
			}
		}
		if (!hasHit){
			soundManager.noteOn(lane +3*12); //TODO should be another pitch
			pitchAtLane[lane] = lane +3*12; 
		}
		hasHit = false;
	}
	
	public void notePlayStop(int lane){
		soundManager.noteOff(pitchAtLane[lane]);
	}

}
