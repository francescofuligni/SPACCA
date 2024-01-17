package application.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminMenuController {

    @FXML
    private Button logoutButton;

    @FXML
    private Button manageGamesButton;

    @FXML
    private Button managePlayersButton;

    @FXML
    public void logout(ActionEvent event) {
    	// ritorna al MainMenu
    }

    @FXML
    public void manageGames(ActionEvent event) {
    	// lancia la schermata di gestione delle partite
    }

    @FXML
    public void managePlayers(ActionEvent event) {
    	// lancia la schermata di gestione dei giocatori
    }
    
}
