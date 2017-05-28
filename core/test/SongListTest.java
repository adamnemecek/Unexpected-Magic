import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mygdx.game.model.SongList;
import com.mygdx.game.model.song.ISong;

/**
 * Tests song retrieval
 * @author rarvid
 *
 */
public class SongListTest {
	private static SongList songList;
	
	@BeforeClass
	public static void Init(){
		songList = new SongList();	
	}
	
	@Test(expected = NullPointerException.class)
	public void testWrong() throws IOException{
		ISong song = songList.getSong("This Track does not exist");
	}
	
	@Test
	public void testSongs() throws IOException{ //goes through all songs and checks that they exist
		for (String string : songList.songs()){
			ISong song = songList.getSong(string);
			assertNotNull(song);
		}
	}

}
