package application.Games;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import application.Card.Card;
import application.Player.PlayerInGame;


//flow ST:  inserisci codice> gioca e finisci partita1> gioca e finisci partita2>gioca e finisci finale> fine torneo con dispay posizioni/punteggi

public class SimpleTournament extends Game { //in costruzione
	
	private SingleGame partita1;
	private SingleGame partita2;
	private SingleGame finale;
	private int counter=0;
	private PlayerInGame[] winners;
	private PlayerInGame[] losers;
	private String code; //serve per aprire la giusta directory torneo

	public SimpleTournament(File game) {
		super(game);
		try {
			scan = new Scanner(gameFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scan.reset();
		
		String line = scan.nextLine();
		String tokens[] = line.split(",");
		this.code = tokens[3];			
		
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/" + code + "/partita1.csv");
		File f1=new File(pathToFile.toString());	
		partita1= new SingleGame(f1);
		partita1.newGame();
		
		
		
		
				
		
		
	
	}
	
//TODO forse serve nuovo fxml apposito con label che indichi che partita si sta giocando oppure aggiungere label alla nostra fxml che in SingleGame normali
	//mostra codice partita mentre in singleGame derivato da tournament mostri "partita1", "partita2" oppure "finale"
	
	
	@Override
	public void removePlayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

}
