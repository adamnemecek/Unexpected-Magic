package com.mygdx.game.gameEngine.managers;

import java.util.ArrayList;
import java.util.HashMap;


import java.util.List;
import java.util.Map;


import com.mygdx.game.Observers.ObserverHandler;
import com.mygdx.game.Observers.TickListener;
import com.mygdx.game.gameEngine.sound.ISynth;
import com.mygdx.game.gameEngine.sound.Synth;
import com.mygdx.game.model.ITrackableNote;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.TrackableNote;

import com.mygdx.game.model.song.IVoice;
import com.mygdx.game.utilities.file.Constants;

/**
 * class that evaluates whether the player's input is correct 
 * and handles score and noteplaying accordingly
 * @author Arvid
 *
 */

public class HitManager implements TickListener{
	private List<Player> players;
	private ISynth synth;
	private Map<Player, ITrackableNote> activeNotes;
	private Map<Player, List<Integer>> activePitches;
	private int tick;
	
	
	public HitManager(List<Player> players, ISynth synth){
		this.players = players;
		
		this.synth = synth;
		synth.setVolume(1200);
		ObserverHandler.addTickListener(this);
		activeNotes = new HashMap<>();
		activePitches = new HashMap<>();
		for (Player p : players){
			activePitches.put(p, new ArrayList<Integer>());
		}
	}
	
	
	public synchronized void notePlayStart(int lane, int player){
		if(player >= players.size()) return; //TODO remove when multiplayer is properly implemented
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
	
		

	}
	
	public synchronized void notePlayStop(int lane, int player){
		if(player >= players.size()) return; //TODO remove when multiplayer is properly implemented
		Player p = players.get(player);
		List<Integer> pitches = new ArrayList<>();
		for(Integer pitch : activePitches.get(p)){
			pitches.add(pitch);
		}
		for (Integer pitch : pitches){
			if (pitch % Constants.NUMBER_OF_LANES == lane){
				synth.noteOff(pitch);
				activePitches.get(p).remove(pitch);
			
			}
		}
		
		
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
					
				}
				else{
					p.getScore().wrongNote();
					
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
			
			
		}
		
		if (note.isRest()){
			activeNotes.put(p, null);
		}
		else{
			activeNotes.put(p, note);
		}
	}

	

}
