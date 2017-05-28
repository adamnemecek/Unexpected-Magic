import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mygdx.game.model.Ticker;

import com.mygdx.game.model.song.ISong;
import com.mygdx.game.model.song.Song;

import com.mygdx.game.model.SongList;

/**
 * Tests Ticking and pause in ticker
 * @author rarvid
 * 
 */

public class TickerTest {
	//tests ticker
	private Ticker ticker;
	static private ISong song;
	@BeforeClass
	public static void BeforeClass() throws IOException{
		final SongList songList = new SongList();
		song = songList.getSong("M.U.L.E. Theme");
	}
	@Before
	public void Before(){
		ticker = new Ticker(song);
	}
	
	@Test
	public void testTicking(){
		assertTrue(ticker.isTicking());
		ticker.updateTick(1000000);
		assertFalse(ticker.isTicking());
	}
	
	@Test
	public void testPause(){
		assertTrue(ticker.isTicking());
		ticker.updateTick(5);
		ticker.togglePauseTicker();
		
		double d = ticker.tickWithDecimals();
		for (int i = 0; i < 100; i++){
			ticker.updateTick(1);
			assertEquals(d, ticker.tickWithDecimals(), 0);
		}
		ticker.togglePauseTicker();
		ticker.updateTick(1);
		assertNotEquals(d, ticker.tickWithDecimals(), 0);
	}
	

}
