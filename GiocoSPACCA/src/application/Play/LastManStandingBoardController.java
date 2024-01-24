package application.Play;

import java.io.IOException;

import application.Games.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LastManStandingBoardController extends Board {

	public LastManStandingBoardController(Game game) {
		this.game = game;
	}
	
	@Override
	protected void nextPlayerBoard() throws IOException {
		if(isOut && game.getPlayers().size()>1) {
			game.save();		// salvataggio partita
			
			// se viene eliminato un giocaore (non in caso di vittoria), inizia il round successivo	--> ricarica la schermata di inizio partita
			stage = (Stage)(playCardButton.getScene().getWindow());
	    	FXMLLoader Loader=new FXMLLoader(LastManStandingBoardController.class.getResource("StartScreen.fxml"));
	    	root = (Parent) Loader.load();
	    	scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.centerOnScreen();
	    	stage.show();
		} else {
			game.nextTurn();					// avanza al turno successivo
			game.eliminationManagement();		// controllo per eliminazioni multiple/contemporanee
			
			// carica la schermata del prossimo giocatore
			stage = (Stage)(playCardButton.getScene().getWindow());
			FXMLLoader Loader=new FXMLLoader(LastManStandingBoardController.class.getResource("Board.fxml"));
			Loader.setController(this);
			root = (Parent) Loader.load();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		}
	}

	@Override
	protected void endGame() throws IOException {
		game.save();		// salvataggio partita
		
		// carica la classifica
		stage = (Stage)(saveAndExitButton.getScene().getWindow());
    	FXMLLoader Loader=new FXMLLoader(GameScoreBoardController.class.getResource("GameScoreBoard.fxml"));
    	root = (Parent) Loader.load();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
	}

	@Override
	protected void setTitle() {
		// imposta il label gameTitle (in alto nell'fxml)
		int round = 5-game.getPlayers().size();
		if(round > 3)
			round = 3;
		
		gameTitle.setText("ROUND " + round);
		
		if(isOut && game.getPlayers().size()>2) {
			nextPlayerTitle.setText("");
			nextPlayer.setText("");
		}
	}
}
