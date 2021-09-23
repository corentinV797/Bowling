import customException.InvalidPinsDownException;
import customException.NotEnoughFramesException;
import customException.NotEnoughRollsException;
import customException.TooManyFramesException;

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
		
		for (String str : split) {
			if (str.length() == 1) {
				if(str.charAt(0) != 'X') {
					throw new NotEnoughRollsException("Not enough roll in this frame");
				}
				g.roll(10);
			} else if (str.length() == 2) {
				if (str.charAt(1) == '/') {
					readAndRoll(g, str.charAt(0));
					g.roll(10 - Character.getNumericValue(str.charAt(0)));
				} else {
					readAndRoll(g, str.charAt(0));
					readAndRoll(g, str.charAt(1));
				}
			} else if (str.length() == 3) {
				readAndRoll(g, str.charAt(0));
				g.roll(10 - Character.getNumericValue(str.charAt(0)));
				readAndRoll(g, str.charAt(2));
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
}
