package ncu.csie.game.ClientEnd;

public class Launcher {

	public static void main(String[] args) {
		Game game = new Game("My Game", 1200, 800);
		game.start();
		
	}
}