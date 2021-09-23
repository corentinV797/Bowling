import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import customException.InvalidFrameException;
import customException.InvalidPinsDownException;
import customException.NotEnoughFramesException;
import customException.NotEnoughRollsException;
import customException.TooManyFramesException;
import customException.TooManyRollsException;

public class GameScorerTest {
	private GameScorer gc;
	
	@BeforeEach
	void createGame() {
		gc = new GameScorer();
	}
		
	@Test
	void readEmptyGame() {
		Game g = new Game();
		assertThrows(NotEnoughFramesException.class, () -> gc.read(g, ""));
	}
	
	@Test
	void readGameWithTooManyFrames() {
		Game g = new Game();
		assertThrows(TooManyFramesException.class,
				() -> gc.read(g, "43 43 43 43 43 43 43 43 43 43 43 43 43"));
	}
	
	@Test
	void readGameWithInvalidPinsDown() {
		Game g = new Game();
		assertThrows(InvalidPinsDownException.class,
				() -> gc.read(g, "4& 43 43 43 43 43 43 43 43 43 43 43"));
	}
	
	@Test
	void readGameWithOneFrameWithOnlyOneRoll() {
		Game g = new Game();
		assertThrows(NotEnoughRollsException.class,
				() -> gc.read(g, "-- - -- -- -- -- -- -- -- --"));
		assertThrows(NotEnoughRollsException.class,
				() -> gc.read(g, "-- -- 1 -- -- -- -- -- -- --"));
		assertThrows(NotEnoughRollsException.class,
				() -> gc.read(g, "-- -- -- / -- -- -- -- -- --"));
	}
	
	@Test
	void readGameWithTooManyRollsInOneFrame() {
		Game g = new Game();
		assertThrows(TooManyRollsException.class,
				() -> gc.read(g, "-- --- -- -- -- -- -- -- -- --"));
		assertThrows(TooManyRollsException.class,
				() -> gc.read(g, "-- -- 1111 -- -- -- -- -- -- --"));
		assertThrows(TooManyRollsException.class,
				() -> gc.read(g, "-- -- -- 1/4 -- -- -- -- -- --"));
		Game g1 = new Game();
		assertThrows(TooManyRollsException.class,
				() -> gc.read(g1, "-- -- -- -- -- -- -- -- -- 1/--"));
	}
	
	@Test
	void readGameWithInvalidFrameException() {
		Game g = new Game();
		assertThrows(InvalidFrameException.class,
				() -> gc.read(g, "-- /- -- -- -- -- -- -- -- --"));
		assertThrows(InvalidFrameException.class,
				() -> gc.read(g, "-- -X -- -- -- -- -- -- -- --"));
		assertThrows(InvalidFrameException.class,
				() -> gc.read(g, "-- -/ -- -- -- -- -- -- -- --"));
		assertThrows(InvalidFrameException.class,
				() -> gc.read(g, "-- // -- -- -- -- -- -- -- --"));
		Game g1 = new Game();
		assertThrows(InvalidFrameException.class,
				() -> gc.read(g1, "-- -- -- -- -- -- -- -- -- -//"));
		Game g2 = new Game();
		assertThrows(InvalidFrameException.class,
				() -> gc.read(g2, "-- -- -- -- -- -- -- -- -- /--"));
		Game g3 = new Game();
		assertThrows(InvalidFrameException.class,
				() -> gc.read(g3, "-- -- -- -- -- -- -- -- -- -/X"));
	}

	@Test
	void readZeroScore() {
		try {
			assertEquals(0, gc.getScore("-- -- -- -- -- -- -- -- -- --"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@Test
	void readOnePinEachRoll() {
		try {
			assertEquals(20, gc.getScore("11 11 11 11 11 11 11 11 11 11"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@Test
	void readOneSpareRoll() {
		try {
			assertEquals(37, gc.getScore("1/ 43 11 11 11 11 11 11 11 11"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void readOneStrikeRoll() {
		try {
			assertEquals(24, gc.getScore("X 43 -- -- -- -- -- -- -- --"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@Test
	void readOneSpareInLastFrame() {
		try {
			assertEquals(14, gc.getScore("-- -- -- -- -- -- -- -- -- 1/4"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void readOneStrikeInLastFrame() {
		try {
			assertEquals(17, gc.getScore("-- -- -- -- -- -- -- -- -- X 43"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@Test
	void readOnlySparesPlusFive() {
		try {
			assertEquals(114, gc.getScore("1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/5"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@Test
	void readAlmostPerfectGameBonusSpare() {
		try {
			assertEquals(281, gc.getScore("X X X X X X X X X X 1/"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// test case 1
	@Test
	void readPerfectGame() {
		try {
			assertEquals(300, gc.getScore("X X X X X X X X X X X X"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// test case 2
	@Test
	void readTenPairsOfNineAndMiss() {
		try {
			assertEquals(90, gc.getScore("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// test case 3
	@Test
	void readTenPairsOfFiveFinalFive() {
		try {
			assertEquals(150, gc.getScore("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void isPotentialStrikeTest() {
		assertTrue(gc.isPotentialStrike("X"));
		assertFalse(gc.isPotentialStrike(""));
		assertFalse(gc.isPotentialStrike("11"));
	}
	
	@Test
	void isPotentialSpareTest() {
		assertTrue(gc.isPotentialSpare("1/"));
		assertFalse(gc.isPotentialSpare(""));
		assertFalse(gc.isPotentialSpare("X"));
		assertFalse(gc.isPotentialSpare("1/2"));
	}
	
	@Test
	void isPotentialBonusRoundTest() {
		assertTrue(gc.isPotentialBonusRound("1/1"));
		assertFalse(gc.isPotentialBonusRound(""));
		assertFalse(gc.isPotentialBonusRound("X"));
		assertFalse(gc.isPotentialBonusRound("1/22"));
	}
}
