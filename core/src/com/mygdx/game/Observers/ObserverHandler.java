package com.mygdx.game.Observers;



import java.util.ArrayList;
import java.util.List;

/**
* Class for handling observers
* @author rarvid
* 
* Uses: TickListener
* 
* Used by: HitManager, GameScreen, Metronome, SongPlayback, Ticker
*/
public abstract class ObserverHandler {
	
	private static List<TickListener> tickListeners = new ArrayList<>();
	
	public static void addTickListener(TickListener listener){
		tickListeners.add(listener);
	}
	
	public static void removeTickListener(TickListener listener){
		tickListeners.remove(listener);
	}
	
	public static void clearTickListeners(){
		tickListeners.clear();
	}
	
	public static void notifyTickListeners(int tick){
		for (TickListener tl : tickListeners){
			tl.updateTick(tick);
		}
	}

}
