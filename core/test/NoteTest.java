import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.mygdx.game.model.song.Note;

/**
 * Test note creation
 * @author car0b1nius
 */
public class NoteTest {
	@Test
	public void testPitch() throws IOException {
		final int[] scale = new int[] {0,2,4,5,7,9,11};
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
		for(int denom = 1; denom <= 64; denom *= 2) {
			for(int num = 1; num < 127; num++) {
				String ns = String.format(note, num, denom);
				Note n = Note.getNote(ns);
				assertEquals(ns, num*64/denom, n.getDuration());
			}
		}
	}
}
