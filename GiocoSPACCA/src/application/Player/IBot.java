package application.Player;

import java.util.Collection;
import application.Card.Card;

public interface IBot {
	public Card play(Collection<Card> hand);
}
