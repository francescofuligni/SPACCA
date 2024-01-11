package application.Games;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import application.Card.Card;
import application.Player.PlayerInGame;

public class SingleGame extends Game{
	
	public SingleGame(File game)  {
		super(game);
		boolean allDead = true;
		
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
				
				if(p.getHealthPoints() > 0)
					allDead=false;
			} else {								// giocatori eliminati
				eliminated.add(p);
			}
		}
		
		
		if(allDead)			// nel caso in cui i giocatori sono morti tutti contemporaneamente, il vincitore è chi ha giocato la carta che ha ucciso tutti
			previousPlayer().setHealthPoints(1);
		
		scan.close();
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
