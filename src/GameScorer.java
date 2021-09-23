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
		
		for (int i = 0; i < frame_counter; i++) {
			if (split[i].length() == 1) {
				if(split[i].charAt(0) != 'X') {
					throw new NotEnoughRollsException("Not enough roll in this frame");
				}
				g.roll(10);
			} else if (split[i].length() == 2) {
				if (split[i].charAt(1) == '/') {
					readAndRoll(g, split[i].charAt(0));
					g.roll(10 - Character.getNumericValue(split[i].charAt(0)));
				} else {
					readAndRoll(g, split[i].charAt(0));
					readAndRoll(g, split[i].charAt(1));
				}
			} else if (split[i].length() == 3) {
				readAndRoll(g, split[i].charAt(0));
				g.roll(10 - Character.getNumericValue(split[i].charAt(0)));
				readAndRoll(g, split[i].charAt(2));
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
