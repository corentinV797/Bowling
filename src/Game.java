public class Game {
	private int[] rolls = new int[Constants.MAX_ROLL_NUMBER];
	private int roll_counter = 0;
			
	public int score() {
		int score = 0;
		int frame_number = 0;
		for (int frame_counter = 0; frame_counter < Constants.FRAME_NUMBER; frame_counter++) {
			if (isStrike(frame_number)) {
				score+=(Constants.STRIKE_SCORE + getNextFrameScore(frame_number));
				frame_number++;
				continue;
			} else if (isSpare(frame_number)) {
				score+=(Constants.SPARE_SCORE + getNextRollScore(frame_number));
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
		return rolls[f] == Constants.STRIKE_SCORE;
	}
	
	public boolean isSpare(int f) {
		return getCurrentFrameScore(f) == Constants.SPARE_SCORE;
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
