package com.mygdx.game.gameEngine.sound;

import com.mygdx.game.model.song.INote;
/**
 * Interface for a synth.
 * @author rarvid
 * 
 * Uses: INote
 * 
 * Used By: Synth, HitManager, RoundManager, SongPlayBack
 */
public interface ISynth {
	public void setSongTimeSignaure(int timeSignature, int bpm);
	
	public void play(INote note);
	
	public void play(int noteNumber, int noteDuration);
	
	public void noteOn(int noteNumber);
	
	public void noteOff(int noteNumber);
	
	public void setInstrument(int instrument);
	
	public void changeChannel(int channel);
	
	public void setVolume(int v);

}
