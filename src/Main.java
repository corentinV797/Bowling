public class Main {
	public static void main(String[] args) {		
		try {
			Game g1 = new Game();
			g1.read("X X X X X X X X X X X X");
			System.out.println(g1.score());
			
			Game g2 = new Game();
			g2.read("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-");
			System.out.println(g2.score());
			
			Game g3 = new Game();
			g3.read("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5");
			System.out.println(g3.score());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}