package com.mygdx.game.model;

import com.mygdx.game.model.song.Song;

/**
* Class that handles ticking through the songmap in-game.
*/
public class Ticker {
	private int tick;
	private float trueTick;
	private final int songTotalTicks;
	private final double tickFreq;

	public Ticker(Song song){
		tick = 0;
		tickFreq = calculateTickFreq(song);
		songTotalTicks = 0; //TODO
		//how many ticks in the whole song? calculate somehow. using length of first voice of song maybe?
	}
	
	public int getTick(){
		return tick;
	}
	
	public void updateTick(float delta){
		trueTick += tickFreq * delta;
		if(trueTick > 1) {
			trueTick %= 1;
			tick++;
		}
		/*incTrueTick(delta);
		if(trueTickGreaterThanOne()){
			trueTickReset();
			incTick();
		}*/
	}
	
	private double calculateTickFreq(Song song){
		int bpm = song.bpm;
		int timeBeatSize = song.getTime()[1]; //the denominator of the time signature
		double r = bpm/60.0; //beats per second
		return r*64/timeBeatSize; //number of 64th notes per second
	}
	/*private void incTick(){
		tick++;
	}
	private void incTrueTick(float delta){
		//System.out.println("incTrueTick. " + trueTick + " += " + tickFreq + " * " + delta);
		trueTick += tickFreq * delta;
	}
	private void trueTickReset(){
		//System.out.print("trueTickReset. " + trueTick + " %= " + 1 + " -> ");
		trueTick %= 1;
		//System.out.print(trueTick + "\n");
	}
	private boolean trueTickGreaterThanOne(){
		return trueTick > 1;
	}*/
}
