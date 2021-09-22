import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTest {


	@Test
	void getZeroScore() {
		Game g = new Game();
		for (int i = 0; i < 20; i++) {
			g.roll(0);
		}
		assertEquals(0, g.score());
	}
	
	@Test
	void onePinEachRoll() {
		Game g = new Game();
		for (int i = 0; i < 20; i++) {
			g.roll(1);
		}
		assertEquals(20, g.score());
	}
	
	@Test
	void oneSpareRoll() {
		Game g = new Game();
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
