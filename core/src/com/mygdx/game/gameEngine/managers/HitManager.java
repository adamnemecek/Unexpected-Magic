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
	private Map<Player, List<Integer>> activePitches;
	private int tick;
	
	
	public HitManager(List<Player> players, Synth synth){
		this.players = players;
		
		this.synth = synth;
		synth.setVolume(1200);
		ObserverHandler.addTickListener(this);
		activeNotes = new HashMap<>();
		//pitchesAtLane = new HashMap<>();
		activePitches = new HashMap<>();
		for (Player p : players){
			activePitches.put(p, new ArrayList<Integer>());
		}
	}
	
	
	public synchronized void notePlayStart(int lane, int player){
		Player p = players.get(player);
		if (activeNotes.get(p) != null){
			ITrackableNote note = activeNotes.get(p);
			if (note.getOctave() == lane){
				synth.noteOn(note.getPitch());
				activePitches.get(p).add(note.getPitch());
			}
			else{
				int pitch =lane + 12 * p.getVoice().octaveAtTick(tick);
				synth.noteOn(pitch);
				activePitches.get(p).add(pitch);
			}
		}
		else{
			int pitch =lane + 12 * p.getVoice().octaveAtTick(tick);
			synth.noteOn(pitch);
			activePitches.get(p).add(pitch);
		}
		

		/*List<Integer> activePitches = new ArrayList<>();
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
		*/
		

	}
	
	public synchronized void notePlayStop(int lane, int player){
		Player p = players.get(player);
		List<Integer> pitches = new ArrayList<>();
		for(Integer pitch : activePitches.get(p)){
			pitches.add(pitch);
		}
		for (Integer pitch : pitches){
			if (pitch % Constants.NUMBER_OF_LANES == lane){
				synth.noteOff(pitch);
				activePitches.get(p).remove(pitch);
				System.out.println("gets here");
			}
		}
		
		/*
		if (pitchesAtLane.get(lane) != null){
			for (int pitch : pitchesAtLane.get(lane)){
				synth.noteOff(pitch);
			}
			pitchesAtLane.put(lane, null);
		}
		*/
	}

	@Override
	public void updateTick(int tick) {
		this.tick = tick;
		
		for (Player p : players){
			updateScore(p);
			IVoice voice = p.getVoice();
			if (voice.noteAtTick(tick) != null){
				handleNoteChange(p, new TrackableNote(voice.noteAtTick(tick)));		
			}	
		}	
	}
	
	private void updateScore(Player p){
		ITrackableNote note = activeNotes.get(p); //gets the note the player is supposed to be playing
		if (note != null){
			for (Integer pitch : activePitches.get(p)){ 
				if ((int)pitch == note.getPitch()){ //compares the notes being played to the note that should be played
					p.getScore().hitNote(note.isHit());
					note.hit();
					//System.out.println("gained score: " + p.getScore().getScore());//TODO remove
				}
				else{
					p.getScore().wrongNote();
					//System.out.println("lost score: " + p.getScore().getScore());//TODO remove
				}
			}
		}
	}
	
	private void handleNoteChange(Player p, ITrackableNote note){
		if (activeNotes.get(p) != null){
			ITrackableNote old = activeNotes.get(p);
			if (!old.isHit()){
				p.getScore().missedNote();
			}
			
			
			/*else {
				if(pitchesAtLane.get(old.getOctave()) != null){
					List<Integer> list = pitchesAtLane.get(old.getOctave());
					list.remove(list.indexOf(old.getPitch()));
					if (list.isEmpty()){
						pitchesAtLane.put(old.getOctave(), null);
					}

					synth.noteOff(old.getPitch());
				}
				
			}*/
			
		}
		
		if (note.isRest()){
			activeNotes.put(p, null);
		}
		else{
			activeNotes.put(p, note);
	
			
		}
	}

	

}
