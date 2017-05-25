import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.mygdx.game.model.IPlayer;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Round;
import com.mygdx.game.model.song.Song;

public class RoundTest {

	private Round round;
	@Before
	public void init() throws IOException{
		Song song = new Song("songmaps/his_theme.uxm");
		ArrayList<IPlayer> players = new ArrayList<>();
		round = new Round(song, players);
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
