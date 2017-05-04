package com.mygdx.game.gameEngine.screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
public class MainMenuScreen extends AbstractScreen{
	
	//GAME STUFF
	
	OrthographicCamera guiCam;
	Engine engine;
	private SongList songList;
	private ArrayList<Player> players;
    private Stage stage;
	private Viewport viewport;
	
	//BUTTONS
	private TextureAtlas atlas;
	private Skin skin;
	private ButtonGroup<TextButton> buttongroup;
	private String [] menuItems;
	private int menuItemSelected;
	
	private TextButton playButton;
	private TextButton optionButton;
	private TextButton animationButton;
	private TextButton exitButton;
	
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
        mainTable.setFillParent(true);        
       //Creates button Style
       TextButtonStyle textButtonStyle = new TextButtonStyle();
       textButtonStyle.font = skin.getFont("giygas");
       textButtonStyle.checked = skin.getDrawable("window");
       textButtonStyle.down = skin.getDrawable("window-player");
       
       float fontScale = (float) 1;
       float buttonScale = (float) 1;
       //PLAY BUTTON
       	playButton = new TextButton("Play", textButtonStyle);
       	playButton.setTransform(true);
       	playButton.getLabel().setFontScale(fontScale);
       	playButton.setScale(buttonScale);
        //ANIMATION BUTTON 
        animationButton = new TextButton("Animation",textButtonStyle);
        animationButton.setTransform(true);
        animationButton.getLabel().setFontScale(fontScale);
        animationButton.setScale(buttonScale);
        //EXIT BUTTON
        exitButton = new TextButton("Exit",textButtonStyle);
        exitButton.setTransform(true);
        exitButton.getLabel().setFontScale(fontScale);
        exitButton.setScale(buttonScale);
        //OPTION BUTTON
        optionButton = new TextButton("Options",textButtonStyle);
        optionButton.setTransform(true);
        optionButton.getLabel().setFontScale(fontScale);
        optionButton.setScale(buttonScale);
        //BUTTON GROUP
        buttongroup = new ButtonGroup<TextButton>(playButton, animationButton, exitButton, optionButton);
        buttongroup.setMaxCheckCount(1);
        buttongroup.setMinCheckCount(0);
        buttongroup.setChecked(menuItems[0]);
        
        addButtonListeners();
        
        float width = Gdx.graphics.getWidth()-Gdx.graphics.getHeight();
        float height = Gdx.graphics.getHeight()/8;
        //Add buttons to table  
        mainTable.add(playButton).size(width, height).row();
        mainTable.add(optionButton).size(width, height).row();
        mainTable.add(animationButton).size(width, height).row();
        mainTable.add(exitButton).size(width, height);
        //Add table to stage
        stage.addActor(mainTable);
    }
	
	public void update(){
		
	}
	
	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		game.batch.setProjectionMatrix(guiCam.combined);
		game.batch.begin();
		game.batch.end();
		
		 stage.act();
	     stage.draw();
	}
	
	@Override
    public void dispose() {
        skin.dispose();
		atlas.dispose();
       
    }
	
	@Override
	public void resize(int width, int height){
		viewport.update(width, height);
		guiCam.position.set(guiCam.viewportWidth / 2, guiCam.viewportHeight / 2, 0);
        guiCam.update();
	}
	
	@Override
	public void pause(){
		
	}
	
	private void addButtonListeners(){
		playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	playButtonPushed();
            }
            
        });
        		
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	exitButtonPushed();
            }
            
            
        });
        
        animationButton.addListener(new ClickListener(){
        	@Override
        	public void clicked(InputEvent event, float x, float y){
        		        	animationButtonPushed();
        	}
        });
        
        optionButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	optionButtonPushed();
            }
        });
        
        stage.addListener(new InputListener(){
        	
        	@Override
        	public boolean keyDown(InputEvent event, int keycode){
        		if(keycode == Input.Keys.UP){
        			menuItemSelected = (menuItemSelected+3) % 4;
        			buttongroup.setChecked(menuItems[menuItemSelected]);
        		}
        		else if (keycode == Input.Keys.DOWN){
        			menuItemSelected = (menuItemSelected+1) % 4;
        			buttongroup.setChecked(menuItems[menuItemSelected]);
        		}
        		
        		else if (keycode == Input.Keys.ENTER){       			
        			buttonPushed();
        		}
        		
        		return true;
        	}
        	@Override
        	public boolean mouseMoved(InputEvent event, float x,  float y){
        		if (playButton.isOver()){
        			menuItemSelected = 0;
        			buttongroup.setChecked(menuItems[menuItemSelected]);
        		}
        		else if (optionButton.isOver()){
        			menuItemSelected = 1;
        			buttongroup.setChecked(menuItems[menuItemSelected]);
        		}
        		else if (animationButton.isOver()){
        			menuItemSelected = 2;
        			buttongroup.setChecked(menuItems[menuItemSelected]);
        		}
        		else if (exitButton.isOver()){
        			menuItemSelected = 3;
        			buttongroup.setChecked(menuItems[menuItemSelected]);
        		}
        		return false;
        	}
        	
        });
	}
	
    public void playButtonPushed (){
    	try {
    		Iterator<String> it = songList.songs().iterator();
    		//it.next();
    		//it.next();
    		//it.next();
			game.setScreen(new GameScreen(game, songList.getSong(it.next()), players));
			//song takes the text in the text doc as a String
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void optionButtonPushed(){
		game.setScreen(new OptionsScreen(game));
    }
    
    public void animationButtonPushed(){
    	
    }
    
    public void exitButtonPushed(){
    	 Gdx.app.exit();
    }
    
    public void buttonPushed(){
    	switch(menuItemSelected){
    		
    	case 0: playButtonPushed();
    	break;
    	
    	case 1: optionButtonPushed();
    	break;
    	
    	case 2: animationButtonPushed();
    	break;
    	
    	case 3: exitButtonPushed();
    	break;
    		
    	}
    }
}
