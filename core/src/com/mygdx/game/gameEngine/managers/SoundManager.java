package com.mygdx.game.gameEngine.managers;

import javax.sound.midi.*;

public class SoundManager {

	int volume = 1000;
	int nChannelNumber = 1;
	MidiChannel channel;
	MidiChannel[] channels;
	Synthesizer synth = null;
	Soundbank soundbank;
	Instrument[] instr;
	
	 private final NoteThread notethread;
	
	public SoundManager(){
		
		try
		{
		this.synth = MidiSystem.getSynthesizer();
		}
		catch (MidiUnavailableException e){
		}

		soundbank = synth.getDefaultSoundbank();
		instr = synth.getAvailableInstruments();
		
		try
		{
			synth.open();
		}
			catch (MidiUnavailableException e){
				
		}
					
		
		notethread = new NoteThread(channel);
		//notethread.start();
		
	}
		
	public void play(int noteNumber, int noteDuration){
		notethread.play(noteNumber, noteDuration);
	}
	
	public void noteOn(int noteNumber){
		channel.noteOn(noteNumber, volume);
	}
	
	public void noteOff(int noteNumber){
		channel.noteOff(noteNumber);
	}
	
	public void setInstrument(int instrument){
				
		synth.loadInstrument(instr[instrument]);
		channels = synth.getChannels();
		channel = channels[nChannelNumber];
		
		channel.programChange(instr[instrument].getPatch().getProgram());
	
		Sequence sequence = null;
		try {
			sequence = new Sequence(Sequence.PPQ,1);
		} catch (InvalidMidiDataException e1) {
			
		}
	
		Track track = sequence.createTrack();
		
		ShortMessage sm = new ShortMessage();
		try {
			sm.setMessage(ShortMessage.PROGRAM_CHANGE, nChannelNumber, instrument,nChannelNumber );
		} catch (InvalidMidiDataException e) {
	
		}
		track.add(new MidiEvent(sm,0));
	
	}
	
	
}
