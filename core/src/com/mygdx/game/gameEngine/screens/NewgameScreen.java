package com.mygdx.game.gameEngine.screens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.UnexpectedMagic;
import com.mygdx.game.model.Constants;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.SongList;

public class NewgameScreen extends AbstractScreen {

	private TextureAtlas atlas;
	private Skin skin;
	// GAME STUFF
	private ArrayList<Player> players;
	private SongList songList;

	public NewgameScreen(final UnexpectedMagic game) {
		super(game);
		songList = new SongList();
		players = new ArrayList<>();
		atlas = new TextureAtlas("skins/commodore64/skin/uiskin.atlas");
		skin = new Skin(Gdx.files.internal("skins/commodore64/skin/uiskin.json"), atlas);
		Gdx.input.setInputProcessor(stage);
		// TEST PLAYERS
		players.add(new Player("Testplayer1", null, null));
		players.add(new Player("Testplayer2", null, null));
		//game.setScreen(new GameScreen(game, songList.getSong("Swordland"), players));
		// song takes the text in the text doc as a String
		
		// table
		Table table = new Table();
		table.setFillParent(true);
		table.setDebug(true, true);
		stage.addActor(table);
		// selectbox
		SelectBox<String> songSelector = new SelectBox<String>(skin);
		{
			Set<String> sa = songList.songs();
			String[] a = sa.toArray(new String[sa.size()]);
			Arrays.sort(a);
			songSelector.setItems(a);
			songSelector.setSelected(a[1]);
			System.out.println(Arrays.toString(a));
		}
		TextButton playButton = new TextButton("Play", skin);
		playButton.addListener(
			(Event event) -> {
				if(!(event instanceof InputEvent)) return false;
				InputEvent e = (InputEvent) event;
				if(e.getType() != InputEvent.Type.touchDown) return false;
				System.out.println(songSelector.getSelected());
				return true;
			}
		);
		table.add(songSelector).fillX().uniformX();
		table.row();
		table.add(playButton).fillX().uniformX();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.font.draw(game.batch, "THY NEWE GAME SCREENE", Constants.VIEWPORT_DIM_X / 4, Constants.VIEWPORT_DIM_Y / 2);
		game.batch.end();

		stage.act();
		stage.draw();

	}
}
