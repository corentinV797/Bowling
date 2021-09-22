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
		scoreSeveralPins(20, 0);
		assertEquals(0, g.score());
	}
	
	@Test
	void onePinEachRoll() {
		scoreSeveralPins(20, 1);
		assertEquals(20, g.score());
	}
	
	@Test
	void oneSpareRoll() {
		g.roll(1);
		g.roll(9);
		g.roll(4);
		g.roll(3);
		scoreSeveralPins(16, 1);
		assertEquals(37, g.score());
	}

	void scoreSeveralPins(int rolls_number, int value) {
		for (int i = 0; i < rolls_number; i++) {
			g.roll(value);
		}
	}
}
