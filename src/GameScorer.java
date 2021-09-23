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
		if (s.matches(".*[^\s0-9X/-].*")) { // everything but space character, any digit, X character, / character or - character 
			throw new InvalidPinsDownException("Forbidden character");
		}
		String split[] = s.split(" ");
		int frame_counter = 0;
		for (String str : split) {
			frame_counter++;
		}
		if (frame_counter < Constants.FRAME_NUMBER) {
			throw new NotEnoughFramesException("There isn't enough frame in this game (number of frames = " + frame_counter + ")");
		} else if (frame_counter > (Constants.FRAME_NUMBER + 2)) {
			throw new TooManyFramesException("There are too many frames in this game = " + frame_counter + ")");
		}
		
		for (int i = 0; i < frame_counter; i++) {
			if (isPotentialStrike(split[i])) {
				char first_char = split[i].charAt(0);
				if(!isStrikeChar(first_char)) {
					throw new NotEnoughRollsException("Not enough roll in this frame (frame number = " + i + ")");
				}
				g.roll(Constants.STRIKE_SCORE);
			} else if (isPotentialSpare(split[i])) {
				char first_char = split[i].charAt(0);
				char second_char = split[i].charAt(1);
				if (isSpareChar(second_char)) {
					if (isStrikeChar(first_char) || isNoHitChar(first_char) || isSpareChar(first_char)) {
						throw new InvalidFrameException("Invalid Frame (frame number = " + i + ")");
					}
					readAndRoll(g, first_char);
					g.roll(Constants.SPARE_SCORE - Character.getNumericValue(first_char));
				} else {
					if (isSpareChar(first_char) || isStrikeChar(first_char) || isStrikeChar(second_char)) {
						throw new InvalidFrameException("Invalid Frame (frame number = " + i + ")");
					}
					readAndRoll(g, first_char);
					readAndRoll(g, second_char);
				}
			} else if (isPotentialBonusRound(split[i])) {
				char first_char = split[i].charAt(0);
				char second_char = split[i].charAt(1);
				char third_char = split[i].charAt(2);
				if (i != (Constants.FRAME_NUMBER - 1)) {
					throw new TooManyRollsException("Too many rolls in that frame (frame number = " + i + ")");
				} else if (isSpareChar(first_char) || isStrikeChar(first_char) || !isSpareChar(second_char) || isSpareChar(third_char) || isStrikeChar(third_char)) {
					throw new InvalidFrameException("Invalid Frame (frame number = " + i + ")");
				}
				readAndRoll(g, first_char);
				g.roll(Constants.SPARE_SCORE - Character.getNumericValue(first_char));
				readAndRoll(g, third_char);
			} else {
				throw new TooManyRollsException("Too many rolls in that frame (" + split[i].length() + ")");
			}
		}
	}
	
	public void readAndRoll(Game g, char c) {
		if (isNoHitChar(c)) {
			g.roll(Constants.NO_HIT_SCORE);
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
	
	public boolean isStrikeChar(char c) {
		return c == Constants.STRIKE_CHAR;
	}
	
	public boolean isSpareChar(char c) {
		return c == Constants.SPARE_CHAR;
	}
	
	public boolean isNoHitChar(char c) {
		return c == Constants.NOHIT_CHAR;
	}
}
