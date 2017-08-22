package com.mygdx.game.gameEngine.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.gameEngine.managers.AnimatedImage;
import com.mygdx.game.gameEngine.sound.MusicPlayer;
import com.mygdx.game.utils.Constants;

/**
 * Screen that contains the main menu.
 * @author soflarb
 * Revised by rastom and car0b1nius
 * 
 * Uses: OptionsScreen, NewGameScreen, AbstractScreen, AnimationManager, MusicPlayer
 * 
 * Used by: TitleScreen
 */
public class MainMenuScreen extends AbstractScreen {

	private Texture background = new Texture("images/UnexpectedMagicBackground6.png");
	private ArrayList<AnimatedImage> animations;

	// BUTTONS
	private ButtonGroup<TextButton> buttonGroup;
	private String[] menuItems;
	private int menuItemSelected;

	public MainMenuScreen(final SpriteBatch batch) {
		super(batch);
		MusicPlayer.getInstance().play("main-theme");
		
		menuItemSelected = 0;
		menuItems = new String[] { "New game", "Options", "Exit" };
		
	// ANIMATIONS
		AnimatedImage djblookAnim = new AnimatedImage(new Texture(Gdx.files.internal("animationSheets/napstablook.png")),
				batch, 1, 4, Math.round(Constants.VIEWPORT_DIM_X/2 - 52), Math.round(Constants.VIEWPORT_DIM_Y - 105), 110, 104, 0.1f);
		AnimatedImage feffeAnim = new AnimatedImage(new Texture(Gdx.files.internal("animationSheets/feffe.png")),
				batch, 1, 6, 275, -32, 200, 200, 0.1f );
		AnimatedImage frodoAnim = new AnimatedImage(new Texture(Gdx.files.internal("animationSheets/frodo.png")),
				batch, 1, 6, 210, -10, 200, 200, 0.12f );
		AnimatedImage nekkiAnim = new AnimatedImage(new Texture(Gdx.files.internal("animationSheets/nekki.png")),
				batch, 1, 1, 0, -30, 200, 200, 0.1f );
		animations = new ArrayList<>();
		animations.add(djblookAnim);
		animations.add(feffeAnim);
		animations.add(frodoAnim);
		animations.add(nekkiAnim);
		
//	}

//	@Override
//	public void show() {
		// Create Table
		Table table = new Table();
		table.setFillParent(true);
		//table.setDebug(true, true);
		// Add table to stage
		stage.addActor(table);

		// Creates button Style
		//TextButtonStyle textButtonStyle = new TextButtonStyle();
		//textButtonStyle.font = skin.getFont("font");
		//textButtonStyle.checked = skin.getDrawable("window");
		//textButtonStyle.down = skin.getDrawable("window-player");

		//float fontScale = 1f;

		TextButton newgameButton = new TextButton(menuItems[0], skin);
//		newgameButton.setTransform(true);
//		newgameButton.scaleBy(buttonScale);
		TextButton optionButton = new TextButton(menuItems[1], skin);
//		optionButton.setTransform(true);
//		optionButton.scaleBy(buttonScale);
		TextButton exitButton = new TextButton(menuItems[2], skin);
//		exitButton.setTransform(true);
//		exitButton.scaleBy(buttonScale);
		
		// table layout
		float tableScaleX = 0.9f;
		float tableScaleY = 0.8f;
		table.setTransform(true);
		table.setOrigin(Constants.VIEWPORT_DIM_X / 2, Constants.VIEWPORT_DIM_Y / 2);
		table.setScale(tableScaleX, tableScaleY);
		//table.add(new Label(" \n ",skin)); //this is called silvertejp
		table.row();
		table.add(newgameButton).fillX().uniformX().padTop(30);
		table.row();
		table.add(optionButton).fillX().uniformX();
		table.row();
		table.add(exitButton).fillX().uniformX();
		
		// BUTTON GROUP
		buttonGroup = new ButtonGroup<TextButton>(newgameButton, optionButton, exitButton);
		buttonGroup.setMaxCheckCount(1);
		buttonGroup.setMinCheckCount(0);
		buttonGroup.setChecked(menuItems[0]);
		
		newgameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				newgameButtonPushed();
			}

		});

		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				exitButtonPushed();
			}

		});

		optionButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				optionButtonPushed();
			}
		});

		stage.addListener(new InputListener() {

			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if (keycode == Input.Keys.UP) {
					menuItemSelected = (menuItemSelected + 2) % 3;
					buttonGroup.setChecked(menuItems[menuItemSelected]);
				} else if (keycode == Input.Keys.DOWN) {
					menuItemSelected = (menuItemSelected + 1) % 3;
					buttonGroup.setChecked(menuItems[menuItemSelected]);
				}

				else if (keycode == Input.Keys.ENTER) {
					buttonPushed();
				}

				return true;
			}

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {
				if (newgameButton.isOver()) {
					menuItemSelected = 0;
					buttonGroup.setChecked(menuItems[menuItemSelected]);
				} else if (optionButton.isOver()) {
					menuItemSelected = 1;
					buttonGroup.setChecked(menuItems[menuItemSelected]);
				} else if (exitButton.isOver()) {
					menuItemSelected = 2;
					buttonGroup.setChecked(menuItems[menuItemSelected]);
				}
				return false;
			}

		});
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(background, 0, 0, Constants.VIEWPORT_DIM_X, Constants.VIEWPORT_DIM_Y);
		batch.end();
		for(AnimatedImage animation : animations){ 
			animation.draw();
		}
		stage.act();
		stage.draw();
	}

	private void newgameButtonPushed() {
		changeToScreen(new NewgameScreen(batch));
	}

	private void optionButtonPushed() {
		changeToScreen(new OptionsScreen(batch));
	}

	private void exitButtonPushed() {
		Gdx.app.exit();
	}

	private void buttonPushed() {
		switch (menuItemSelected) {

		case 0:
			newgameButtonPushed();
			break;

		case 1:
			optionButtonPushed();
			break;

		case 3:
			exitButtonPushed();
			break;

		}
	}
}
