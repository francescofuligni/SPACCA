package application.Games;

import java.io.File;

public class SingleGame extends Game{
	
	public SingleGame(File game) {
		super(game);
	}
	
	public void removePlayer() {
		players.remove(turn);
		turn--;
	}
}
