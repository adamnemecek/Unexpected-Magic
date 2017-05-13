package com.mygdx.game.gameEngine.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.Constants;
import com.mygdx.game.model.Score;
import com.sun.corba.se.impl.orbutil.closure.Constant;

public class Hud {
	public Stage stage;
	private OrthographicCamera camera;
	private Viewport viewport;
	private Score score;
	private TextureAtlas atlas;
	private Skin skin;
	
	private Label songNameLabel;
	private Label songBPMLabel;
	private Label scoreLabel;
	
	private TextButton menuButton;
	
	public Hud(SpriteBatch spriteBatch, Score score){
		atlas = new TextureAtlas("skins/Mother_Skin/terramotherui/terra-mother-ui.atlas");
		skin = new Skin(Gdx.files.internal("skins/Mother_Skin/terramotherui/terra-mother-ui.json"),atlas);
		
		camera = new OrthographicCamera();
		viewport = new ScalingViewport(Scaling.fit, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y, camera);
		stage = new Stage(viewport, spriteBatch);
		
		this.score = score;
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		//scoreLabel = new Label("scoreLabel", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		//songNameLabel = new Label("songNameLabel", skin);
		//songBPMLabel = new Label("songBPMLabel", skin);
		scoreLabel = new Label("SCORE " + score, skin); //TODO dynamic score
		menuButton = new TextButton("Menu", skin);
		//("%03d, someNumberVariable") for displaying 3 digits of it in a label or something
		table.add(songNameLabel).expandX().padTop(5);
		table.add(songBPMLabel).expandX().padTop(5);
		table.add(scoreLabel).expandX().padTop(5);
		stage.addActor(table);
		
	}

	public void setScoreLabel(){
	    this.scoreLabel.setText("SCORE " + score.getScore());
    }

}
