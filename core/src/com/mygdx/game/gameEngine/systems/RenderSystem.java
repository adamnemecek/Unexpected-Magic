package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.SpriteComponent;

/**
 * System that handles rendering.
 */

public class RenderSystem extends IteratingSystem{
	private SpriteBatch batch;
	
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<SpriteComponent> sm = ComponentMapper.getFor(SpriteComponent.class);

    public RenderSystem(SpriteBatch batch) {
    	super(Family.all(PositionComponent.class, SpriteComponent.class).get());
    	this.batch = batch;
        
    }

    public void processEntity(Entity entity, float deltaTime) {
        PositionComponent pos = pm.get(entity);
        SpriteComponent spr = sm.get(entity);
        spr.sprite.flip(false, true); //TODO
        batch.begin();
        batch.draw(spr.sprite.getTexture(), Math.round(pos.x), Math.round(pos.y));
        batch.end();
        System.out.println("RenderSystem.");
    }
}
