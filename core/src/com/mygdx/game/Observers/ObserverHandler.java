package com.mygdx.game.Observers;

import java.util.ArrayList;
import java.util.List;

public abstract class ObserverHandler {
	
	private List<TickListener> tickListeners = new ArrayList();
	
	public void addTickListener(TickListener listener){
		tickListeners.add(listener);
	}
	
	public void removeTickListener(TickListener listener){
		tickListeners.remove(listener);
	}
	
	public void notifyTickListeners(){
		for (TickListener tl : tickListeners){
			tl.newTick();
		}
	}

}
