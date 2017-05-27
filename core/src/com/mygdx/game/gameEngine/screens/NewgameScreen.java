package com.mygdx.game.gameEngine.screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.gameEngine.sound.MusicPlayer;
import com.mygdx.game.model.IPlayer;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.SongList;
import com.mygdx.game.model.song.ISong;
import com.mygdx.game.model.song.IVoice;

/**
 * Screen for setting up a new game. 
 * @author car0b1nius
 * Revised by soflarb
 * 
 * Uses: AbstractScreen, SongList
 * 
 * Used by: NewGameScreen
 */
public class NewgameScreen extends AbstractScreen {
	// GAME STUFF
	private SongList songList;
	private ButtonGroup<TextButton> playerNumButtongroup;
	private TextField[] playerNames;

	public NewgameScreen(SpriteBatch batch) {
		super(batch);
		songList = new SongList();
		Gdx.input.setInputProcessor(stage);
		
		// table
		Table table = new Table();
		table.setFillParent(true);
		//table.setDebug(true, true);
		stage.addActor(table);
		
		//back button
		TextButton backButton = new TextButton("Back", skin);
		backButton.addListener(
				(Event event) -> {
					if(!(event instanceof InputEvent)) return false;
					InputEvent evt = (InputEvent) event;
					if(evt.getType() != InputEvent.Type.touchDown) return false;
					changeToPreviousScreen();
					return true;
				}
			);
		
		// selecting number of players
		Label selectPlayerNumLabel = new Label("Select number \nof players: ", skin);
		String[] playerNumItems = new String[] {"0", "1", "2", "3", "4"};
		TextButton[] playerNumButtons = new TextButton[playerNumItems.length];
		for(int i = 0; i < playerNumButtons.length; i++) {
			TextButton tb = new TextButton(playerNumItems[i], skin);
			playerNumButtons[i] = tb;
		}
		playerNumButtongroup = new ButtonGroup<TextButton>(playerNumButtons) {
			//I'd rather add a ListenableButtonGroup to the utils package, but it's not allowed to have any dependencies on GDX.
			//Oh well, enjoy your faaabulous project structure, it's just as you want it and not messy at all.
			@Override
			protected boolean canCheck(TextButton button, boolean newState) {
				if(!super.canCheck(button, newState)) return false;
				if(!newState) return true;
				for(int j = 0; j < playerNumButtons.length-1; j++){
					boolean enabled = j <= playerNumButtongroup.getCheckedIndex()-1;
					TextField f = playerNames[j];
					f.setDisabled(!enabled);
					f.setVisible(enabled);
				}
				return true;
			}
		};
		playerNumButtongroup.setMaxCheckCount(1);
		playerNumButtongroup.setMinCheckCount(1);
		
		// selecting song
		Label selectSongLabel = new Label("Select song: ", skin);
		SelectBox<SongEntry> songSelector = new SelectBox<>(skin);
		{
			Set<String> ss = songList.songs();
			SongEntry[] a = new SongEntry[ss.size()];
			{
				int i = 0;
				for(String str : ss) {
					a[i] = new SongEntry(str);
					i++;
				}
			}
			Arrays.sort(a);
			songSelector.setItems(a);
			songSelector.addListener(
				(Event event) -> {
					if(!(event instanceof ChangeEvent)) return false;
					int voiceNum = songList.voicesInSong(songSelector.getSelected().title);
					playerNumButtons[Math.min(voiceNum, playerNumButtongroup.getCheckedIndex())].setChecked(true);
					for(int i = 0; i < 5; i++) {
						TextButton b = playerNumButtons[i];
						b.setDisabled(i > voiceNum);
						//b.setVisible(i <= voiceNum);
					}
					return true;
				}
			);
			System.out.println(Arrays.toString(a));
			//songSelector.setSelected(a[1]);
		}

		// naming players
		Label namePlayers = new Label("Name players: ", skin);
		playerNames = new TextField[] {
				makePlayerNameField("Player 1"),
				makePlayerNameField("Player 2"),
				makePlayerNameField("Player 3"),
				makePlayerNameField("Player 4")
		};
		playerNumButtongroup.setChecked(playerNumItems[0]);
		
		// play button
		TextButton playButton = new TextButton("Play", skin);
		playButton.addListener(
			(Event event) -> {
				if(!(event instanceof InputEvent)) return false;
				InputEvent evt = (InputEvent) event;
				if(evt.getType() != InputEvent.Type.touchDown) return false;
				//System.out.println(songSelector.getSelected());
				ISong song;
				try {
					song = songList.getSong(songSelector.getSelected().title);
				} catch (IOException e) {
					e.printStackTrace();
					//Remove file from list
					songSelector.setItems(songSelector.getItems().removeIndex(songSelector.getSelectedIndex()));
					songSelector.setSelectedIndex(0);
					return true;
				}
				IVoice[] voices = song.getVoices();
				int pl = playerNumButtongroup.getCheckedIndex();
				List<IPlayer> players = new ArrayList<>(pl);
				for(int i = 0; i < pl; i++) {
					String playerName = playerNames[i].getText().trim();
					if(playerName.equals("")){
						playerName = playerNames[i].getMessageText();
						}
					players.add(new Player(playerName, voices[i]));
					System.out.println("playername: " + playerName);
				}
				List<IVoice> nonPlayerVoices = new ArrayList<>(voices.length-pl);
				for(int i = pl; i < voices.length; i++) {
					nonPlayerVoices.add(voices[i]);
				}
				changeToScreen(new GameScreen(batch, song, players, nonPlayerVoices));
				MusicPlayer.getInstance().stop();
				return true;
			}
		);
		
		// table layout
		int totalColumns = playerNumItems.length;
		table.add(backButton).colspan(totalColumns).align(Align.left);
		table.row();
		table.add(selectSongLabel).left().colspan(totalColumns);
		table.row();
		table.add(songSelector).colspan(totalColumns);
		table.row();
		table.add(selectPlayerNumLabel).left().colspan(totalColumns);
		table.row();
		for(TextButton tb : playerNumButtons) {
			table.add(tb).fillX().uniform();
		}
		table.row();
		table.add(namePlayers).left().colspan(totalColumns);
		table.row();
		for(TextField f : playerNames) {
			table.add(f).left().colspan(totalColumns).row();
		}
		table.add(playButton).colspan(totalColumns);
		
	}
	private TextField makePlayerNameField(String placeholder) {
		TextField textField = new TextField(null, skin);
		textField.setFocusTraversal(true);
		textField.setMaxLength(10);
		textField.setMessageText(placeholder);
		textField.setDisabled(true);
		return textField;
	}
	@Override
	public void render(float delta) {
		super.render(delta);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		stage.act();
		stage.draw();
	}
	private static class SongEntry implements Comparable<SongEntry>{
		private static final int MAX_LENGTH = 23;
		final String title, toString;
		public SongEntry(String title) {
			this.title = title;
			int l = title.length();
			if(l <= MAX_LENGTH) {
				toString = title;
			} else {
				toString =
						title.substring(0,(MAX_LENGTH-3)/2) +
						"..." +
						title.substring(l-(MAX_LENGTH-3-(MAX_LENGTH-3)/2)); //To ensure rounding errors go both ways
			}
			
		}
		@Override
		public String toString() {
			return toString;
		}
		@Override
		public boolean equals(Object that) {
			if(that == null || that.getClass() != SongEntry.class) return false;
			return title.equals(((SongEntry)that).title);
		}
		@Override
		public int compareTo(SongEntry that) {
			return title.compareTo(that.title);
		}
	}
}
