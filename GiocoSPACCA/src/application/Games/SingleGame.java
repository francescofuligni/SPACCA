package application.Games;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import application.Card.Card;
import application.Player.PlayerInGame;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SingleGame extends Game{
	
	public SingleGame(Path path)  {
		super(path);
		
		if(!newGame())					// se è una nuova partita, distribuisce le carte
			eliminationManagement();	// se non è una nuova partita, gestisce eventuali eliminazioni
	}
	
	
	@Override
	public void removePlayer() {
		// rimuove il giocatore corrente nel caso in cui sia stato eliminato
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
		// salva la partita su file
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
			Alert exceptionAlert = new Alert(AlertType.ERROR);
			exceptionAlert.setTitle("ERRORE");
			exceptionAlert.setHeaderText("Errore nell'accesso al file");
			exceptionAlert.setContentText(e.getClass().getSimpleName());
			exceptionAlert.showAndWait();
		}
	}
	
	@Override
	public void eliminationManagement() {
		// gestisce eliminazioni multiple/contemporanee
		boolean allDead = true;
		
		for(PlayerInGame p : players) {					// scorre i giocatori in partita e controlla se sono tutti eliminati
			if(p.getHealthPoints()>0) {
				allDead=false;
				break;
			}
		}
		
		if(allDead) {									// nel caso in cui tutti i giocatori sono eliminati, il vincitore è chi ha maggiori punti salute
			int iHighest = -1, cont=0, i=turn;
			while(cont<players.size()) {
				if(i>=players.size())
					i=0;
				
				if(i==turn || players.get(i).getHealthPoints() > players.get(iHighest).getHealthPoints())
					iHighest = i;
				cont++;
				i++;
			}
			players.get(iHighest).setHealthPoints(1);	// riporta in partita il giocatore con maggiori punti salute
		}
	}
}
