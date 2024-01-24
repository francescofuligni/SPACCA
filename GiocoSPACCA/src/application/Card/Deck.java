package application.Card;

import java.util.Random;

public class Deck {
	
	public static final int SIZE = 20;
	public Card[] cards = new Card[SIZE];		// mazzo infinito	--> si salvano solo i tipi di carte (20 diversi)
	
	public Deck() {
		for(int i=0; i<SIZE; i++) {
			if(i<10)
				cards[i]=new NormalCard(i+1);	// carte normali (da 1 a 10)
			else
				cards[i]=new SpecialCard(i+1);	// carte speciali (da 11 a 20)	--> carte che fanno sia danno che cura sono considerate speciali
		}
	}
	
	public Card pick() {						// non serve mescolare il mazzo: le carte vengono estratte casualmente dal mazzo
		Random rand = new Random();
		return cards[rand.nextInt(SIZE)];
	}
	
	public Card getCard(int code) {
		return cards[code-1];					// essendo il mazzo infinto, le carte NON vengono rimosse all'estrazione
	}
}