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
	private OrthographicCamera camera;
	private Viewport viewport;
	private Score score;
	private TextureAtlas atlas;
	private Skin skin;
	
	private Label songTitleLabel;
	private Label songBPMLabel;
	private Label scoreLabel;
	
	private TextButton menuButton;
	
	private NoteLanes noteLanes;
	private Texture activeTexture;
	private Texture inactiveTexture;
	
	//player boxes at the bottom of the screen
	private PlayerBox[] playerBoxes;
	// player1Box = {player1.name; player1.score;
	
	private class PlayerBox extends Table{
		private Label playerNameLabel;
		private Label playerScoreLabel;
		PlayerBox(Player player) {
			super(skin);
			playerNameLabel = new Label(player.getName(), skin);
			playerScoreLabel =  new Label("0", skin);
			this.add(playerNameLabel).row();
			this.add(playerScoreLabel);
		}
	}
	
	public Hud(SpriteBatch batch, Score score, NoteLanes noteLanes, String songTitle, String bpm, List<Player> players){
		atlas = new TextureAtlas("skins/commodore64/skin/uiskin.atlas");
		skin = new Skin(Gdx.files.internal("skins/commodore64/skin/uiskin.json"),atlas);
		
		camera = new OrthographicCamera();
		viewport = new ScalingViewport(Scaling.fit, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y, camera);
		stage = new Stage(viewport, batch);
		this.batch = batch;
		this.score = score;
		
		songTitleLabel = new Label(songTitle, skin);
		songTitleLabel.setFontScale(0.8f);
		
		songBPMLabel = new Label("BPM: " + bpm, skin);
		songBPMLabel.setFontScale(0.8f);
		
		scoreLabel = new Label("SCORE " + score, skin);
		scoreLabel.setFontScale(0.8f);
		
		menuButton = new TextButton("Menu", skin);
		menuButton.getLabel().setFontScale(0.5f);
//		menuButton.setTransform(true);
//		menuButton.setScale(0.5f);
		//("%03d, someNumberVariable") for displaying 3 digits of it in a label or something
        
	 	// top table layout with song title, menu button, etc.
        Table topTable = new Table();
        stage.addActor(topTable);
        topTable.setDebug(true, true);
		topTable.setFillParent(true);
		topTable.top();
		topTable.left();
		
        topTable.add(menuButton).width(Value.percentWidth(.15f, topTable)).height(Value.percentHeight(.1f, topTable)).padRight(5);
		topTable.add().expandX();
        topTable.add(songTitleLabel).fillX().padLeft(5).padRight(5);
		topTable.add(songBPMLabel).fillX().padLeft(5).padRight(5);
		
		//note lanes
		this.noteLanes = noteLanes;
		this.activeTexture = new Texture("images/lanes/Blue.png");
        this.inactiveTexture = new Texture("images/lanes/Red.png");
        
        // bot table layout with player boxes.
        Table botTable = new Table();
        stage.addActor(botTable);
        botTable.setDebug(true, true);
        botTable.setFillParent(true);
        botTable.bottom();
        playerBoxes = new PlayerBox[players.size()];
        for(int i = 0; i < players.size(); i++){
        	playerBoxes[i] = new PlayerBox(players.get(i));
        	botTable.add(playerBoxes[i]).fillX().expandX();
        }
	}

	public void draw(){
		viewport.apply();
		stage.draw();
		drawLanes();
	}
	public void setScoreLabel(){
	    this.scoreLabel.setText("SCORE " + score.getScore());
    }
	
	public void activateLane(int lane){
		noteLanes.activateLane(lane);
	}
	
	public void deactivateLane(int lane){
		noteLanes.deactivateLane(lane);
	}
	private void drawLanes(){
		batch.begin();
		for(int i  = 0; i < Constants.NUMBER_OF_LANES; i ++){
			batch.draw(getLaneTexture(i),Constants.LANE_WIDTH*i,Constants.SCORE_BOUNDS_LOWER,Constants.LANE_WIDTH,Constants.SCORE_BOUNDS_UPPER-Constants.SCORE_BOUNDS_LOWER);
		}
		batch.end();
	}
	public Texture getLaneTexture(int i){
	    if(noteLanes.getLaneState(i)){
	        return activeTexture;
        }
        else return inactiveTexture;
    }

    public NoteLanes getNoteLanes(){
	    return noteLanes;
    }
    public void resize(int width, int height){
		viewport.update(width, height);
	}
}
