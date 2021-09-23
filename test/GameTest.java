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
		scoreSpare();
		g.roll(4);
		g.roll(3);
		scoreSeveralPins(16, 1);
		assertEquals(37, g.score());
	}
	
	@Test
	void oneStrikeRoll() {
		scoreStrike();
		g.roll(4);
		g.roll(3);
		scoreSeveralPins(16, 0);
		assertEquals(24, g.score());
	}
	
	@Test
	void oneSpareInLastFrame() {
		scoreSeveralPins(18, 0);
		scoreSpare();
		g.roll(4);
		assertEquals(14, g.score());
	}
	
	@Test
	void oneStrikeInLastFrame() {
		scoreSeveralPins(18, 0);
		scoreStrike();
		g.roll(4);
		g.roll(3);
		assertEquals(17, g.score());
	
	@Test
	void onlySparesPlusFive() {
		for (int i = 0; i < 10; i++) {
			scoreSpare();
		}
		g.roll(5);
		assertEquals(114, g.score());
	}
	
	@Test
	void almostPerfectGameBonusSpare() {
		scoreSeveralPins(9, 10);
		scoreStrike();
		scoreSpare();
		assertEquals(281, g.score());
	}
	
	// test case 1
	@Test
	void perfectGame() {
		scoreSeveralPins(10, 10);
		scoreStrike();
		scoreStrike();
		assertEquals(300, g.score());
	}
	
	// test case 2
	@Test
	void tenPairsOfNineAndMiss() {
		for (int i = 0; i < 10; i++) {
			nineAndMiss();
		}
		assertEquals(90, g.score());
	}
	
	void nineAndMiss() {
		g.roll(9);
		g.roll(0);
	}
	
	}

	void scoreSeveralPins(int rolls_number, int value) {
		for (int i = 0; i < rolls_number; i++) {
			g.roll(value);
		}
	}
	
	void scoreStrike() {
		g.roll(10);
	}
	
	void scoreSpare() {
		g.roll(1);
		g.roll(9);
	}
}
