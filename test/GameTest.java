import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {
	private Game g;
	
	@BeforeEach
	void createGame() {
		g = new Game();
	}

	@Test
	void getZeroScore() {
		for (int i = 0; i < 20; i++) {
			g.roll(0);
		}
		assertEquals(0, g.score());
	}
	
	@Test
	void onePinEachRoll() {
		for (int i = 0; i < 20; i++) {
			g.roll(1);
		}
		assertEquals(20, g.score());
	}
	
	@Test
	void oneSpareRoll() {
		g.roll(1);
		g.roll(9);
		g.roll(4);
		g.roll(3);
		for (int i = 0; i < 16; i++) {
			g.roll(1);
		}
		assertEquals(37, g.score());
	}

}
