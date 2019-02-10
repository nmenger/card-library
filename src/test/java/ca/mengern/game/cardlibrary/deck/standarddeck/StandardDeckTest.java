package ca.mengern.game.cardlibrary.deck.standarddeck;

import org.junit.jupiter.api.Test;

import ca.mengern.game.cardlibrary.card.standardcard.StandardCard;
import ca.mengern.game.cardlibrary.card.standardcard.StandardCardRank;
import ca.mengern.game.cardlibrary.card.standardcard.StandardCardSuit;
import ca.mengern.game.cardlibrary.deck.standarddeck.StandardDeck;

class StandardDeckTest {

	@Test
	void testBuildOrdered52CardDeck() {
		StandardDeck deck = StandardDeck.buildOrdered52CardDeck();

		assert (deck.size() == 52);

		StandardCard firstCard = deck.inspectTop();
		assert (firstCard.getRank() == StandardCardRank.ACE);
		assert (firstCard.getSuit() == StandardCardSuit.CLUB);

		StandardCard lastCard = deck.inspectBottom();
		assert (lastCard.getRank() == StandardCardRank.KING);
		assert (lastCard.getSuit() == StandardCardSuit.HEART);
	}

}
