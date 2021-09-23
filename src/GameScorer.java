import customException.InvalidFrameException;
import customException.InvalidPinsDownException;
import customException.NotEnoughFramesException;
import customException.NotEnoughRollsException;
import customException.TooManyFramesException;
import customException.TooManyRollsException;

public class GameScorer {
	public int getScore(String s) {
		Game g = new Game();
		try {
			read(g, s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return g.score();
	}
	
	public void read(Game g, String s) throws Exception {
		if (s.matches(".*[^\s0-9X/-].*")) {
			throw new InvalidPinsDownException("Forbidden character");
		}
		String split[] = s.split(" ");
		int frame_counter = 0;
		for (String str : split) {
			frame_counter++;
		}
		if (frame_counter < 10) {
			throw new NotEnoughFramesException("There isn't enough frame in this game");
		} else if (frame_counter > 12) {
			throw new TooManyFramesException("There are too many frames in this game");
		}
		
		for (int i = 0; i < frame_counter; i++) {
			if (isPotentialStrike(split[i])) {
				char first_char = split[i].charAt(0);
				if(first_char != 'X') {
					throw new NotEnoughRollsException("Not enough roll in this frame");
				}
				g.roll(10);
			} else if (isPotentialSpare(split[i])) {
				char first_char = split[i].charAt(0);
				char second_char = split[i].charAt(1);
				if (second_char == '/') {
					if (first_char == 'X' || first_char == '-' || first_char == '/') {
						throw new InvalidFrameException("Invalid Frame");
					}
					readAndRoll(g, first_char);
					g.roll(10 - Character.getNumericValue(first_char));
				} else {
					if (first_char == '/' || first_char == 'X' || second_char == 'X') {
						throw new InvalidFrameException("Invalid Frame");
					}
					readAndRoll(g, first_char);
					readAndRoll(g, second_char);
				}
			} else if (isPotentialBonusRound(split[i])) {
				char first_char = split[i].charAt(0);
				char second_char = split[i].charAt(1);
				char third_char = split[i].charAt(2);
				if (i != (10 - 1)) {
					throw new TooManyRollsException("Too many rolls in that frame");
				} else if (first_char == '/' || first_char == 'X' || second_char != '/' || third_char == '/' || third_char == 'X') {
					throw new InvalidFrameException("Invalid Frame");
				}
				readAndRoll(g, first_char);
				g.roll(10 - Character.getNumericValue(first_char));
				readAndRoll(g, third_char);
			} else {
				throw new TooManyRollsException("Too many rolls in that frame");
			}
		}
	}
	
	public void readAndRoll(Game g, char c) {
		if (c == '-') {
			g.roll(0);
		} else {
			g.roll(Character.getNumericValue(c));
		}
	}
	
	public boolean isPotentialStrike(String s) {
		return s.length() == 1;
	}
	
	public boolean isPotentialSpare(String s) {
		return s.length() == 2;
	}
	
	public boolean isPotentialBonusRound(String s) {
		return s.length() == 3;
	}
}
