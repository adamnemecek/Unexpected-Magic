package com.mygdx.game.gameEngine.sound;

/**
 * Created by rasmus on 2017-05-18.
 */

//TODO, generally make better, less hardcoding..

public class Metronome extends Thread {
	private int tickInterval;
	private Synth synth;

	public Metronome(Synth s, int tickInterval) {
		this.synth = s;
		this.tickInterval = tickInterval;
		this.synth.setInstrument(17);
		this.synth.changeChannel(9); //in order for drum, channel must be 9
		this.synth.setVolume(40);
	}

	@Override
	public void run() {
		boolean interrupted = false;

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
}
