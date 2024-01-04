package application.Card;

import application.Games.Game;
import javafx.scene.image.Image;

public abstract class Card {

	private int code;
	private Image pic;
	
	public Card(int code) {
		this.code = code;
		this.pic= new Image(getClass().getResource("/Cards/"+code+".png").toString());
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
	public abstract void effect(Game g);
	
}
