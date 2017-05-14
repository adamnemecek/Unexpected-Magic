package com.mygdx.game.gameEngine.managers;

import com.mygdx.game.model.Score;
import com.mygdx.game.model.Ticker;
import com.mygdx.game.model.NoteLanes;
import com.mygdx.game.model.Round;

/**
 * 
 * @author soflarb
 * Revised by Arvid
 *
 */

public class RoundManager {
	private Round round;
	private EntityManager entityManager;
	private Ticker ticker;
	private final NoteLanes noteLanes;

	public RoundManager(Round round, EntityManager entityManager, Ticker ticker, NoteLanes noteLanes){
	this.round = round;
	this.entityManager = entityManager;
	this.ticker = ticker;
	this.noteLanes = noteLanes;

	}
	
	public void activateLane(int lane){
		this.noteLanes.activateLane(lane);
	}
	
	public void deactivateLane(int lane){
		this.noteLanes.deactivateLane(lane);
	}
	
	public int getTick(){
		return ticker.getTick();
	}
	//returns true if game is over
	public boolean gameOver(){
		//TODO use ticker do not use position or anything
		//temporary this will end the game too early but it's a test:
		return (ticker.getTick() > 0 && !ticker.isTicking());
	}
	
	public void update(float delta){
		ticker.updateTick(delta);
		entityManager.update(getTick());
	}
}
