package com.mygdx.game.gameEngine.managers;

/**
 * @author Arvid
 */

import java.util.List;

import com.mygdx.game.model.Player;
import com.mygdx.game.model.song.ISong;

public class HitManager {
	private ISong song;
	private List<Player> players;
	private boolean[] canGain;
	
	public void notePlayStart(int lane){
		for (Player p : players){
			//INote Note = p.getVoice().currentlyPlaying();
		
		}
	}
	
	public void notePlayStop(int lane){
		
	}

}
