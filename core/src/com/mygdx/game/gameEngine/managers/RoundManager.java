package com.mygdx.game.gameEngine.managers;

import com.mygdx.game.model.Ticker;
import com.mygdx.game.model.Round;

public class RoundManager {
	private Round round;
	EntityManager entityManager;
	private Ticker ticker;
	
	public RoundManager(Round round, EntityManager entityManager, Ticker ticker){
	this.round = round;
	this.entityManager = entityManager;
	this.ticker = ticker;
	}
	
	public int getTick(){
		return ticker.getTick();
	}
	public void update(float delta){
		ticker.updateTick(delta);
		entityManager.update(ticker.getTick());
	}
}
