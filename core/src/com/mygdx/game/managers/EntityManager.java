package com.mygdx.game.managers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.RenderableComponent;
import com.mygdx.game.components.SpriteComponent;
import com.mygdx.game.components.VelocityComponent;
import com.mygdx.game.systems.MovementSystem;

/**
* Class for managing entities.
*/
public class EntityManager {
	private Engine engine;
	
	public EntityManager(Engine engine, SpriteBatch batch){
		this.engine = engine;
		//Create all the systems
		MovementSystem movementSystem = new MovementSystem();
		//Add all the systems to the engine
		engine.addSystem(movementSystem);
		
		//Create a test entity
		Entity testEntity = new Entity();
		PositionComponent testPositionComponent = new PositionComponent();
		VelocityComponent testVelocityComponent = new VelocityComponent();
		SpriteComponent testSpriteComponent = new SpriteComponent();
		RenderableComponent testRenderableComponent = new RenderableComponent();
		//TODO How does one give the components values? I'll do it like this now.
		testPositionComponent.x = 0;
		testPositionComponent.y = 0;
		testVelocityComponent.x = 0;
		testVelocityComponent.y = 1;
		testSpriteComponent.sprite = new Sprite(new Texture("textures/textureCheckedBlue16x16.png"));
		
		testEntity.add(testPositionComponent).add(testVelocityComponent).add(testSpriteComponent).add(testRenderableComponent);
		
		//Add all entities to engine
		engine.addEntity(testEntity);
		
	}
	/*
	public void update(){
		engine.update(deltaTime);
	}
	*/
}
