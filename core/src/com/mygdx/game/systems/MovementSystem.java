package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.VelocityComponent;

/**
 * System that handles movement.
 */

public class MovementSystem extends IteratingSystem{
	
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);

    public MovementSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    public void processEntity(Entity entity, float delta) {
        PositionComponent pos = pm.get(entity);
        VelocityComponent vel = vm.get(entity);
        System.out.println("MovementSystem. DELTA:" + delta);
        pos.x += vel.x * delta * 10; //TODO
        pos.y += vel.y * delta * 10; //TODO
        System.out.println("MovementSystem. NEW POS. x = " + pos.x + ", y = " + pos.y);
    }
}
