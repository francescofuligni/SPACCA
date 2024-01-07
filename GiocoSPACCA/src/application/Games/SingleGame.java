package application.Games;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import application.Card.Card;
import application.Player.PlayerInGame;

public class SingleGame extends Game{
	
	public SingleGame(File game)  {
		super(game);
		int botCounter=1;
		
		while(scan.hasNextLine() ) {				// aggiunge i giocatori all'arraylist allPlayers
			String line=scan.nextLine();
			String[] tokens = line.split(",");
			
			String username = tokens[1];
			int healthPoints = Integer.parseInt(tokens[2]);
			PlayerInGame p;
			if(username == "BOT"+botCounter)
				p = createBot(username, healthPoints);
			else
				p = new PlayerInGame(username, healthPoints);
			
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
		
		if(currentPlayer().getHand().size() == 0)			// se i giocatori non hanno carte in mano, vengono distribuite le carte
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
	        if(players.size()>0) {
	        	Iterator<PlayerInGame> iter1 = players.iterator();
	        	while(iter1.hasNext())
	        		fw.write("in," + iter1.next() + "\n");
	        }
			
			// giocatori eliminati
			if(eliminated.size()>0) {
				Iterator<PlayerInGame> iter2 = eliminated.iterator();
				while(iter2.hasNext())
					fw.write("out," + iter2.next() + "\n");
			}
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
