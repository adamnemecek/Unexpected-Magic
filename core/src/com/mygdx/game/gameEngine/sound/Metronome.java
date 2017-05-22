package com.mygdx.game.gameEngine.sound;

/**
 * Class for that works as a metronome
 * takes bpm and plays a beat according to it
 * @author rastom
 */

//TODO, generally make better, less hardcoding..

public class Metronome extends Thread {
	private int tickInterval;
	private Synth synth;
	private boolean interrupted;

	public Metronome(int bpm) {
		this.synth = new Synth();
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
