package ca.mengern.game.cardlibrary.deck.standarddeck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import ca.mengern.game.cardlibrary.card.standardcard.StandardCard;
import ca.mengern.game.cardlibrary.card.standardcard.StandardCardRank;
import ca.mengern.game.cardlibrary.card.standardcard.StandardCardSuit;
import ca.mengern.game.cardlibrary.deck.Deck;

public class StandardDeck extends Deck<StandardCard> {

	private StandardDeck(LinkedList<StandardCard> deck) {
		super(deck);
	}

	public static StandardDeck buildOrdered52CardDeck() {
		LinkedList<StandardCard> newDeck = new LinkedList<StandardCard>();

		// Natural enum order is by rank, we want ordered by draw order
		List<StandardCardRank> ranksByDrawOrder = new ArrayList<StandardCardRank>();
		ranksByDrawOrder.addAll(Arrays.asList(StandardCardRank.values()));
		ranksByDrawOrder.sort(StandardCardRank.ORDER_COMPARATOR);

		for (StandardCardSuit suit : StandardCardSuit.values()) {
			for (StandardCardRank rank : ranksByDrawOrder) {
				newDeck.add(new StandardCard(suit, rank));
			}
		}

		return new StandardDeck(newDeck);
	}
}
