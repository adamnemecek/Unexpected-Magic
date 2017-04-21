package com.mygdx.game.model;

import java.util.ArrayList;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.managers.EntityManager;
import com.mygdx.game.model.song.Song;
/**
 * A class that represents a game round.
 */
public class Round {
	public final Song song;
	public final ArrayList<Player> players;
	private Ticker ticker;
	EntityManager entityManager;
	Engine engine;
	SpriteBatch batch;
	
	//public Round(){}//TODO for testing only.
	
	public Round(Song song, ArrayList<Player> players, Engine engine, SpriteBatch batch){
		this.song = song;
		this.players = players;
		this.engine = engine;
		this.batch = batch;
		ticker = new Ticker(song);
		entityManager = new EntityManager(engine, batch, song);
	}
	private void updateTick(float delta){
		ticker.updateTick(delta);
	}
	public int getTick(){
		return ticker.getTick();
	}
	public void update(float delta){
		updateTick(delta);
		entityManager.update(ticker.getTick());
	}
}

