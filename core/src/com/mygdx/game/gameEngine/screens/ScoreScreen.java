package com.mygdx.game.gameEngine.screens;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.model.IPlayer;
import com.mygdx.game.model.ScoreListener;
import com.mygdx.game.utilities.file.Constants;

/**
 * Screen that shows score after a game.
 * @author soflarb
 */
public class ScoreScreen extends AbstractScreen{
	private final List<IPlayer> players;
	private ScoreBox[] scoreBoxes;
	
	private class ScoreBox extends Table{
		private Label playerNameLabel;
		private Label playerScoreLabel;
		private Label playerStreakLabel;
		private IPlayer player;
		ScoreBox(IPlayer player) {
			this(player.getName(), Integer.toString(player.getScore().getScore()), Integer.toString(player.getScore().getStreak()), skin);
		}
		public ScoreBox(String name, String score, String streak, Skin skin) {
			super(skin);
			float scale = 0.5f;
			playerNameLabel = new Label(name, skin);
			playerNameLabel.setFontScale(scale);
			playerScoreLabel =  new Label(score, skin);
			playerScoreLabel.setFontScale(scale);
			playerStreakLabel =  new Label(streak, skin);
			playerStreakLabel.setFontScale(scale);
			this.add(playerNameLabel).row();
			this.add(playerScoreLabel).row();
			this.add(playerStreakLabel);
		}
	}
	
	protected ScoreScreen(SpriteBatch batch, List<IPlayer> players) {
		super(batch);
		this.players = players;
		// table
		Table table = new Table();
		table.setFillParent(true);
		table.setDebug(true);
		float tableScaleX = 0.6f;
		float tableScaleY = 0.6f;
		table.setTransform(true);
		table.setOrigin(Constants.VIEWPORT_DIM_X / 2, Constants.VIEWPORT_DIM_Y / 2);
		table.setScale(tableScaleX, tableScaleY);
		//table.setDebug(true, true);
		stage.addActor(table);
		//float fontScale = 0.5f;
		//main menu button
		TextButton mainMenuButton = new TextButton("Main Menu", skin);
		//mainMenuButton.getLabel().setFontScale(fontScale);
		mainMenuButton.addListener(
			(Event event) -> {
				if(!(event instanceof InputEvent)) return false;
				InputEvent evt = (InputEvent) event;
				if(evt.getType() != InputEvent.Type.touchDown) return false;
				changeToScreen(new MainMenuScreen(batch));
				return true;
				
			}
		);
		
		// table layout
		scoreBoxes = new ScoreBox[players.size()];
        Label titleLabel = new Label("Results: ", skin);
		table.add(titleLabel).fillX().colspan(scoreBoxes.length);
		table.row();
		ScoreBox labelBox = new ScoreBox("Name: ","Score: ","Streak: ",skin);
		table.add(labelBox).fillX().expandX().uniform();
        for(int i = 0; i < players.size(); i++){
        	scoreBoxes[i] = new ScoreBox(players.get(i));
        	table.add(scoreBoxes[i]).uniform().padLeft(2f).padRight(2f);
        }
        table.row();
		table.add(mainMenuButton).colspan(scoreBoxes.length).center();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		stage.act();
		stage.draw();
		
	}
}
