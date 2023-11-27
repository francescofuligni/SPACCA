package application.Player;

import java.util.Collection;
import application.Card.Card;

public interface Bot {
	public Card play(Collection<Card> hand);
}
