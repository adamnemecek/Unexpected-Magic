package com.mygdx.game.gameEngine.systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.gameEngine.components.CompositeSpriteComponent;
import com.mygdx.game.gameEngine.components.PositionComponent;
import com.mygdx.game.utilities.file.Constants;

public class HitSystem extends IteratingSystem {
	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<CompositeSpriteComponent> cm = ComponentMapper.getFor(CompositeSpriteComponent.class);

	private List<ScoreLineListener> listeners;
    public HitSystem() {
        super(Family.all(PositionComponent.class).get());
        listeners = new ArrayList();
 
	 }
    
    public void addObserver(ScoreLineListener sll){
    	listeners.add(sll);
    }
    
    public void removeObserver(ScoreLineListener sll){
    	listeners.remove(sll);
    }
    
    private void alertReached(Entity entity){
    	for (ScoreLineListener l : listeners){
    		l.reachedScoreLine(entity);
    	}
    }
    
    private void alertPassed(Entity entity){
    	for (ScoreLineListener l : listeners){
    		l.passedScoreLine(entity);
    	}
    }
    
    @Override
	protected void processEntity(Entity entity, float deltaTime) {
    	//TODO this is extremely unforgiving
		PositionComponent pos = pm.get(entity);
		CompositeSpriteComponent cpc = cm.get(entity);


         if(pos.y <= 0 && pos.y > - 5){
         	alertReached(entity);
         }
         
         else if(pos.y + cpc.getCompositeSprite().getLength() < -15){
        	 alertPassed(entity);
         }
		
	}  
    


}
