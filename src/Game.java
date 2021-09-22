import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<Integer> rolls = new ArrayList<Integer>();
	
	public int score() {
		int score = 0;
		int frame_number = 0;
		for (int frame_counter = 0; frame_counter < 10; frame_counter++) {
			int frame_score = rolls.get(frame_number) + rolls.get(frame_number + 1);
				score+=frame_score;
			frame_number+=2;
		}
		return score;
	}
	
	public void roll(int pins) {
		rolls.add(pins);
	}
}
