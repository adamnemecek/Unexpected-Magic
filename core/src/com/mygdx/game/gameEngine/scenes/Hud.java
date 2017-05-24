package com.mygdx.game.gameEngine.scenes;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.NoteLanes;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Score;
import com.mygdx.game.utilities.file.Constants;

/**
 * A class that defines the properties of the heads-up display.
 * @author soflarb
 * 
 */
public class Hud {
	private SpriteBatch batch;
	public Stage stage;
	private Viewport viewport;
	private Score score;
	private Skin skin;
	
	private Label scoreLabel;
	

	private Texture activeTexture;
	private Texture inactiveTexture;
	private Texture backgroundTop;
	private Texture backgroundBot;

	private class PlayerBox extends Table{
		private Label playerNameLabel;
		private Label playerScoreLabel;
		PlayerBox(Player player) {
			super(skin);
			playerNameLabel = new Label(player.getName(), skin);
			float scale = 0.5f;
			playerNameLabel.setFontScale(scale);
			playerScoreLabel =  new Label("0", skin);
			playerScoreLabel.setFontScale(scale);
			this.add(playerNameLabel).row();
			this.add(playerScoreLabel);
		}
	}
	
	public Hud(SpriteBatch batch, String songTitle, String bpm, List<Player> players){
		TextureAtlas atlas = new TextureAtlas("skins/commodore64/skin/uiskin.atlas");
		skin = new Skin(Gdx.files.internal("skins/commodore64/skin/uiskin.json"),atlas);

		viewport = new ScalingViewport(Scaling.fit, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y, new OrthographicCamera());
		stage = new Stage(viewport, batch);
		this.batch = batch;
		this.score = new Score();
		
		Label songTitleLabel = new Label(songTitle, skin);
		songTitleLabel.setFontScale(0.8f);
		
		Label songBPMLabel = new Label("BPM: " + bpm, skin);
		songBPMLabel.setFontScale(0.8f);
		
		scoreLabel = new Label("SCORE " + score, skin);
		scoreLabel.setFontScale(0.8f);

		TextButton pauseButton = new TextButton("Menu", skin);
		pauseButton.getLabel().setFontScale(0.5f);
		pauseButton.addListener((Event event) -> {
			if(!(event instanceof InputEvent)) return false;
			InputEvent evt = (InputEvent) event;
			if(evt.getType() != InputEvent.Type.touchDown) return false;
			//TODO implement pause
			//notify listeners? In this case I think gamescreen should be listening,
			// and it should run it's method pauseGame() when it knows used clicked this hud button
			System.out.println("Player clicked pause"); //TODO doesn't work?
			return true;
		}
				);
        
	 	// top table layout with song title, menu button, etc.
        Table topTable = new Table();
        stage.addActor(topTable);
        topTable.setDebug(true, true);
		topTable.setFillParent(true);
		topTable.top();
		topTable.left();
		
        topTable.add(pauseButton).width(Value.percentWidth(.15f, topTable)).height(Value.percentHeight(.1f, topTable)).padRight(5);
		topTable.add().expandX();
        topTable.add(songTitleLabel).fillX().padLeft(5).padRight(5);
		topTable.add(songBPMLabel).fillX().padLeft(5).padRight(5);
		
		//note lanes
		this.activeTexture = new Texture("images/lanes/lane-white-bordered-33x10.png");
        this.inactiveTexture = new Texture("images/lanes/lane-black-bordered-33x10.png");
        
        //background
        this.backgroundTop = new Texture("images/hud-background-390x25.png");
        this.backgroundBot = new Texture("images/hud-background-390x35.png");
        
        // bot table layout with player boxes.
        Table botTable = new Table();
        stage.addActor(botTable);
        botTable.setDebug(true, true);
        botTable.setFillParent(true);
        botTable.bottom();
		PlayerBox[] playerBoxes = new PlayerBox[players.size()];
        for(int i = 0; i < players.size(); i++){
        	playerBoxes[i] = new PlayerBox(players.get(i));
        	botTable.add(playerBoxes[i]).fillX().expandX().uniform().padBottom(2f);
        }
	}

	public void draw(){
		viewport.apply();
		drawBackground();
		stage.draw();
		drawLanes();
	}
	public void setScoreLabel(){
	    this.scoreLabel.setText("SCORE " + score.getScore());
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
