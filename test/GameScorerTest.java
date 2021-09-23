import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import customException.InvalidPinsDownException;
import customException.NotEnoughFramesException;
import customException.TooManyFramesException;

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
}
