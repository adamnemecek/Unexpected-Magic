package com.mygdx.game.gameEngine.managers;

import java.util.concurrent.ConcurrentHashMap;

import javax.sound.midi.MidiChannel;

import java.util.Map;

public class NoteThread extends Thread{

	private final MidiChannel channel;
	private final int sleepTime = 5;
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
