import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.mygdx.game.model.song.Note;

/**
 * Test note creation
 * @author car0b1nius
 */
public class NoteTest {
	@Test
	public void testPitch() throws IOException {
		final String[] notes = new String[]{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
		for(int i = 0; i <= 127; i++) {
			int oct = i / 12;
			String pitch = notes[i % 12];
			String ns = pitch + oct + ":1/1";
			Note n = Note.getNote(ns);
			assertEquals(ns, i, n.getPitch());
		}
	}
	@Test
	public void testDuration() throws IOException {
		final String note = "-:%d/%d";
		for(int num = 1; num < 127; num++) {
			for(int denom = 1; denom <= 64; denom *= 2) {
				String ns = String.format(note, num, denom);
				Note n = Note.getNote(ns);
				assertEquals(ns, num*64/denom, n.getDuration());
			}
		}
	}
	@Test
	public void testEverything() throws IOException {
		for(int num = 1; num < 127; num++) {
			testAllPitches(Integer.toString(num), num*64);
			for(int denom = 1; denom <= 64; denom *= 2) {
				testAllPitches(num + "/" + denom, num*64/denom);
			}
		}
	}
	final String[] notes = new String[]{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	private void testAllPitches(String duration, int expected) throws IOException {
		{
			String ns = "-:" + duration;
			Note n = Note.getNote(ns);
			assertTrue(ns + " rest", n.isRest());
			assertEquals(ns + " duration", expected, n.getDuration());
			String s = ".";
		}
		for(int i = 0; i <= 127; i++) {
			int oct = i / 12;
			String pitch = notes[i % 12];
			String ns = pitch + oct + ":" + duration;
			Note n = Note.getNote(ns);
			assertEquals(ns + " pitch", i, n.getPitch());
			assertFalse(ns + " rest", n.isRest());
			assertEquals(ns + " duration", expected, n.getDuration());
		}
	}
}
