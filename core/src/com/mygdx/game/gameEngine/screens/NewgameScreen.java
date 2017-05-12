package com.mygdx.game.gameEngine.screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.UnexpectedMagic;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.SongList;

/**
 * 
 * @author car0b1nius
 */
public class NewgameScreen extends AbstractScreen {

	// GAME STUFF
	private ArrayList<Player> players;
	private SongList songList;

	public NewgameScreen(final UnexpectedMagic game) {
		super(game);
		songList = new SongList();
		players = new ArrayList<>();
		Gdx.input.setInputProcessor(stage);
		// TEST PLAYERS
		players.add(new Player("Testplayer1", null, null));
		players.add(new Player("Testplayer2", null, null));
		// song takes the text in the text doc as a String
		
		// table
		Table table = new Table();
		table.setFillParent(true);
		table.setDebug(true, true);
		stage.addActor(table);
		// selectbox
		SelectBox<SongEntry> songSelector = new SelectBox<>(skin);
		{
			Set<String> ss = songList.songs();
			SongEntry[] a = new SongEntry[ss.size()];
			int i = 0;
			for(String str : ss) {
				a[i] = new SongEntry(str);
				i++;
			}
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
				try {
					game.setScreen(new GameScreen(game, songList.getSong(songSelector.getSelected().title), players));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return true;
			}
		);
		table.add(songSelector).fillX().uniformX();
		table.row();
		table.add(playButton).fillX().uniformX();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		/*game.batch.begin();
		game.font.draw(game.batch, "THY NEWE GAME SCREENE", Constants.VIEWPORT_DIM_X / 4, Constants.VIEWPORT_DIM_Y / 2);
		game.batch.end();*/
		stage.act();
		stage.draw();
	}
	private static class SongEntry  implements Comparable<SongEntry>{
		private static final int MAX_LENGTH = 23;
		final String title, toString;
		public SongEntry(String title) {
			this.title = title;
			int l = title.length();
			if(l <= MAX_LENGTH) {
				toString = title;
			} else {
				//@formatter:off
				toString = title.substring(0,(MAX_LENGTH-3)/2) + "..."
				         + title.substring(l-(MAX_LENGTH-3-(MAX_LENGTH-3)/2)); //To ensure rounding errors go both ways
				//@formatter:on
			}
			
		}
		@Override
		public String toString() {
			return toString;
		}
		@Override
		public int compareTo(SongEntry that) {
			return title.compareTo(that.title);
		}
	}
}
