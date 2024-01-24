package application.Games;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import application.Player.PlayerInGame;

public class SimpleTournament {
	
	private SingleGame currentGame;
	private File fin;

	public SimpleTournament(Path pathToGame) {
		try {
			// per individuare la partita corrente, controlla il file della finale
			
			fin = new File(pathToGame.toString() + "/finale.csv");
			Scanner scan = new Scanner(fin);
			scan.reset();
			
			if(scan.hasNextLine()) {
				scan.nextLine();			// salta la prima riga (intestazione)
			
				if(scan.hasNextLine()) {
					scan.nextLine();
					if(scan.hasNextLine()) {
						// caso finale	-->	sono stati scritti entrambi i finalisti
						this.currentGame = new SingleGame(fin.toPath());
					} else {
						// caso semifinale 2	-->	è stato scritto un solo finalista
						this.currentGame = new SingleGame(Paths.get(pathToGame.toString() + "/semifinale2.csv"));
					}
				} else {
					// caso semifinale 1	--> non è stato scritto alcun finalista
					this.currentGame = new SingleGame(Paths.get(pathToGame.toString() + "/semifinale1.csv"));
				}
			}
			scan.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public SingleGame getCurrentGame() {
		// restituisce la partita singola corrente identificata nel costruttore
		return this.currentGame;
	}
	
	public void updateFinal() {
		// aggiorna il file della finale
		try {
	        FileWriter fw = new FileWriter(fin.getAbsolutePath(), true);		// sovrascrive il file
	        PlayerInGame winner = currentGame.getPlayers().get(0);				// scrive il vincitore nel file della finale
	        winner.setHealthPoints(winner.MAXHP);
	        winner.setHand(new ArrayList<>());
	        fw.write("in," + winner + "\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
