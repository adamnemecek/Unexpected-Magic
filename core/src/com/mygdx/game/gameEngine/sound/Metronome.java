package com.mygdx.game.gameEngine.sound;

import com.mygdx.game.Observers.ObserverHandler;
import com.mygdx.game.Observers.TickListener;

/**
 * Class that represents a metronome.
 * Takes bpm and plays a beat according to it.
 * @author rastom
 * Revised by rarvid, car0b1nius
 * 
 * Uses: ObserverHandler, TickListener, Synth
 * 
 * Used By: RoundManager
 */

public class Metronome implements TickListener {

	private final Synth synth;
	private final int timeSignature;

	public Metronome(int timeSignature) {
		this.synth = new Synth();
		this.synth.setInstrument(30);
		this.synth.changeChannel(9); //in order for drum, channel must be 9
		this.synth.setVolume(505);
		this.timeSignature = timeSignature;

		ObserverHandler.addTickListener(this);
	}

	@Override
	public void updateTick(int tick) {
		int beatFreq = tick%(64/timeSignature);

		if(beatFreq == 0){
			this.synth.noteOn(35);
		}
		else if(beatFreq == 2){
			this.synth.noteOff(35);
		}
	}
}
