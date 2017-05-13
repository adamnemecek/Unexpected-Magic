package com.mygdx.game.gameEngine.screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.mygdx.game.UnexpectedMagic;
import com.mygdx.game.model.Constants;
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
	private ButtonGroup<TextButton> playerNumButtongroup;
	private String[] playerNumItems;
	TextButton zeroPlayerButton; //TODO do these need to be fields here?
	TextButton onePlayerButton;
	TextButton twoPlayerButton;
	TextButton threePlayerButton;
	TextButton fourPlayerButton;
	Label selectSongLabel;
	Label selectPlayerNumLabel;
	Label namePlayers;
	TextField player1NameTextField;
	TextField player2NameTextField;
	TextField player3NameTextField;
	TextField player4NameTextField;

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
		
		// selecting song
		selectSongLabel = new Label("Select song: ", skin);
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
		
		// selecting number of players
		selectPlayerNumLabel = new Label("Select number \nof players: ", skin);
		playerNumItems = new String[] {"0", "1", "2", "3", "4"};
		zeroPlayerButton = new TextButton(playerNumItems[0], skin);
		onePlayerButton = new TextButton(playerNumItems[1], skin);
		twoPlayerButton = new TextButton(playerNumItems[2], skin);
		threePlayerButton = new TextButton(playerNumItems[3], skin);
		fourPlayerButton = new TextButton(playerNumItems[4], skin);
		
		playerNumButtongroup = new ButtonGroup<TextButton>(zeroPlayerButton, onePlayerButton, twoPlayerButton, threePlayerButton, fourPlayerButton);
		playerNumButtongroup.setMaxCheckCount(1);
		playerNumButtongroup.setMinCheckCount(1);
		playerNumButtongroup.setChecked(playerNumItems[0]);
		
		// naming players
		namePlayers = new Label("Name players: ", skin);
		player1NameTextField = new TextField("", skin);
		player1NameTextField.setFocusTraversal(true);
		player1NameTextField.setMaxLength(10); //TODO appropriate maxlength of name
		player1NameTextField.setMessageText("Player 1");
		
		player2NameTextField = new TextField("", skin);
		player2NameTextField.setFocusTraversal(true);
		player2NameTextField.setMaxLength(10); //TODO appropriate maxlength of name
		player2NameTextField.setMessageText("Player 2");
		
		player3NameTextField = new TextField("", skin);
		player3NameTextField.setFocusTraversal(true);
		player3NameTextField.setMaxLength(10); //TODO appropriate maxlength of name
		player3NameTextField.setMessageText("Player 3");
		
		player4NameTextField = new TextField("", skin);
		player4NameTextField.setFocusTraversal(true);
		player4NameTextField.setMaxLength(10); //TODO appropriate maxlength of name
		player4NameTextField.setMessageText("Player 4");
		
		// play button
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
		
		// table layout
		int totalColumns = playerNumItems.length;
		table.add(selectSongLabel).left().colspan(totalColumns);
		table.row();
		table.add(songSelector).colspan(totalColumns);
		table.row();
		table.add(selectPlayerNumLabel).left().colspan(totalColumns);
		table.row();
		table.add(zeroPlayerButton).fillX().uniform();
		table.add(onePlayerButton).fillX().uniform();
		table.add(twoPlayerButton).fillX().uniform();
		table.add(threePlayerButton).fillX().uniform();
		table.add(fourPlayerButton).fillX().uniform();
		table.row();
		table.add(namePlayers).left().colspan(totalColumns);
		table.row();
		table.add(player1NameTextField).left().colspan(totalColumns);
		table.row();
		table.add(player2NameTextField).left().colspan(totalColumns);
		table.row();
		table.add(player3NameTextField).left().colspan(totalColumns);
		table.row();
		table.add(player4NameTextField).left().colspan(totalColumns);
		table.row();
		table.add(playButton).colspan(totalColumns);
		
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
