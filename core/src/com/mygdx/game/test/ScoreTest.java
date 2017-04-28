package com.mygdx.game.test;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;

import org.junit.Test;

import com.mygdx.game.model.song.Note;
import com.mygdx.game.model.Score;
public class ScoreTest {
	
	Note n;
	Score score;
	@Before
	public void beforeClass() throws IOException{
		score = new Score();
		n = Note.getNote("A1:1/1");
	}

	@Test
	public void testStreak(){
	
		for	(int i = 0; i < 100; i++){
			score.hitnote(n);
		}
		assertEquals(100, score.getStreak());
		
		score.missedNote();
		
		assertEquals(0, score.getStreak());
		
	}
	
	@Test
	public void testScore(){
		for	(int i = 0; i < 100; i++){
			score.hitnote(n);
		}
		
		assertEquals(score.getScore(), n.calculateLength()*100);
		System.out.println(score.getScore());
	}

}
