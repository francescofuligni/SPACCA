package application.Games;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import application.Card.Card;
import application.Player.PlayerInGame;

public class LastManStanding extends Game {
	
	public boolean isNewGame;

	public LastManStanding(Path path)  {
		super(path);
		
		this.isNewGame = newGame();
		maxOneDead();
		someoneDied();		// TODO
	}
	
	public String getRound() {
		return "ROUND " + (5-players.size());
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
		
		if(!currentPlayer().equals(nextPlayerAlive()))
			restartGame();
	}

	@Override
	public void save() {
		try {
	        FileWriter fw = new FileWriter(gameFile.getAbsolutePath());			// sovrascrive il file
	        fw.write("LASTMANSTANDING," + difficulty + "," +  turn +  "\n");
	        
	        // giocatori in partita
	        if(players.size()>0)
	        	for(PlayerInGame p : players)
	        		fw.write("in," + p + "\n");
			
			// giocatori eliminati
			if(eliminated.size()>0)
				for(PlayerInGame p : eliminated)
					fw.write("eliminated," + p + "\n");
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void restartGame() {
		for(PlayerInGame p : players) {
			p.setHealthPoints(p.MAXHP);			// reinizializza i punti salute
			p.setHand(new ArrayList<>());		// rimuove le vecchie carte
		}
	}
	
	
	// TODO: COMBINARE I DUE METODI SEGUENTI IN MODO CHE SI FACCIANO MENO ITERAZIONI POSSIBILI
	// --> obiettivo: scorrere al massimo una volta il vettore contenente i giocatori per individuare se ci sono uno o più eliminati che devono abbandonare
	
	private void someoneDied() {
		// TODO
		// controlla se qualcuno è morto --> se trova un giocatore morto, setta il turno in modo che sia il turno del giocatore morto
		// --> in questo modo, appena un giocatore muore è costretto ad abbandonare e la partita viene ricaricata
	}
	
	private void maxOneDead() {
		// TODO
		// metodo che si assicura che muoia solo un giocatore per turno --> se ne muoiono di più, imposta i punti vita degli altri a 1
	}
}
