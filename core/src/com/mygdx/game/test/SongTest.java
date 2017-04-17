package com.mygdx.game.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import com.mygdx.game.model.song.Song;
import com.mygdx.game.model.song.Voice;

public class SongTest {

	@Test
	public void test() throws IOException {
		System.out.println(System.getProperty("user.dir"));
		String s;
		{
			Scanner sc = new Scanner(new File("assets/songmaps/his_theme.uxm")).useDelimiter("\\Z");
			s = sc.next();
			sc.close();
		}
		Song song = new Song(s);
		assertEquals("His Theme", song.title);
		assertEquals("4/4", song.time);
		assertEquals(120, song.bpm);
		Voice[] voices = song.getVoices();
		assertEquals(4, voices.length);
	}
}
