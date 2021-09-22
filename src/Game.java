
public class Game {
	private int pin_down_counter = 0;
	
	public int score() {
		return pin_down_counter;
	}
	
	public void roll(int pins) {
		pin_down_counter+=pins;
	}
}
