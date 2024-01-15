package application.Games;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import application.Card.Card;
import application.Player.PlayerInGame;

public class SingleGame extends Game{
	
	public SingleGame(Path path)  {
		super(path);
		
		if(this.allDead)			// nel caso in cui i giocatori sono morti tutti contemporaneamente, il vincitore è chi ha giocato la carta che ha ucciso tutti
			previousPlayer().setHealthPoints(1);
		
		newGame();
	}
	
	
	@Override
	public void removePlayer() {
		eliminated.add(0, players.remove(turn));
		eliminated.get(0).setHand(new ArrayList<Card>());
		eliminated.get(0).setHealthPoints(0);
		if(turn-1<0)
			turn = players.size()-1;
		else 
			turn--;
	}

	@Override
	public void save() {
		try {
	        FileWriter fw = new FileWriter(gameFile.getAbsolutePath());			// sovrascrive il file
	        fw.write("SingleGame," + difficulty + "," +  turn +  "\n");
	        
	        // giocatori in partita
	        if(players.size()>0)
	        	for(PlayerInGame p : players)
	        		fw.write("in," + p + "\n");
			
			// giocatori eliminati
			if(eliminated.size()>0)
				for(PlayerInGame p : eliminated)
					fw.write("out," + p + "\n");
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
