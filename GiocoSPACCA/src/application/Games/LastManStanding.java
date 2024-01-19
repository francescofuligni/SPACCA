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
	
	
	private boolean someoneDied() {
		// --> se trova un giocatore morto, setta il turno in modo che sia il turno del giocatore morto
		// --> in questo modo, appena un giocatore muore è costretto ad abbandonare e la partita viene ricaricata
		
		// se ci sono più giocatori eliminati contemporaneamente, viene eliminato quello con minori punti salute minori
		int cont=0, i=turn, iLowest=-1;
		boolean first = true;
		
		while(cont<players.size()) {							// identifica il giocatore con punti salute minori
			if(i>=players.size())
				i=0;
			
			if((players.get(i).getHealthPoints()<=0 && first)) {
				iLowest = i;
				first = false;
			} else if(iLowest!=-1 && players.get(i).getHealthPoints() < players.get(iLowest).getHealthPoints()) {
				iLowest = i;
			}
			cont++;
			i++;
		}
		
		if(iLowest==-1) {
			return false;
		} else {
			for(int j=0; j<players.size(); j++) {
				if(players.get(j).getHealthPoints()<=0 && j!=iLowest)
					players.get(j).setHealthPoints(1);			// mantiene in partita tutti i giocatori tranne quello con punti salute minori
			}
			turn = iLowest;
			return true;
		}
	}
}
