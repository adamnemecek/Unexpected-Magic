package com.mygdx.game.model;

import com.mygdx.game.model.song.ISong;

/**
* Class that handles ticking through the songmap in-game.
* @author soflarb
*/
public class Ticker {
	private int tick;
	private float time;
	private final int songTotalTicks;
	private final double tickFreq;
	private boolean ticking;

	public Ticker(ISong song){
		tick = 0;
		tickFreq = calculateTickFreq(song); //ticks/sec
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
		if(!ticking) return;
		time += delta;
		tick = (int)(time * tickFreq);
		/*trueTick += tickFreq * delta;
		if(trueTick > 1) {
			trueTick %= 1;
			tick++;
		}*/
		if(tick >= songTotalTicks){ ticking = false; }
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
