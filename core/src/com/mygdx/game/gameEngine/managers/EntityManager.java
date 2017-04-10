package com.mygdx.game.managers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Constants;
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
		
		//Create a test entity----
		Entity testEntity = new Entity();
		PositionComponent testPositionComponent = new PositionComponent();
		VelocityComponent testVelocityComponent = new VelocityComponent();
		SpriteComponent testSpriteComponent = new SpriteComponent();
		RenderableComponent testRenderableComponent = new RenderableComponent();
		//TODO How does one give the components values? I'll do it like this now.
		testPositionComponent.x = 0;
		testPositionComponent.y = Constants.VIEWPORT_DIM[1];
		testVelocityComponent.x = 0;
		testVelocityComponent.y = -4;
		testSpriteComponent.sprite = new Sprite(new Texture("sprites/note1.png"));
		testEntity.add(testPositionComponent).add(testVelocityComponent).add(testSpriteComponent).add(testRenderableComponent);
		//----
		//Create a test entity----
		Entity testEntity2 = new Entity();
		PositionComponent testPositionComponent2 = new PositionComponent();
		VelocityComponent testVelocityComponent2 = new VelocityComponent();
		SpriteComponent testSpriteComponent2 = new SpriteComponent();
		RenderableComponent testRenderableComponent2 = new RenderableComponent();
		//TODO How does one give the components values? I'll do it like this now.
		testPositionComponent2.x = 20;
		testPositionComponent2.y = Constants.VIEWPORT_DIM[1];
		testVelocityComponent2.x = 0;
		testVelocityComponent2.y = -4;
		testSpriteComponent2.sprite = new Sprite(new Texture("sprites/note1.png"));
		testEntity2.add(testPositionComponent2).add(testVelocityComponent2).add(testSpriteComponent2).add(testRenderableComponent2);
		//----
		
		//Add all entities to engine
		engine.addEntity(testEntity);
		engine.addEntity(testEntity2);
		
	}
	/*
	public void update(){
		engine.update(deltaTime);
	}
	*/
}
