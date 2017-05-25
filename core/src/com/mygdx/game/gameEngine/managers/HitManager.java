package com.mygdx.game.gameEngine.managers;

import java.util.ArrayList;
import java.util.HashMap;


import java.util.List;
import java.util.Map;


import com.mygdx.game.Observers.ObserverHandler;
import com.mygdx.game.Observers.TickListener;
import com.mygdx.game.gameEngine.sound.ISynth;

import com.mygdx.game.model.IPlayer;
import com.mygdx.game.model.ITrackableNote;

import com.mygdx.game.model.TrackableNote;

import com.mygdx.game.model.song.IVoice;
import com.mygdx.game.utilities.file.Constants;

/**
 * class that evaluates whether the player's input is correct 
 * and handles score and noteplaying accordingly
 * @author Arvid
 * Revised By Arvid & 
 *
 */

public class HitManager implements TickListener{
	private List<? extends IPlayer> players;
	private ISynth synth;
	private Map<IPlayer, ITrackableNote> activeNotes; //keeps track of the notes each player is supposed to play
	private Map<IPlayer, List<Integer>> activePitches; //keeps track of the notes each player is currently playing
	private int tick;
	
	
	public HitManager(List<? extends IPlayer> players, ISynth synth){
		this.players = players;
		
		this.synth = synth;
		synth.setVolume(1200);
		ObserverHandler.addTickListener(this);
		activeNotes = new HashMap<>();
		activePitches = new HashMap<>();
		for (IPlayer p : players){
			activePitches.put(p, new ArrayList<Integer>());
		}
	}
	
	//Called by InputAction (via RoundManager), interprets which note the key press should be, plays it and logs it
	public synchronized void notePlayStart(int lane, int player){
		if(player >= players.size()) return; //TODO remove when multiplayer is properly implemented
		IPlayer p = players.get(player);

		if (activeNotes.get(p) != null){
			ITrackableNote note = activeNotes.get(p);
			//check if correct
			if (note.getOctave() == lane){
				synth.noteOn(note.getPitch());
				activePitches.get(p).add(note.getPitch());
			}
			//if not correct
			else{
				int pitch =lane + 12 * p.getVoice().octaveAtTick(tick);
				synth.noteOn(pitch);
				activePitches.get(p).add(pitch);
			}
		}
		//if not correct
		else{
			int pitch =lane + 12 * p.getVoice().octaveAtTick(tick);
			synth.noteOn(pitch);
			activePitches.get(p).add(pitch);
		}
	
		

	}
	//Called by InputAction (via RoundManager), stops playing the note corresponding to the uplifted key and removes it from activePitches
	public synchronized void notePlayStop(int lane, int player){
		if(player >= players.size()) return; //TODO remove when multiplayer is properly implemented
		
		IPlayer p = players.get(player);
		List<Integer> pitches = new ArrayList<>();
		for(Integer pitch : activePitches.get(p)){ //creates a copy of ActivePitches
			pitches.add(pitch);
		}
		for (Integer pitch : pitches){ //goes through the copy, modifies the original with new information
			if (pitch % Constants.NUMBER_OF_LANES == lane){
				synth.noteOff(pitch);
				activePitches.get(p).remove(pitch);
			
			}
		}
		
		
	}

	@Override
	//called everytime the game updates its tick, updates score and checks for changes to which notes should be played
	public void updateTick(int tick) {
		this.tick = tick;
		
		for (IPlayer p : players){
			updateScore(p);
			IVoice voice = p.getVoice();
			if (voice.noteAtTick(tick) != null){
				handleNoteChange(p, new TrackableNote(voice.noteAtTick(tick)));		
			}	
		}	
	}
	
	private void updateScore(IPlayer p){
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
	
	private void handleNoteChange(IPlayer p, ITrackableNote note){
		if (activeNotes.get(p) != null){ //checks if there was a note the player was supposed to play before this one
			ITrackableNote old = activeNotes.get(p);
			if (!old.isHit()){ //checks if the player missed the previous note
				p.getScore().missedNote();
			}
			
			
		}
		//updates activeNotes with the new note
		if (note.isRest()){
			activeNotes.put(p, null);
		}
		else{
			activeNotes.put(p, note);
		}
	}

	

}
