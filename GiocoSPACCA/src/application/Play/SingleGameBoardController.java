package application.Play;

import java.io.IOException;
import application.Games.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SingleGameBoardController extends Board {
	
	public SingleGameBoardController(Game game) {
		this.game = game;
	}
	
	@Override
	protected void nextPlayerBoard() throws IOException {
		game.nextTurn();					// avanza al turno successivo
		game.eliminationManagement();		// controllo per eliminazioni multiple/contemporanee
		
		// carica la schermata del prossimo giocatore
		stage = (Stage)(playCardButton.getScene().getWindow());
		FXMLLoader Loader=new FXMLLoader(SingleGameBoardController.class.getResource("Board.fxml"));
		Loader.setController(this);
		root = (Parent) Loader.load();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}
	
	@Override
	protected void endGame() throws IOException {
		game.save();	// salvataggio partita
		
		// carica la classifica della partita
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
		gameTitle.setText(game.code.toUpperCase());
	}
}
