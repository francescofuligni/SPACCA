package application.Play;

import java.io.IOException;

import application.Games.LastManStanding;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LastManStandingBoardController extends Board {

	public LastManStandingBoardController() {
		game = new LastManStanding(InsertCodeController.pathToGame);
	}
	
	@Override
	protected void nextPlayerBoard() throws IOException {
		if(isOut && game.getPlayers().size()>1) {
			game.save();
			
			// ricarica la schermata di inizio partita
			stage = (Stage)(playCardButton.getScene().getWindow());
	    	FXMLLoader Loader=new FXMLLoader(LastManStandingBoardController.class.getResource("StartScreen.fxml"));
	    	root = (Parent) Loader.load();
	    	scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.centerOnScreen();
	    	stage.show();
		} else {
			game.nextTurn();
			game.save();
			
			// carica la schermata del prossimo giocatore
			stage = (Stage)(playCardButton.getScene().getWindow());
			FXMLLoader Loader=new FXMLLoader(LastManStandingBoardController.class.getResource("SingleGameBoard.fxml"));
			Loader.setController(new LastManStandingBoardController());
			root = (Parent) Loader.load();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		}
	}

	@Override
	protected void endGame() throws IOException {
		game.save();
		stage = (Stage)(saveAndExitButton.getScene().getWindow());
    	FXMLLoader Loader=new FXMLLoader(GameScoreBoardController.class.getResource("GameScoreBoard.fxml"));
    	root = (Parent) Loader.load();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
	}
}
