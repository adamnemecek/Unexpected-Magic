import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;
import com.mygdx.game.model.SongList;
import com.mygdx.game.model.song.Song;
import com.mygdx.game.model.song.INote;
import com.mygdx.game.model.song.ISong;
import com.mygdx.game.model.song.IVoice;

/**
 * 
 * @author car0b1nius
 * Revised by rarvid
 *
 */

public class SongTest {
	@Test
	public void testHisTheme() throws IOException {
		//Song song = new Song("songmaps/his_theme.uxm");
		SongList songList = new SongList();
		ISong isong = songList.getSong("His Theme");
		Song song = (Song) isong;
		assertEquals("His Theme", song.getTitle());
		int[] time = song.getTime();
		assertEquals(4, time[0]);
		assertEquals(4, time[1]);
		assertEquals(2, time.length);
		assertEquals(76, song.getBpm());
		IVoice[] voices = song.getVoices();
		assertEquals(4, voices.length);
		INote note = voices[0].noteAtTick(0);
		assertNotNull(note);
		
		
	}
}
