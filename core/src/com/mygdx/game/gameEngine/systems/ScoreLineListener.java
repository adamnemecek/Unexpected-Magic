package com.mygdx.game.gameEngine.systems;

import com.badlogic.ashley.core.Entity;

public interface ScoreLineListener {
	
	void reachedScoreLine(Entity entity);
	void passedScoreLine(Entity entity);

}
