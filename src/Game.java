/**
 * 
 * @author Corentin V
 *
 * Game class purpose is to process every roll input (int format)
 * and to calculate the score of the game (core class)
 * 
 */
public class Game {
	// contain all the rolls of the game, int format
	private int[] rolls = new int[Constants.MAX_ROLL_NUMBER];
	// keep track of the current roll
	private int roll_counter = 0;
	
	/**
	 * Process game score based on rolls content
	 * 
	 * @return int score of the game
	 */
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
	/**
	 * Add a roll in rolls array
	 * @param pins
	 */
	public void roll(int pins) {
		rolls[roll_counter++] = pins;
	}
	
	/**
	 * Check if current frame is a strike
	 * @param f current frame : int
	 * @return boolean
	 */
	public boolean isStrike(int f) {
		return rolls[f] == Constants.STRIKE_SCORE;
	}
	
	/**
	 * Check if current frame is a spare
	 * @param f current frame : int
	 * @return boolean
	 */
	public boolean isSpare(int f) {
		return getCurrentFrameScore(f) == Constants.SPARE_SCORE;
	}
	
	/**
	 * return score of the input frame
	 * @param f current frame : int
	 * @return int
	 */
	public int getCurrentFrameScore(int f) {
		return rolls[f] + rolls[f + 1];
	}
	
	/**
	 * return score of the next frame of the input frame
	 * @param f current frame : int
	 * @return int
	 */
	public int getNextFrameScore(int f) {
		return rolls[f + 1] + rolls[f + 2];	
	}
	
	/**
	 * return the next roll score after the input frame
	 * @param f current frame : int
	 * @return int
	 */
	public int getNextRollScore(int f) {
		return rolls[f + 2];
	}
}
