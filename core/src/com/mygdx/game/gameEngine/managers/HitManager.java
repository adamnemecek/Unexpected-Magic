package com.mygdx.game.gameEngine.managers;

import java.util.HashMap;

/**
 * @author Arvid
 *
 */

import java.util.List;
import java.util.Map;

import com.badlogic.ashley.core.Entity;
import com.mygdx.game.Observers.ObserverHandler;
import com.mygdx.game.Observers.TickListener;
import com.mygdx.game.gameEngine.components.HitComponent;
import com.mygdx.game.gameEngine.components.NoteComponent;
import com.mygdx.game.gameEngine.sound.Synth;
import com.mygdx.game.gameEngine.systems.HitSystem;
import com.mygdx.game.gameEngine.systems.ScoreLineListener;
import com.mygdx.game.model.ITrackableNote;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.TrackableNote;
import com.mygdx.game.model.song.INote;
import com.mygdx.game.model.song.IVoice;
import com.mygdx.game.utilities.file.Constants;

public class HitManager implements TickListener{
	private List<Player> players;
	private Synth synth;
	private Map<Player, ITrackableNote> activeNotes;
	//private Map<Player, Entity> activeNotes;
	private int [] pitchAtLane;
	private int tick;
	
	
	public HitManager(List<Player> players, Synth synth, HitSystem hitSystem){
		this.players = players;
		this.pitchAtLane = new int [Constants.NUMBER_OF_LANES];
		this.synth = synth;
		ObserverHandler.addTickListener(this);
		activeNotes = new HashMap<>();
		//this.activeNotes = new HashMap<Player, Entity>(c);
		//hitSystem.addObserver(this);
		
	}
	
	
	public synchronized void notePlayStart(int lane){
		boolean hasHit = false;
		for (Player p : players){
			try{
				ITrackableNote note = activeNotes.get(p);
				if (note.getOctave() == lane){
					note.hit();
					p.getScore().hitNote(note.isHit());
					if (!hasHit){
						synth.noteOn(note.getPitch());
						pitchAtLane[lane] = note.getPitch();
						hasHit = true;
					}	
				}
			}
			catch (java.lang.NullPointerException np){
				
			}	
		}
		if (!hasHit){
			synth.noteOn(lane + 3*12);
			pitchAtLane[lane] = lane + 3*12;
		}
		
		

	}
	
	public void notePlayStop(int lane){
		synth.noteOff(pitchAtLane[lane]);
		
	}

	@Override
	public void updateTick(int tick) {
		this.tick = tick;
		for (Player p : players){
			IVoice voice = p.getVoice();
			if (voice.noteAtTick(tick) != null){
				handleNoteChange(p, new TrackableNote(voice.noteAtTick(tick)));		
			}	
		}	
	}
	
	private void handleNoteChange(Player p, ITrackableNote note){
		try{
			ITrackableNote old = activeNotes.get(p);
			if (!old.isHit()){
				p.getScore().missedNote();
			}
			
		}
		catch (java.lang.NullPointerException np){
			
		}
		System.out.println("gets here");
		if (note.isRest()){
			activeNotes.put(p, null);
		}
		else{
			activeNotes.put(p, note);
			
		}
	}

	

}
