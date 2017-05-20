package com.mygdx.game.gameEngine.sound;

/**
 * Created by rasmus on 2017-05-18.
 */

//TODO, generally make better, less hardcoding..

public class Metronome extends Thread {
	private int tickInterval;
	private Synth synth;
	private boolean interrupted;

	public Metronome(Synth s, int bpm) {
		this.synth = s;
		this.tickInterval = calculate_msPB(bpm);
		this.synth.setInstrument(17);
		this.synth.changeChannel(9); //in order for drum, channel must be 9
		this.synth.setVolume(505);
		this.interrupted = false;
	}

	private int calculate_msPB(int bpm){
		return (60*1000)/bpm;
	}

	@Override
	public void run() {


		while (!interrupted) {

			this.synth.noteOn(80);

			try {
				Metronome.sleep(this.tickInterval);
			} catch (InterruptedException e) {
				interrupted = true;
			}
			this.synth.noteOff(80);

		}

	}

	public void stopMetronome(){
		this.interrupted = true;
	}
}
