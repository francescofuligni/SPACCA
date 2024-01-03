package application.Play;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Admin.SelectPlayerNumberSGController;
import application.Games.SingleGame;
import application.Player.PlayerInGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class BoardController implements Initializable{

	@FXML
	private Label currentPlayer, nextPlayer, lifePoints;
	
	@FXML
	private ImageView[] hand;
	
	@FXML
	private Button saveAndExit;
	
	@FXML
	private ProgressBar lifeBar;

	SingleGame game=new SingleGame(InsertCodeController.file);
		
	private PlayerInGame current = game.currentPlayer();
	private PlayerInGame next = game.nextPlayer();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		currentPlayer.setText(current.getUsername());
		nextPlayer.setText(next.getUsername());
		lifePoints.setText(""+current.getHealthPoints());
		
		
	}
	
}
