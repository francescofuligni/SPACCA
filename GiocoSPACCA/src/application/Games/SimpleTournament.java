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

//flow ST:  inserisci codice> gioca e finisci partita1> gioca e finisci partita2>gioca e finisci finale> fine torneo con dispay posizioni/punteggi

public class SimpleTournament { //in costruzione
	
	private SingleGame currentGame;
	private File fin;
	public String code;

	public SimpleTournament(Path pathToGame) {
		try {
			// per sapere la partita corrente, si controlla il file della finale
			
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
		return this.currentGame;
	}
	
	public void updateFinal() {
		try {
	        FileWriter fw = new FileWriter(fin.getAbsolutePath(), true);			// sovrascrive il file
	        PlayerInGame winner = currentGame.getPlayers().get(0);
	        winner.setHealthPoints(winner.MAXHP);
	        winner.setHand(new ArrayList<>());
	        fw.write("in," + winner + "\n");
	        
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// note di Fula
	
	// IL TORNEO LAST MAN STANDING PUO' ESSERE IMPLEMENTATO COME SOTTOCLASSE DI GAME, modificando il metodo removePlayer in modo che ricarichi la vita degli altri giocatori
	//	--> NELLA BOARD sarebbe utile aggiungere un messaggio o una schermata intermedia quando si ricarica la partita, per distinguere il torneo LMS dalla partita normale
	
	// i salvataggi e le eliminazioni vengono gestite direttamente all'interno di SingleGame
	// 	--> nella board, i metodi verranno richiamati sul currentGame, restituito dal metodo getCurrentGame()
	
	// NELLA BOARD del torneo è utile inseirire un label (piccolo in alto) che specifica la partita attuale, restituita da getMessage()
	//	--> sarebbe anche utile aggiungere una schermata intermedia tra una partita e l'altra con un messaggio che indichi che partita sta per iniziare
	
	/* TODO
	 * 
	 * Nell'endgame della SimpleTournamentBoard (DA IMPLEMENTARE COME SOTTOCLASSE DI BOARD) bisogna fare un controllo:
	 * - se il codice del currentGame è semifinale1 o semifinale2, bisogna scrivere il vincitore (unico giocatore con stato "in") nel file della finale
	 * 		e successivamente si lancia la board sulla prossima partita (semifinale2 o finale) --> basta semplicemente ricaricare l'fxml: il costruttore della classe SimpleTournament troverà automaticamente la prossima partita
	 * - se il codice del currentGame è finale, bisogna lanciare l'fxml per la classifica della partita
	 * 
	 */
}
