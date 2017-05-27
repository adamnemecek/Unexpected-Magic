package com.mygdx.game.gameEngine.sound;

import java.util.concurrent.ConcurrentHashMap;
import javax.sound.midi.MidiChannel;
import java.util.Map;

/**
 * Class for playing specific notes, during a specified time.
 * @author rastom
 * 
 * Uses: None
 * Used by: Synth
 */

public class NoteThread extends Thread{

	private final MidiChannel channel;

	//thread will check between every sleep time if the note is over and then remove
	//sleepTime adjust accuracy
	private final int sleepTime = 1;
	private final int noteVolume = 1000;
	
	Map<Integer, Integer> notes = new ConcurrentHashMap<Integer, Integer>();
	
	public NoteThread (MidiChannel channel){
		this.channel = channel;
	
	}
	
	public void play(Integer noteNumber, Integer noteDuration){

	    notes.put(noteNumber, noteDuration);
		
		channel.noteOn(noteNumber, noteVolume);
	}
	
	@Override
	public void run() {	
		
		boolean interrupted = false;


		while(!interrupted){
			try {
				NoteThread.sleep(sleepTime);
			} catch (InterruptedException e) {
				interrupted = true;
			}
			
			for(Map.Entry<Integer, Integer> entry : notes.entrySet()){
				Integer timeLeft = entry.getValue() - sleepTime;
				if (timeLeft <= 0){
					channel.noteOff(entry.getKey());
					notes.remove(entry.getKey());
					
				}
				else{
					entry.setValue(timeLeft);
				}
				
			}
		}
	}
}
