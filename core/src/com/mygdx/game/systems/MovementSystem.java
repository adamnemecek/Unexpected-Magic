package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.VelocityComponent;

/**
 * System that handles movement.
 */

public class MovementSystem extends EntitySystem{
    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);

    public MovementSystem(){}

    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i){
            Entity entity = entities.get(i);
            PositionComponent pos = pm.get(entity);
            VelocityComponent vel = vm.get(entity);

            pos.x += vel.x * deltaTime;
            pos.y += vel.y * deltaTime;
        }
    }
}
