package com.mygdx.game.gameEngine.managers;
/**
 * Created by rasmus on 2017-05-18.
 */

//TODO, generally make better, less hardcoding..

public class Metronome extends Thread {
	private int tickInterval;
	private SoundManager soundManager;

	public Metronome(SoundManager s, int tickInterval) {
		this.soundManager = s;
		this.tickInterval = tickInterval;
		this.soundManager.setInstrument(17);
		this.soundManager.changeChannel(9); //in order for drum, channel must be 9
		this.soundManager.setVolume(40);
	}

	@Override
	public void run() {
		boolean interrupted = false;

		while (!interrupted) {

			this.soundManager.noteOn(80);

			try {
				NoteThread.sleep(this.tickInterval);
			} catch (InterruptedException e) {
				interrupted = true;
			}
			this.soundManager.noteOff(80);

		}

	}
}
