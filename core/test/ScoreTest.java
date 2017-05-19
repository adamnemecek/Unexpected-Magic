
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
	public void before(){
		score = new Score();
		
	}

	@Test
	public void testStreak(){
	
		for	(int i = 0; i < 100; i++){
			score.hitNote(false);
		}
		assertEquals(100, score.getStreak());
		assertEquals(150, score.getScore());
		
		score.missedNote();
		
		assertEquals(0, score.getStreak());
		assertEquals(150, score.getScore());
		
	}
	
	@Test
	public void testScore(){
		for	(int i = 0; i < 100; i++){
			score.hitNote(true);
		}
		
		assertEquals(score.getScore(), 100);
		
	}
	
	@Test
	public void testMultiplier(){
		for(int i = 0; i < 100; i++){
			score.hitNote(false);
		}
		assertEquals(3, score.getMultiplier());
		for(int i = 0; i < 100; i++){
			score.hitNote(true);
		}
		assertEquals(3, score.getMultiplier());
	}

}
