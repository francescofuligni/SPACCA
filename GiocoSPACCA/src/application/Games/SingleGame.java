package application.Games;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import application.Card.Card;
import application.Player.PlayerInGame;

public class SingleGame extends Game{
	
	public SingleGame(File game) throws FileNotFoundException {
		super(game);
		int botCounter=1;
		
		while(scan.hasNextLine() ) {				// aggiunge i giocatori all'arraylist allPlayers
			String line=scan.nextLine();
			String[] tokens = line.split(",");
			
			String username = tokens[0];
			int healthPoints = Integer.parseInt(tokens[1]);
			PlayerInGame p;
			if(username == "BOT"+botCounter)
				p = createBot(username, healthPoints);
			else
				p = new PlayerInGame(username, healthPoints);
			
			ArrayList<Card> hand = new ArrayList<>();
			for(int i=2; i<tokens.length; i++) {
				hand.add(deck[Integer.parseInt(tokens[i])]);				// dipende da come chiamiamo le carte
			}
			p.setHand(hand);
			
			players.add(p);
		}
		scan.close();
		
		shuffle();
		Random rand = new Random();
		this.turn = rand.nextInt(players.size());
	}
	
	public void removePlayer() {
		players.remove(turn);
		turn--;
	}
}
