package com.mygdx.game.gameEngine.scenes;

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
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.NoteLanes;
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
	
	private Label songNameLabel;
	private Label songBPMLabel;
	private Label scoreLabel;
	
	private TextButton menuButton;
	
	private NoteLanes noteLanes;
	private Texture activeTexture;
	private Texture inactiveTexture;
	
	public Hud(SpriteBatch batch, Score score, NoteLanes noteLanes){
		//atlas = new TextureAtlas("skins/Mother_Skin/terramotherui/terra-mother-ui.atlas");
		atlas = new TextureAtlas("skins/commodore64/skin/uiskin.atlas");
		//skin = new Skin(Gdx.files.internal("skins/Mother_Skin/terramotherui/terra-mother-ui.json"),atlas);
		skin = new Skin(Gdx.files.internal("skins/commodore64/skin/uiskin.json"),atlas);
		
		camera = new OrthographicCamera();
		viewport = new ScalingViewport(Scaling.fit, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y, camera);
		stage = new Stage(viewport, batch);
		this.batch = batch;
		this.score = score;
		
		songNameLabel = new Label("songNameLabel", skin);
		songNameLabel.setFontScale(0.5f);
		
		songBPMLabel = new Label("songBPMLabel", skin);
		songBPMLabel.setFontScale(0.5f);
		
		scoreLabel = new Label("SCORE " + score, skin);
		scoreLabel.setFontScale(0.5f);
		
		menuButton = new TextButton("Menu", skin);
		menuButton.setTransform(true);
		menuButton.setScale(0.5f);
		//("%03d, someNumberVariable") for displaying 3 digits of it in a label or something
        
		//table layout
        Table table = new Table();
        table.setDebug(true, true);
		table.setFillParent(true);
		table.top();
        table.add(menuButton).fillX();
		table.add(songNameLabel).fillX();//.expandX();
		table.add(songBPMLabel).fillX();//.expandX();
		//table.add(scoreLabel).expandX();
		stage.addActor(table);
		
		this.noteLanes = noteLanes;
		this.activeTexture = new Texture("images/lanes/Blue.png");
        this.inactiveTexture = new Texture("images/lanes/Red.png");
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
