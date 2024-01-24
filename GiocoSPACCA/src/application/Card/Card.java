package application.Card;

import application.Games.Game;
import javafx.scene.image.Image;

public abstract class Card {	// superclasse di NormalCard e SpecialCard

	private int code;			// codice carta
	private Image pic;			// immagine carta
	
	public Card(int code) {
		this.code = code;
		this.pic= new Image(getClass().getResource("/Cards/" + code + ".png").toString());
		// il codice corrisponde al nome del file immagine
	}
	
	public int getCode() {
		return code;
	}
	public Image getPicture() {
		return pic;
	}
	public String toString() {
		return ""+code;
	}
	
	// metodo astratto	--> diverso per carte speciali o normali
	public abstract void effect(Game g);
}
