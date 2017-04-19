package com.mygdx.game.gameEngine.screens;

import java.io.IOException;
import java.util.ArrayList;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.Constants;
import com.mygdx.game.model.Player;
import com.mygdx.game.services.file.SongList;
import com.mygdx.game.UnexpectedMagic;

/**
* Screen that contains the main menu.
*/
public class MainMenuScreen extends ScreenAdapter{
	
	final UnexpectedMagic game;
	OrthographicCamera guiCam;
	Engine engine;
	private SongList songList;
	private ArrayList<Player> players;
	
	private int menuItemSelected;
    private String [] menuItems; 
    private Stage stage;
	private ScalingViewport viewport;
	
	private TextureAtlas atlas;
	private Skin skin;

	public MainMenuScreen(final UnexpectedMagic game){
		this.game = game;
		engine = game.engine;
		guiCam = new OrthographicCamera(Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y);
		guiCam.setToOrtho(false, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y);
		
		songList = new SongList();
		players = new ArrayList<>();
		//TEST PLAYERS
		players.add(new Player("Testplayer1", null, null));
		players.add(new Player("Testplayer2", null, null));
		
		menuItemSelected = 0;
	    menuItems =  new String [] {"Play","Options","Animation","Exit"};
	    viewport = new ScalingViewport(Scaling.fit,Constants.VIEWPORT_DIM_X,Constants.VIEWPORT_DIM_Y, guiCam);
	    viewport.apply();
	    stage = new Stage(viewport, game.batch);
	    atlas = new TextureAtlas("skins/Mother_Skin/terramotherui/terra-mother-ui.atlas");
	    skin = new Skin(Gdx.files.internal("skins/Mother_Skin/terramotherui/terra-mother-ui.json"),atlas);
	    Gdx.input.setInputProcessor(stage);
	}
	
    @Override
    public void show() {
    	
    	
    	
        //Create Table
        Table mainTable = new Table();
        //Set table to fill stage
        mainTable.setFillParent(true);
        
       //Creates button Style
       TextButtonStyle textButtonStyle = new TextButtonStyle();
       textButtonStyle.font = skin.getFont("giygas");
       textButtonStyle.over = skin.getDrawable("smash");
        
        //PLAY BUTTON
        TextButton playButton = new TextButton("Play", textButtonStyle);
        //playButton.getLabel().setFontScale(3/2,3/2);
       
        
        //ANIMATION BUTTON 
        TextButton animationButton = new TextButton("Animation",textButtonStyle);
        //animationButton.getLabel().setFontScale(3/2,3/2);
        
        //EXIT BUTTON
        TextButton exitButton = new TextButton("Exit",textButtonStyle);
        //exitButton.getLabel().setFontScale(3/2,3/2);
        
        //OPTION BUTTON
        TextButton optionButton = new TextButton("Options",textButtonStyle);
        //optionButton.getLabel().setFontScale(3/2,3/2);
       
        
        ButtonGroup bG = new ButtonGroup(playButton, animationButton, exitButton, optionButton);
        bG.setMaxCheckCount(1);
        bG.setMinCheckCount(0);
        bG.setChecked(menuItems[0]);
        
        //Add listeners to buttons
        stage.addListener(new InputListener(){
        	@Override
        	public boolean keyDown(InputEvent event, int keycode){
        		if(keycode == Input.Keys.UP){
        			menuItemSelected = (menuItemSelected+3) % 4;
        			bG.setChecked(menuItems[menuItemSelected]);
        		}
        		else if (keycode == Input.Keys.DOWN){
        			menuItemSelected = (menuItemSelected+1) % 4;
        			bG.setChecked(menuItems[menuItemSelected]);
        		}
        		
        		else if (keycode == Input.Keys.ENTER){
        	
        			//buttonPushed();
        		}
        		
        		return true;
        	}
        });
        
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //playButtonPushed();
            	System.out.println("PLAY");
            	try {
    				game.setScreen(new GameScreen(game, songList.getSong(songList.songs().iterator().next()), players)); //TODO The Song argument is null!
    				//song takes the text in the text doc as a String
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }
        });
        		
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	//exitButtonPushed();
            	System.out.println("HELLO");
            }
        });
        animationButton.addListener(new ClickListener(){
        	@Override
        	public void clicked(InputEvent event, float x, float y){
        		//animationButtonPushed();
        		System.out.println("HELLO");
        	}
        });
        
        optionButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	
            }
        });
        	
        
        
        // listener for option button
      
        //Add buttons to table
        mainTable.add(playButton);
        mainTable.row();
        mainTable.add(optionButton);
        mainTable.row();
        mainTable.add(animationButton);
        mainTable.row();
        mainTable.add(exitButton);
        
        //Add table to stage
        
        stage.addActor(mainTable);
    }
	
	public void update(){
		
	}
	/*
	public void draw(){
		
	}
	*/
	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		game.batch.setProjectionMatrix(guiCam.combined);
		game.batch.begin();
		//game.font.draw(game.batch, songList.songs().toString(), Constants.VIEWPORT_DIM_X/15, Constants.VIEWPORT_DIM_Y - 10);
		//game.font.draw(game.batch, "MAIN MENU TEST", Constants.VIEWPORT_DIM_X/2, Constants.VIEWPORT_DIM_Y/2);
		//game.font.draw(game.batch, "PRESS THE ANY KEY", Constants.VIEWPORT_DIM_X/2, Constants.VIEWPORT_DIM_Y/2 - 100);
		game.batch.end();
		
		 stage.act();
	     stage.draw();

		/*if(Gdx.input.isKeyPressed(Keys.ANY_KEY)){
			try {
				game.setScreen(new GameScreen(game, songList.getSong(songList.songs().iterator().next()), players)); //TODO The Song argument is null!
				//song takes the text in the text doc as a String
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dispose();
		}*/
	}
	
	@Override
    public void dispose() {
        skin.dispose();
		atlas.dispose();
        
        //atlas.dispose();
    }
	
	@Override
	public void resize(int width, int height){
		viewport.update(width, height);
	}
	
	@Override
	public void pause(){
		
	}
	
}
