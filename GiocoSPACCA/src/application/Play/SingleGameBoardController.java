package application.Play;

import java.io.IOException;
import application.Games.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SingleGameBoardController extends Board {
	
	public SingleGameBoardController() {
		game = new SingleGame(InsertCodeController.pathToGame);
	}
	
	@Override
	protected void nextPlayerBoard() throws IOException {			// carica la schermata del prossimo giocatore (e salva la partita)
		game.nextTurn();
		game.save();
		stage = (Stage)(playCardButton.getScene().getWindow());
		FXMLLoader Loader=new FXMLLoader(SingleGameBoardController.class.getResource("Board.fxml"));
		Loader.setController(new SingleGameBoardController());
		root = (Parent) Loader.load();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
		
	}
	
	@Override
	protected void endGame() throws IOException {					// carica la classifica della partita
		game.save();
		stage = (Stage)(saveAndExitButton.getScene().getWindow());
    	FXMLLoader Loader=new FXMLLoader(GameScoreBoardController.class.getResource("GameScoreBoard.fxml"));
    	root = (Parent) Loader.load();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
	}

	@Override
	protected void setTitle() {
		gameTitle.setText(game.code.toUpperCase());
	}
}
