package com.mygdx.game.gameEngine.managers;

import javax.sound.midi.*;

import com.mygdx.game.model.song.INote;

public class SoundManager {

	private int volume = 1000;
	private int nChannelNumber = 1;
	private MidiChannel channel;
	private MidiChannel[] channels;
	private Synthesizer synth = null;
	private Soundbank soundbank;
	private Instrument[] instr;
	private int bpm = 155;
	private int timeSignature = 4; //TODO read from song

	
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
					
		setInstrument(90);
		notethread = new NoteThread(channel);
		notethread.start();
		
	}

	public void setSongTimeSignaure(int timeSignaure, int bpm){
		this.timeSignature = timeSignaure;
		this.bpm = bpm;
	}

	public void play(INote note){
		 play(note.getPitch(), timeConvert(note.getDuration(),timeSignature,bpm));
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
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
	
		Track track = sequence.createTrack();
		
		ShortMessage sm = new ShortMessage();
		try {
			sm.setMessage(ShortMessage.PROGRAM_CHANGE, nChannelNumber, instrument,nChannelNumber );
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
		track.add(new MidiEvent(sm,0));
	
	}

	private int timeConvert(double noteSignature, double songSignature, double bpm){

		double mspm = 60*1000;
		double i = mspm/(songSignature*(1/(noteSignature))*bpm);
		System.out.println(i);
		return (int)i;
	}
	
}
