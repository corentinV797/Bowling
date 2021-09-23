public class Main {
	public static void main(String[] args) {		
		try {
			GameScorer gc = new GameScorer();
			// test case 1
			System.out.println(gc.getScore("X X X X X X X X X X X X"));
			// test case 2
			System.out.println(gc.getScore("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-"));
			// test case 3
			System.out.println(gc.getScore("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}