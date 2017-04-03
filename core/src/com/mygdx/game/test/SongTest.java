package com.mygdx.game.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import com.mygdx.game.song.Song;

public class SongTest {

	@Test
	public void test() throws IOException {
		System.out.println(System.getProperty("user.dir"));
		Scanner sc = new Scanner(new File("assets/songmaps/his_theme.uxm")).useDelimiter("\\Z");
		String s = sc.next();
		sc.close();
		Song song = new Song(s);
		assertEquals("His Theme", song.title);
	}
}
