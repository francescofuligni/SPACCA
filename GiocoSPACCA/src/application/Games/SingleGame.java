package application.Games;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import application.Card.Card;
import application.Player.PlayerInGame;

public class SingleGame extends Game{
	
	public SingleGame(File game)  {
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
				hand.add(deck.getCard(Integer.parseInt(tokens[i])));
			}
			p.setHand(hand);
			
			players.add(p);
		}
		scan.close();
		
		giveCards();
		
		// TODO SALVARE IL TURNO QUANDO SI CHIUDE LA PARTITA
		Random rand = new Random();
		this.turn = rand.nextInt(players.size());	// seleziona randomicamente il primo giocatore
	}
	
	public void removePlayer() {
		players.remove(turn);
		turn--;
	}

	@Override
	public void save() {
		try {
	        FileWriter fw = new FileWriter(gameFile.getAbsolutePath());			// sovrascrive il file
	        Iterator<PlayerInGame> iter = players.iterator();
	        
	        fw.write("SingleGame," + difficulty + "," + this.turn + "\n");
			while(iter.hasNext())
				fw.write(iter.next() + "\n");
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
