package com.mygdx.game.gameEngine.managers;

import java.util.HashMap;

/**
 * @author Arvid
 */

import java.util.List;
import java.util.Map;

import com.badlogic.ashley.core.Entity;
import com.mygdx.game.gameEngine.components.HitComponent;
import com.mygdx.game.gameEngine.components.NoteComponent;
import com.mygdx.game.gameEngine.sound.Synth;
import com.mygdx.game.gameEngine.systems.HitSystem;
import com.mygdx.game.gameEngine.systems.ScoreLineListener;
import com.mygdx.game.model.ITrackableNote;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.song.INote;
import com.mygdx.game.model.song.IVoice;
import com.mygdx.game.utilities.file.Constants;

public class HitManager implements ScoreLineListener{
	private List<Player> players;
	private Synth synth;
	private Map<Player, ITrackableNote> activeNotes;
	//private Map<Player, Entity> activeNotes;
	private int [] pitchAtLane;
	
	
	public HitManager(List<Player> players, Synth synth, HitSystem hitSystem){
		this.players = players;
		this.pitchAtLane = new int [Constants.NUMBER_OF_LANES];
		this.synth = synth;
		//this.activeNotes = new HashMap<Player, Entity>();
		hitSystem.addObserver(this);
	}
	
	public void reachedScoreLine(Entity noteEntity){

		/*IVoice voice = noteEntity.getComponent(VoiceComponent.class).getVoice();

		for (Player p : players){
			
			if(p.getVoice().equals(voice)){
				activeNotes.put(p, noteEntity);
			}
		}*/
	}
	
	public void passedScoreLine(Entity noteEntity){
		/*for (Player p: players){
			
			if(activeNotes.get(p) != null){
				
				if(activeNotes.get(p).equals(noteEntity)){

					activeNotes.put(p, null);
					if (!noteEntity.getComponent(HitComponent.class).isHit()){
						p.getScore().missedNote();
					}
					break;
				}
			}
			
		}*/
	}
	
	public synchronized void notePlayStart(int lane){
		boolean hasHit = false;
		for (Player p : players){
			
		}
		
		
		/*boolean hasHit = false;
		for (Player p : players){
			if (this.activeNotes.get(p)!=null){
				Entity noteEntity = this.activeNotes.get(p);
				INote n = noteEntity.getComponent(NoteComponent.class).getNote();
				if (n.getOctave() == lane ){
					p.getScore().hitNote(noteEntity.getComponent(HitComponent.class).isHit());
					noteEntity.getComponent(HitComponent.class).hit();
					synth.noteOn(n.getPitch());
					this.pitchAtLane [lane] = n.getPitch();
					hasHit = true;
					System.out.println(p.getName() + ": " + p.getScore().getScore());
					
				}
			}
			
			
		}
		if (!hasHit){
			//synth.noteOn(lane +5*12); //TODO should be another pitch
			//pitchAtLane[lane] = lane +5*12;
		}*/

	}
	
	public void notePlayStop(int lane){
		synth.noteOff(pitchAtLane[lane]);
		
	}

	

}
