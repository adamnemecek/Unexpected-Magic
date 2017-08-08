package com.mygdx.game.gameEngine.scenes;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.IPlayer;
import com.mygdx.game.model.NoteLanes;
import com.mygdx.game.model.ScoreListener;
import com.mygdx.game.utils.Constants;

/**
 * A class that defines the properties of the heads-up display.
 * @author soflarb
 * Revised by rarvid
 * 
 * Uses: IPlayer, NoteLanes, IPlayer, IScore, ScoreListener
 * Used by: InputAction, GameScreen
 */
public class Hud {
	private final SpriteBatch batch;
	private final Stage stage;
	private final Viewport viewport;
	private final Skin skin;
	private final PlayerBox[] playerBoxes;

	private final Texture activeTexture;
	private final Texture inactiveTexture;
	private final Texture backgroundTop;
	private final Texture backgroundBot;

	private class PlayerBox extends Table implements ScoreListener{
		private final Label playerNameLabel;
		private Label playerScoreLabel;
		PlayerBox(IPlayer player) {
			super(skin);
			playerNameLabel = new Label(player.getName(), skin);
			float scale = 0.5f;
			player.getScore().addListener(this);
			playerNameLabel.setFontScale(scale);
			playerScoreLabel =  new Label("0", skin);
			playerScoreLabel.setFontScale(scale);
			this.add(playerNameLabel).row();
			this.add(playerScoreLabel);
		}
		public void newScore(int score){
			playerScoreLabel.setText(Integer.toString(score));
		}
	}

	public Hud(SpriteBatch batch, String songTitle, String bpm, List<? extends IPlayer> players){
		TextureAtlas atlas = new TextureAtlas("skins/commodore64/skin/uiskin.atlas");
		skin = new Skin(Gdx.files.internal("skins/commodore64/skin/uiskin.json"),atlas);

		viewport = new ScalingViewport(Scaling.fit, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y, new OrthographicCamera());
		stage = new Stage(viewport, batch);
		this.batch = batch;

		Label songTitleLabel = new Label(songTitle, skin);
		songTitleLabel.setFontScale(0.5f);

		Label songBPMLabel = new Label("BPM: " + bpm, skin);
		songBPMLabel.setFontScale(0.5f);

		// top table layout with song title, menu button, etc.
		Table topTable = new Table();
		stage.addActor(topTable);
		//topTable.setDebug(true, true);
		topTable.setFillParent(true);
		topTable.top();
		topTable.left();

		topTable.add().expandX();
		topTable.add(songTitleLabel).fillX().padLeft(5).padRight(5).padTop(7);
		topTable.add(songBPMLabel).fillX().padLeft(5).padRight(5).padTop(7);

		//note lanes
		this.activeTexture = new Texture("images/lanes/lane-white-bordered-33x10.png");
		this.inactiveTexture = new Texture("images/lanes/lane-black-bordered-33x10.png");

		//background
		this.backgroundTop = new Texture("images/hud-background-390x25.png");
		this.backgroundBot = new Texture("images/hud-background-390x35.png");

		// bot table layout with player boxes.
		Table botTable = new Table();
		stage.addActor(botTable);
		//botTable.setDebug(true, true);
		botTable.setFillParent(true);
		botTable.bottom();
		playerBoxes = new PlayerBox[players.size()];
		for(int i = 0; i < players.size(); i++){
			playerBoxes[i] = new PlayerBox(players.get(i));
			botTable.add(playerBoxes[i]).fillX().expandX().uniform().padBottom(2f);
		}
		Gdx.input.setInputProcessor(stage);
	}
	public Stage getStage(){
		return this.stage;
	}
	
	public void draw(){
		viewport.apply();
		drawBackground();
		stage.draw();
		drawLanes();
	}

	public void activateLane(int lane){
		NoteLanes.activateLane(lane);
	}

	public void deactivateLane(int lane){
		NoteLanes.deactivateLane(lane);
	}

	private void drawLanes(){
		batch.begin();
		for(int i  = 0; i < Constants.NUMBER_OF_LANES; i ++){
			batch.draw(getLaneTexture(i),Constants.LANE_WIDTH*i,Constants.SCORE_BOUNDS_LOWER,Constants.LANE_WIDTH,Constants.SCORE_BOUNDS_UPPER-Constants.SCORE_BOUNDS_LOWER);
		}
		batch.end();
	}
	
	private void drawBackground(){
		batch.begin();
		batch.draw(backgroundTop, 0, Constants.VIEWPORT_DIM_Y-Constants.PIANOROLL_TOP_PADDING, Constants.VIEWPORT_DIM_X, Constants.PIANOROLL_TOP_PADDING);
		batch.draw(backgroundBot, 0, 0, Constants.VIEWPORT_DIM_X, Constants.PIANOROLL_TOP_PADDING);
		batch.end();
	}
	
	private Texture getLaneTexture(int i){
		if(NoteLanes.getLaneState(i)){
			return activeTexture;
		}
		else return inactiveTexture;
	}

	public void resize(int width, int height){
		viewport.update(width, height);
	}
}
