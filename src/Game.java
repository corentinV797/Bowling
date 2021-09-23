import customException.NotEnoughFramesException;
import customException.TooManyFramesException;

public class Game {
	private int[] rolls = new int[21];
	private int roll_counter = 0;
	
	public void read(String s) throws Exception {
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
	}
	
	public int score() {
		int score = 0;
		int frame_number = 0;
		for (int frame_counter = 0; frame_counter < 10; frame_counter++) {
			if (isStrike(frame_number)) {
				score+=(10 + getNextFrameScore(frame_number));
				frame_number++;
				continue;
			} else if (isSpare(frame_number)) {
				score+=(10 + getNextRollScore(frame_number));
			} else {
				score+=getCurrentFrameScore(frame_number);
			}
			frame_number+=2;
		}
		return score;
	}
	
	public void roll(int pins) {
		rolls[roll_counter++] = pins;
	}
	
	public boolean isStrike(int f) {
		return rolls[f] == 10;
	}
	
	public boolean isSpare(int f) {
		return getCurrentFrameScore(f) == 10;
	}
	
	public int getCurrentFrameScore(int f) {
		return rolls[f] + rolls[f + 1];
	}
	
	public int getNextFrameScore(int f) {
		return rolls[f + 1] + rolls[f + 2];	
	}
	
	public int getNextRollScore(int f) {
		return rolls[f + 2];
	}
}
