import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.mygdx.game.model.song.Note;

public class NoteTest {
	@Test
	public void testNumbers() throws IOException {
		final int[] scale = new int[] {0,2,4,5,7,9,11};
		final String[] notes = new String[]{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
		for(int i = 0; i <= 127; i++) {
			int oct = i / 12;
			String pitch = notes[i % 12];
			Note n = Note.getNote(pitch + oct + ":1/1");
			assertEquals(i, n.number);
		}
	}
	@Test(expected=IOException.class) 
	public void testLow() throws IOException {
		Note.getNote("Cb0:1/1");
	}
	@Test(expected=IOException.class) 
	public void testHigh() throws IOException {
		Note.getNote("G#10:1/1");
	}
	
	@Test
	public void testSameness() throws IOException {
		Note n1 = Note.getNote("C5:1/1");
		assertSame("C5:1/1", n1.toString());
		Note n2 = Note.getNote("C5:1");
		assertSame("C5:1/1", n2.toString());
		assertSame(n1, n2);
		n2 = Note.getNote("C5:2/2");
		assertNotSame(n1, n2);
		n2 = Note.getNote("-:1/1");
		assertSame(-1, n2.number);
	}
}
