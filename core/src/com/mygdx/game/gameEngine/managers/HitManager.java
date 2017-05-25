package com.mygdx.game.gameEngine.managers;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Arvid
 *
 */

import java.util.List;
import java.util.Map;


import com.mygdx.game.Observers.ObserverHandler;
import com.mygdx.game.Observers.TickListener;

import com.mygdx.game.gameEngine.sound.Synth;

import com.mygdx.game.model.ITrackableNote;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.TrackableNote;

import com.mygdx.game.model.song.IVoice;
import com.mygdx.game.utilities.file.Constants;

public class HitManager implements TickListener{
	private List<Player> players;
	private Synth synth;
	private Map<Player, ITrackableNote> activeNotes;
	private Map<Integer, List<Integer>> pitchesAtLane;
	private int tick;
	
	
	public HitManager(List<Player> players, Synth synth){
		this.players = players;
		
		this.synth = synth;
		ObserverHandler.addTickListener(this);
		activeNotes = new HashMap<>();
		pitchesAtLane = new HashMap<>();
	}
	
	
	public synchronized void notePlayStart(int lane, int player){

		List<Integer> activePitches = new ArrayList<>();
		for (Player p : players){
			try{
				ITrackableNote note = activeNotes.get(p);
				if (note.getOctave() == lane){
					p.getScore().hitNote(note.isHit());
					note.hit();
					activePitches.add(note.getPitch());
					synth.noteOn(note.getPitch());
					
					
				}
			}
			catch (java.lang.NullPointerException np){
				
			}	
		}
		
		
		if (!activePitches.isEmpty()){
			pitchesAtLane.put(lane, activePitches);
		}
		
		

	}
	
	public void notePlayStop(int lane, int player){
		System.out.println(player);
		if (pitchesAtLane.get(lane) != null){
			for (int pitch : pitchesAtLane.get(lane)){
				synth.noteOff(pitch);
			}
			pitchesAtLane.put(lane, null);
		}
		
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
			else {
				if(pitchesAtLane.get(old.getOctave()) != null){
					List<Integer> list = pitchesAtLane.get(old.getOctave());
					list.remove(list.indexOf(old.getPitch()));
					if (list.isEmpty()){
						pitchesAtLane.put(old.getOctave(), null);
					}

					synth.noteOff(old.getPitch());
				}
				
			}
			
		}
		catch (java.lang.NullPointerException np){
			
		}
		
		if (note.isRest()){
			activeNotes.put(p, null);
		}
		else{
			activeNotes.put(p, note);
			if (pitchesAtLane.get(note.getOctave()) != null){
				pitchesAtLane.get(note.getOctave()).add(note.getPitch());
				synth.noteOn(note.getPitch());
				activeNotes.get(p).hit();
			}
			
		}
	}

	

}
