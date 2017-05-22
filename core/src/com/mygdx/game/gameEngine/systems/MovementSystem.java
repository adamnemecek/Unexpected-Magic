package com.mygdx.game.gameEngine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.gameEngine.components.PositionComponent;
import com.mygdx.game.gameEngine.components.VelocityComponent;
import com.mygdx.game.gameEngine.managers.HitManager;
import com.mygdx.game.utilities.file.Constants;

/**
 * System that handles movement.
 * @author soflarb
 * Revised by 
 */

public class MovementSystem extends IteratingSystem{
	
	//private HitManager hitManager;
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);

    public MovementSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    public void processEntity(Entity entity, float delta) {
        PositionComponent pos = pm.get(entity);
        VelocityComponent vel = vm.get(entity);
        
        //ever since I was 8 or 9, I've been standing on the scoreLine
        
        
        //System.out.println("MovementSystem. DELTA:" + delta);
        pos.x = pos.x + (int)delta*vel.getX();//TODO
        pos.y = pos.y + (int)delta*vel.getY();//TODO

        //System.out.println("MovementSystem. NEW POS. x = " + pos.x + ", y = " + pos.y);
    }
}
