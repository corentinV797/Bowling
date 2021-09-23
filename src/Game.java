public class Game {
	private int[] rolls = new int[21];
	private int roll_counter = 0;
	
	public int score() {
		int score = 0;
		int frame_number = 0;
		for (int frame_counter = 0; frame_counter < 10; frame_counter++) {
			if (rolls[frame_number] == 10) {
				score+=(10 + getNextFrameScore(frame_number));
				frame_number++;
				continue;
			} else if (frame_score == 10) {
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
