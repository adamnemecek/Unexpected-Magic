package com.mygdx.game.gameEngine.managers;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Arvid
 */

import java.util.List;
import java.util.Map;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.mygdx.game.gameEngine.components.HitComponent;
import com.mygdx.game.gameEngine.components.NoteComponent;
import com.mygdx.game.gameEngine.components.VoiceComponent;
import com.mygdx.game.gameEngine.systems.HitSystem;
import com.mygdx.game.gameEngine.systems.ScoreLineListener;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Ticker;
import com.mygdx.game.model.song.INote;
import com.mygdx.game.model.song.ISong;
import com.mygdx.game.model.song.IVoice;
import com.mygdx.game.utilities.file.Constants;

public class HitManager implements ScoreLineListener{
	private List<Player> players;
	private SoundManager soundManager;
	private Map<Player, Entity> activeNotes;
	private int [] pitchAtLane;
	
	
	public HitManager(Ticker ticker, List<Player> players, SoundManager soundManager, HitSystem hitSystem){
		this.players = players;
		this.pitchAtLane = new int [Constants.NUMBER_OF_LANES];
		this.soundManager = soundManager;
		this.activeNotes = new HashMap<Player, Entity>();
		hitSystem.addObserver(this);
	}
	
	public void reachedScoreLine(Entity noteEntity){
		for (Player p : players){
			
			IVoice voice = noteEntity.getComponent(VoiceComponent.class).getVoice();
			if(p.getVoice().equals(voice) && activeNotes.get(p) == null){
				activeNotes.put(p, noteEntity);
			}
		}
	}
	
	public void passedScoreLine(Entity noteEntity){
		for (Player p: players){
			
			if(activeNotes.get(p) != null){
				
				if(activeNotes.get(p).equals(noteEntity)){
					
					activeNotes.put(p, null);
					
					if (!noteEntity.getComponent(HitComponent.class).isHit()){
						p.getScore().missedNote();
					}
				}
			}
			
		}
	}
	
	public synchronized void notePlayStart(int lane){
		boolean hasHit = false;
		for (Player p : players){
			if (this.activeNotes.get(p)!=null){
				Entity noteEntity = this.activeNotes.get(p);
				INote n = noteEntity.getComponent(NoteComponent.class).getNote();
				if (n.getOctave() == lane ){
					p.getScore().hitNote(noteEntity.getComponent(HitComponent.class).isHit());
					noteEntity.getComponent(HitComponent.class).hit();
					soundManager.noteOn(n.getPitch());
					this.pitchAtLane [lane] = n.getPitch();
					hasHit = true;
					
				}
			}
			
			
		}
		if (!hasHit){
			soundManager.noteOn(lane +5*12); //TODO should be another pitch
			pitchAtLane[lane] = lane +5*12; 
		}
		
		/*for (Player p : players){
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
		hasHit = false;*/
		
		
	}
	
	public void notePlayStop(int lane){
		//soundManager.noteOff(pitchAtLane[lane]);
		for (int i = lane; i <= 127; i +=12){
			soundManager.noteOff(i);//TODO this is overkill
		}
	}

	

}
