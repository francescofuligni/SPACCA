package application.Games;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import application.Card.Card;
import application.Player.PlayerInGame;

public class LastManStanding extends Game {
	
	public boolean isNewGame = false;
	// TODO: VISUALIZZARE UNA SCHERMATA ALL'INIZIO DI OGNI PARTITA

	public LastManStanding(File gameFile)  {
		super(gameFile);
		
		while(scan.hasNextLine()) {				// aggiunge i giocatori all'arraylist allPlayers
			String line=scan.nextLine();
			String[] tokens = line.split(",");
			
			String username = tokens[1];
			int healthPoints = Integer.parseInt(tokens[2]);
			PlayerInGame p = new PlayerInGame(username, healthPoints);
			
			if(tokens[0].equals("in")) {			// giocatori in partita
				ArrayList<Card> hand = new ArrayList<>();
				for(int i=3; i<tokens.length; i++)
					hand.add(deck.getCard(Integer.parseInt(tokens[i])));
				p.setHand(hand);
				players.add(p);
				
			} else {								// giocatori eliminati
				eliminated.add(p);
			}
		}
		scan.close();
		
		this.isNewGame = newGame();
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
}
