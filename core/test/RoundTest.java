import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mygdx.game.model.IPlayer;
import com.mygdx.game.model.Round;
import com.mygdx.game.model.SongList;
import com.mygdx.game.model.song.ISong;


/**
 * Probably the most pointless test, tests a getter
 * @author ???
 * revised by rarvid
 *
 */

public class RoundTest {

	private Round round;
	@Before
	public void init() throws IOException{
		SongList songList = new SongList();
		ISong song = songList.getSong("His Theme");
		//Song song = (Song) isong;
		ArrayList<IPlayer> players = new ArrayList<>();
		round = new Round(song, players);
	}
	@Test
	public void test() {
		List<IPlayer> list = round.getPlayers();
		assertNotNull(list);
	}

}
