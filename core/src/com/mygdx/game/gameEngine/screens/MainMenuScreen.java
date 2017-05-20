package com.mygdx.game.gameEngine.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.UnexpectedMagic;
import com.mygdx.game.gameEngine.managers.AnimationManager;

/**
 * Screen that contains the main menu.
 */
public class MainMenuScreen extends AbstractScreen {


	private AnimationManager feffeAnim;
	private AnimationManager frodoAnim;

	// BUTTONS
	private ButtonGroup<TextButton> buttongroup;
	private String[] menuItems;
	private int menuItemSelected;
	

	public MainMenuScreen(final SpriteBatch batch) {
		super(batch);
		

		menuItemSelected = 0;
		menuItems = new String[] { "New game", "Options", "Animation", "Exit" };
		feffeAnim = new AnimationManager(new Texture(Gdx.files.internal("animationSheets/feffe.png")),
				batch, 1, 6, 275, -32, 200, 200, 0.1f );
		frodoAnim = new AnimationManager(new Texture(Gdx.files.internal("animationSheets/frodo.png")),
				batch, 1, 6, 210, -10, 200, 200, 0.12f );
	}

	@Override
	public void show() {
		// Create Table
		Table table = new Table();
		table.setFillParent(true);
		table.setDebug(true, true);
		// Add table to stage
		stage.addActor(table);

		// Creates button Style
		//TextButtonStyle textButtonStyle = new TextButtonStyle();
		//textButtonStyle.font = skin.getFont("font");
		//textButtonStyle.checked = skin.getDrawable("window");
		//textButtonStyle.down = skin.getDrawable("window-player");

		//float fontScale = 1f;
		//float buttonScale = 1f;

		TextButton newgameButton = new TextButton(menuItems[0], skin);
		TextButton optionButton = new TextButton(menuItems[1], skin);
		TextButton animationButton = new TextButton(menuItems[2], skin);
		TextButton exitButton = new TextButton(menuItems[3], skin);
		
		// Add buttons to table
		/*
		mainTable.add(newgameButton).size(width, height).row();
		mainTable.add(optionButton).size(width, height).row();
		mainTable.add(animationButton).size(width, height).row();
		mainTable.add(exitButton).size(width, height);
		*/
		table.add(newgameButton).fillX().uniformX();
		table.row();//.pad(-30,0,-30,0);
		table.add(optionButton).fillX().uniformX();
		table.row();
		table.add(animationButton).fillX().uniformX();
		table.row();
		table.add(exitButton).fillX().uniformX();
		
		// BUTTON GROUP
		buttongroup = new ButtonGroup<TextButton>(newgameButton, optionButton, animationButton, exitButton);
		buttongroup.setMaxCheckCount(1);
		buttongroup.setMinCheckCount(0);
		buttongroup.setChecked(menuItems[0]);
		
		/*for(TextButton btn : buttongroup.getButtons()) {
			btn.setTransform(true);
			btn.getLabel().setFontScale(fontScale);
			btn.setScale(buttonScale);
		}*/
		/*
		addButtonListeners();

	}

	// TODO should be done in a more elegant way
	private void addButtonListeners() {
		*/
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

		animationButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				animationButtonPushed();
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
					menuItemSelected = (menuItemSelected + 3) % 4;
					buttongroup.setChecked(menuItems[menuItemSelected]);
				} else if (keycode == Input.Keys.DOWN) {
					menuItemSelected = (menuItemSelected + 1) % 4;
					buttongroup.setChecked(menuItems[menuItemSelected]);
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
					buttongroup.setChecked(menuItems[menuItemSelected]);
				} else if (optionButton.isOver()) {
					menuItemSelected = 1;
					buttongroup.setChecked(menuItems[menuItemSelected]);
				} else if (animationButton.isOver()) {
					menuItemSelected = 2;
					buttongroup.setChecked(menuItems[menuItemSelected]);
				} else if (exitButton.isOver()) {
					menuItemSelected = 3;
					buttongroup.setChecked(menuItems[menuItemSelected]);
				}
				return false;
			}

		});
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		feffeAnim.render(); // render animation TODO should perhaps be automated
		frodoAnim.render();
		batch.end();

		stage.act();
		stage.draw();
	}

	public void newgameButtonPushed() {
		notifyScreenChange(new NewgameScreen(batch));
	}

	public void optionButtonPushed() {
		notifyScreenChange(new OptionsScreen(batch));
	}

	public void animationButtonPushed() {

	}

	public void exitButtonPushed() {
		Gdx.app.exit();
	}

	public void buttonPushed() {
		switch (menuItemSelected) {

		case 0:
			newgameButtonPushed();
			break;

		case 1:
			optionButtonPushed();
			break;

		case 2:
			animationButtonPushed();
			break;

		case 3:
			exitButtonPushed();
			break;

		}
	}
}
