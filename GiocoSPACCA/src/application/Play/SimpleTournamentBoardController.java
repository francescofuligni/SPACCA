package application.Play;

import java.io.IOException;

import application.Games.SimpleTournament;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimpleTournamentBoardController extends Board {
	
	private SimpleTournament tournament;
	
	public SimpleTournamentBoardController() {
		this.tournament = new SimpleTournament(InsertCodeController.pathToGame);
		this.game = tournament.getCurrentGame();
	}

	@Override
	protected void nextPlayerBoard() throws IOException {
		game.nextTurn();
		game.save();
		stage = (Stage)(playCardButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(SimpleTournamentBoardController.class.getResource("SingleGameBoard.fxml"));
		  Loader.setController(new SimpleTournamentBoardController());
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}

	@Override
	protected void endGame() throws IOException {
		game.save();
		
		if(!game.gameFile.getName().split("\\.")[0].equals("finale")) {		// se è una semifinale
			
			tournament.updateFinal();				// scrive il vincitore della semifinale nel file della finale
			stage = (Stage)(playCardButton.getScene().getWindow());
			  FXMLLoader Loader=new FXMLLoader(SimpleTournamentBoardController.class.getResource("SingleGameBoard.fxml"));
			  Loader.setController(new SimpleTournamentBoardController());
			  root = (Parent) Loader.load();
			  scene = new Scene(root);
			  stage.setScene(scene);
			  stage.show();
			  
		} else {									// se è la finale
			game.save();
			stage = (Stage)(saveAndExitButton.getScene().getWindow());
	    	FXMLLoader Loader=new FXMLLoader(GameScoreBoardController.class.getResource("GameScoreBoard.fxml"));
	    	root = (Parent) Loader.load();
	    	scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show();
		}
	}
}
