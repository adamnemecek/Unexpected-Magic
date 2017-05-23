package com.mygdx.game.Observers;

/**
 * Class for handling observers
 * @author Arvid
 */


import java.util.ArrayList;
import java.util.List;

public abstract class ObserverHandler {
	
	private static List<TickListener> tickListeners = new ArrayList();
	
	public static void addTickListener(TickListener listener){
		tickListeners.add(listener);
	}
	
	public static void removeTickListener(TickListener listener){
		tickListeners.remove(listener);
	}
	
	public static void notifyTickListeners(int tick){
		for (TickListener tl : tickListeners){
			tl.updateTick(tick);
		}
	}

}
