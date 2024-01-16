package application.Games;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

import application.Card.Card;
import application.Player.PlayerInGame;

public class LastManStanding extends Game {

	public LastManStanding(Path path)  {
		super(path);
		
		newGame();
		someoneDied();
	}
	
	public String getRound() {
		return "ROUND " + (5-players.size());
	}
	
	@Override
	public void removePlayer() {
		eliminated.add(0, players.remove(turn));
		eliminated.get(0).setHand(new ArrayList<Card>());
		eliminated.get(0).setHealthPoints(0);
		
		if(players.size()>1) {
			restartGame();
		} else {
			if(turn-1<0)
				turn = players.size()-1;
			else 
				turn--;
		}
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
		Random rand = new Random();
		turn = rand.nextInt(players.size());
	}
	
	
	private void someoneDied() {
		// --> se trova un giocatore morto, setta il turno in modo che sia il turno del giocatore morto
		// --> in questo modo, appena un giocatore muore è costretto ad abbandonare e la partita viene ricaricata
		
		boolean firstDead = true;
		int cont=0, i=turn;
		while(cont<players.size()) {
			if(i>=players.size())
				i=0;
			
			if(players.get(i).getHealthPoints()<=0) {
				if(!firstDead) {
					players.get(i).setHealthPoints(1);
				} else {
					turn = i;
					firstDead=false;
				}
			}
			cont++;
			i++;
		}
	}
}
