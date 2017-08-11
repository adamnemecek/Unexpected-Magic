package com.mygdx.game.gameEngine.screens;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.model.IPlayer;
import com.mygdx.game.model.song.ISong;
import com.mygdx.game.utils.Constants;

/**
 * Screen that shows score after a game.
 * @author soflarb
 * 
 * Uses: AbstractScreen, IPlayer, ISong, IScore
 * 
 * Used by: GameScreen
 */
public class ScoreScreen extends AbstractScreen{
	private ScoreBox[] scoreBoxes;
	private Texture background = new Texture("images/UnexpectedMagicBackground-black-window.png");
	private class ScoreBox extends Table{
		private Label playerNameLabel;
		private Label playerScoreLabel;
		private Label playerBestStreakLabel;
		ScoreBox(IPlayer player) {
			this(player.getName(), Integer.toString(player.getScore().getScore()), Integer.toString(player.getScore().getBestStreak()), false, skin);
		}
		public ScoreBox(String name, String score, String bestStreak, boolean alignRight, Skin skin) {
			super(skin);
			float scale = 0.5f;
			playerNameLabel = new Label(name, skin);
			playerNameLabel.setFontScale(scale);
			playerScoreLabel =  new Label(score, skin);
			playerScoreLabel.setFontScale(scale);
			playerBestStreakLabel =  new Label(bestStreak, skin);
			playerBestStreakLabel.setFontScale(scale);
			int align = (alignRight) ? Align.right : Align.left;
			this.add(playerNameLabel).align(align);
			this.row();
			this.add(playerScoreLabel).align(align);
			this.row();
			this.add(playerBestStreakLabel).align(align);
		}
	}
	
	protected ScoreScreen(SpriteBatch batch, List<IPlayer> players, ISong song) {
		super(batch);
		
		// table
		Table table = new Table();
		table.setFillParent(true);
		float tableScaleX = 0.6f;
		float tableScaleY = 1f;
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
		Label resultsLabel = new Label("__Results_____ ", skin);
		Label songTitleLabel = new Label(song.getTitle(), skin);
		int totalColumns = scoreBoxes.length + 1;
		table.add(resultsLabel).fillX().colspan(totalColumns).padBottom(30);
		table.row();
		table.add(songTitleLabel).fillX().colspan(totalColumns).padBottom(10).align(Align.center);
		table.row();
		ScoreBox labelBox = new ScoreBox("Name: ","Score: ","Best Streak: ", true, skin);
		table.add(labelBox).fillX().expandX().uniform().padLeft(2f);
		for(int i = 0; i < players.size(); i++){
			scoreBoxes[i] = new ScoreBox(players.get(i));
			table.add(scoreBoxes[i]).fillX().expandX().uniform().padLeft(2f).padRight(2f);
		}
		table.row();
		table.add(mainMenuButton).colspan(totalColumns).center().padTop(20);
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
		stage.act();
		stage.draw();
	}
}
