package com.mygdx.game.gameEngine.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.mygdx.game.model.Constants;
import com.mygdx.game.UnexpectedMagic;
import com.mygdx.game.gameEngine.managers.EntityManager;
import com.mygdx.game.gameEngine.systems.MovementSystem;
import com.mygdx.game.gameEngine.systems.RenderSystem;

/**
* Screen that contains the game (in-game).
*/
public class GameScreen extends ScreenAdapter{
	
	final UnexpectedMagic game;
	Engine engine;
	OrthographicCamera inGameCam;
	ScalingViewport viewport;
	EntityManager entityManager;
	SpriteBatch batch;
	Texture backgroundTexture;
	
	public GameScreen(final UnexpectedMagic game){
		this.game = game;
		engine = game.engine;
		batch = game.batch;
		inGameCam = new OrthographicCamera();
		inGameCam.setToOrtho(false);
		
		backgroundTexture = new Texture("images/textureCheckedBlue16x16.png");
		backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		
		viewport = new ScalingViewport(Scaling.fit, Constants.VIEWPORT_DIM[0], Constants.VIEWPORT_DIM[1], inGameCam);
		viewport.apply(true);
		
		engine.addSystem(new MovementSystem()); //TODO
		engine.addSystem(new RenderSystem(batch));
		System.out.println("GameScreen. Engine's systems: " + engine.getSystems().toString());
		
		entityManager = new EntityManager(engine, batch);
		
	}
	
	public void update (float delta) {
		batch.begin();
		//Print the delta time on screen
		game.font.draw(game.batch, "DYNAMIC STUFF Delta: "+ delta, Constants.VIEWPORT_DIM[0]/4, Constants.VIEWPORT_DIM[1]/2 - 40);
		batch.end();
		engine.update(delta);

		}

	@Override
	public void render(float delta){
		game.batch.setProjectionMatrix(inGameCam.combined);
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(backgroundTexture, 0, 0, Constants.VIEWPORT_DIM[0], Constants.VIEWPORT_DIM[0], 0, 10, 10, 0);
		game.font.draw(game.batch, "STATIC STUFF YOU'RE IN GAME YAY.", Constants.VIEWPORT_DIM[0]/4, Constants.VIEWPORT_DIM[1]/2);
		game.font.draw(game.batch, "STATIC STUFF look at the cutes texture in everywhere", Constants.VIEWPORT_DIM[0]/4, Constants.VIEWPORT_DIM[1]/2 - 20);
		game.batch.end();
		
		update(delta);
		
	}
	@Override
	public void resize(int width, int height){
		System.out.println("GameScreen. viewport.update()");
		viewport.update(width, height);
	}
	
}
