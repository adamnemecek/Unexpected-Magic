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
		assertEquals("His Theme", song.getTitle());
		int[] time = song.getTime();
		assertEquals(4, time[0]);
		assertEquals(4, time[1]);
		assertEquals(2, time.length);
		assertEquals(120, song.getBpm());
		Voice[] voices = song.getVoices();
		assertEquals(4, voices.length);
		Note[] notes = voices[0].getNotes();
		assertNotNull(notes[0]);
	}
}
