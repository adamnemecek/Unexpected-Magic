import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mygdx.game.model.SongList;
import com.mygdx.game.model.song.Song;
import com.mygdx.game.model.song.Voice;


/**
 * Tests whether octaves and pitches are in the correct range
 * @author rarvid
 *
 */
public class VoiceTest {
	private static Voice voice;
	
	@BeforeClass
	public static void Init() throws IOException{
		SongList songList = new SongList();
		Song song = (Song)songList.getSong("His Theme");
		voice = (Voice)song.getVoices()[0];
	}
	@Test
	public void testOctave(){
		for (int tick = 0; tick < voice.length(); tick++){
			assertEquals(voice.octaveAtTick(tick), 5.5 , 5.5); //checks that octaveAtTick Always is between 0 and 11		
		}
	}
	
	@Test
	public void testPitch(){
		for (int tick = 0; tick < voice.length(); tick++){
			assertEquals(voice.pitchAtTick(tick), 63.5 , 63.5); //checks that pitchAtTick Always is between 0 and 127		
		}
	}
}
