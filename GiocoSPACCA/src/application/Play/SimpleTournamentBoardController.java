package application.Play;

import java.io.IOException;

import application.Games.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimpleTournamentBoardController extends Board {
	
	private SimpleTournament tournament;
	
	public SimpleTournamentBoardController(SimpleTournament tournament, Game game) {
		this.tournament = tournament;
		this.game = game;
		System.out.println("Controller istanziato");		// stampa di prova
	}

	@Override
	protected void nextPlayerBoard() throws IOException {
		game.nextTurn();
		game.eliminationManagement();		// controllo per eliminazioni multiple/contemporanee
		
		stage = (Stage)(playCardButton.getScene().getWindow());
		FXMLLoader Loader=new FXMLLoader(SimpleTournamentBoardController.class.getResource("Board.fxml"));
		Loader.setController(this);
		root = (Parent) Loader.load();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	protected void endGame() throws IOException {
		game.save();
		
		if(!game.gameFile.getName().split("\\.")[0].equals("finale")) {		// se è una semifinale
			tournament.updateFinal();										// scrive il vincitore della semifinale nel file della finale
	
			// ricarica la pagina di inizio partita
			stage = (Stage)(playCardButton.getScene().getWindow());
	    	FXMLLoader Loader=new FXMLLoader(StartScreenController.class.getResource("StartScreen.fxml"));
	    	root = (Parent) Loader.load();
	    	scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.centerOnScreen();
	    	stage.show();
			  
		} else {					// se è la finale
			game.save();
			
			// carica la classifica del torneo (TODO)
			stage = (Stage)(saveAndExitButton.getScene().getWindow());
	    	FXMLLoader Loader=new FXMLLoader(GameScoreBoardController.class.getResource("GameScoreBoard.fxml"));
	    	root = (Parent) Loader.load();
	    	scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show();
		}
	}

	@Override
	protected void setTitle() {
		if(game.code.endsWith("1"))
			gameTitle.setText("SEMIFINALE 1");
		else if(game.code.endsWith("2"))
			gameTitle.setText("SEMIFINALE 2");
		else
			gameTitle.setText("FINALE");
	}
}
