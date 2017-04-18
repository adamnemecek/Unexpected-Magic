package com.mygdx.game.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.mygdx.game.model.song.Note;
import com.mygdx.game.model.song.Song;
import com.mygdx.game.model.song.Voice;

public class SongTest {
	@Test
	public void testHisTheme() throws IOException {
		Song song = new Song("songmaps/his_theme.uxm");
		assertEquals("His Theme", song.title);
		int[] time = song.getTime();
		assertEquals(4, time[0]);
		assertEquals(4, time[1]);
		assertEquals(2, time.length);
		assertEquals(120, song.bpm);
		Voice[] voices = song.getVoices();
		assertEquals(4, voices.length);
		Note[] bar = voices[0].getBars()[0];
		assertTrue(bar[0] == bar[3]);
	}
	@Test
	public void testNotes() throws IOException {
		Note n1 = Note.getNote("A1:1/1");
		assertEquals("A1:1/1", n1.toString());
		Note n2 = Note.getNote("A1:1");
		assertEquals("A1:1/1", n2.toString());
		assertEquals(n1, n2);
		n2 = Note.getNote("A1:2/2");
		assertNotEquals(n1, n2);
	}
}
