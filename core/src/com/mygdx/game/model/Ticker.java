package com.mygdx.game.model;

import com.mygdx.game.Observers.ObserverHandler;
import com.mygdx.game.model.song.ISong;

/**
* Class that handles ticking through the songmap in-game.
* @author soflarb
*/
public class Ticker {
	private int tick;
	private int oldTick;
	private float time;
	private final int songTotalTicks;
	private final double tickFreq;
	private boolean ticking;

	public Ticker(ISong song){
		time = -2;
		oldTick = -1;
		tickFreq = calculateTickFreq(song); //ticks/sec
		tick = (int)tickWithDecimals();
		songTotalTicks = song.getVoices()[0].length(); //TODO voice length is in number of 64 notes, every tick is a 64 note
		//System.out.println("songTotalTicks: " +songTotalTicks);
		ticking = true;
	}
	
	public int getTick(){
		return tick;
	}
	// returns length of tick in milliseconds
	public double getTickLength(){
		return 1000/tickFreq;
	}
	
	public void updateTick(float delta){
		if(!isTicking()) return;
		time += delta;
		tick = (int)(tickWithDecimals());
		/*trueTick += tickFreq * delta;
		if(trueTick > 1) {
			trueTick %= 1;
			tick++;
		}*/
		if (tick > oldTick){
			oldTick = tick;
			ObserverHandler.notifyTickListeners(tick);
		}
		if(tick >= songTotalTicks){ ticking = false; }
	}
	public double tickWithDecimals() {
		return time * tickFreq;
	}
	private double calculateTickFreq(ISong song){
		int bpm = song.getBpm();
		int timeBeatSize = song.getTime()[1]; //the denominator of the time signature
		double r = bpm/60.0; //beats per second
		return r*64/timeBeatSize; //number of 64th notes per second
	}
	public boolean isTicking(){
		return ticking;
	}
}
