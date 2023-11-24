package application.SingleGame;

import java.io.IOException;

import application.Admin.SelectPlayerNumberSGController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Board2PlayersController {

	@FXML
	private Label currentPlayer, nextPlayer, lifePoints;
	
	@FXML
	private ImageView card1, card2, card3, card4, card5;
	
	@FXML
	private Button saveAndExit,infoPartita;
	
	@FXML
	private ProgressBar lifeBar;

	
	
}
