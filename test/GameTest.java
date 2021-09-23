import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import customException.NotEnoughFramesException;
import customException.TooManyFramesException;

class GameTest {
	private Game g;
	
	@BeforeEach
	void createGame() {
		g = new Game();
	}
	
	@Test
	void readEmptyGame() {
		assertThrows(NotEnoughFramesException.class, () -> g.read(""));
	}
	
	@Test
	void readGameWithTooManyFrames() {
		assertThrows(TooManyFramesException.class,
				() -> g.read("43 43 43 43 43 43 43 43 43 43 43 43 43"));
	}

	@Test
	void getZeroScore() {
		scoreSeveralPins(20, 0);
		assertEquals(0, g.score());
	}
	
	@Test
	void readZeroScore() {
		try {
			g.read("-- -- -- -- -- -- -- -- -- --");
			assertEquals(0, g.score());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void onePinEachRoll() {
		scoreSeveralPins(20, 1);
		assertEquals(20, g.score());
	}
	
	@Test
	void readOnePinEachRoll() {
		try {
			g.read("11 11 11 11 11 11 11 11 11 11");
			assertEquals(20, g.score());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	void readOneSpareRoll() {
		try {
			g.read("1/ 43 11 11 11 11 11 11 11 11");
			assertEquals(37, g.score());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	void readOneStrikeRoll() {
		try {
			g.read("X 43 -- -- -- -- -- -- -- --");
			assertEquals(24, g.score());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	}	
	
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
	
	// test case 3
	@Test
	void tenPairsOfFiveFinalFive() {
		scoreSeveralPins(21, 5);
		assertEquals(150, g.score());
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
