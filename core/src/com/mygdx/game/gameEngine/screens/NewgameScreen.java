package com.mygdx.game.gameEngine.screens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
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
		// SONG LIST
		Table table = new Table();
		table.setDebug(true, true);
		table.setFillParent(true);
		SelectBox<String> songSelector = new SelectBox<String>(skin);
		{
			Set<String> sa = songList.songs();
			String[] a = sa.toArray(new String[sa.size()]);
			Arrays.sort(a);
			songSelector.setItems(a);
			songSelector.setSelected(a[1]);
			System.out.println(Arrays.toString(a));
		}
		table.add(songSelector);
		stage.addActor(table);
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
