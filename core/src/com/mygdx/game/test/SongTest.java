package com.mygdx.game.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import com.mygdx.game.model.song.Note;
import com.mygdx.game.model.song.Song;
import com.mygdx.game.model.song.Voice;

public class SongTest {
	@Test
	public void testHisTheme() throws IOException {
		String s;
		{
			Scanner sc = new Scanner(new File("assets/songmaps/his_theme.uxm"));
			sc.useDelimiter("\\Z");
			s = sc.next();
			sc.close();
		}
		Song song = new Song(s);
		assertEquals("His Theme", song.title);
		assertEquals("4/4", song.time);
		assertEquals(120, song.bpm);
		Voice[] voices = song.getVoices();
		assertEquals(4, voices.length);
		Note[] bar = voices[0].getBars()[0];
		assertTrue(bar[0] == bar[3]);
	}
	@Test
	public void testNotes() throws IOException {
		String note = "A1:1";
		Note n1 = Note.getNote(note);
		Note n2 = Note.getNote(note);
		assertTrue(n1 == n2);
		note = "A2:1";
		n2 = Note.getNote(note);
		assertFalse(n1 == n2);
	}
}
