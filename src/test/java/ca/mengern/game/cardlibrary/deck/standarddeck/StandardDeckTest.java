package ca.mengern.game.cardlibrary.deck.standarddeck;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ca.mengern.game.cardlibrary.card.standardcard.StandardCard;
import ca.mengern.game.cardlibrary.card.standardcard.StandardCardRank;
import ca.mengern.game.cardlibrary.card.standardcard.StandardCardSuit;

class StandardDeckTest {

  @Test
  void testBuildOrdered52CardDeck() {
    StandardDeck deck = StandardDeck.buildOrdered52CardDeck();

    Assert.assertEquals(52, deck.size());

    StandardCard firstCard = deck.inspectTop();
    Assert.assertEquals(StandardCardRank.ACE, firstCard.getRank());
    Assert.assertEquals(StandardCardSuit.CLUB, firstCard.getSuit());

    StandardCard lastCard = deck.inspectBottom();
    Assert.assertEquals(StandardCardRank.KING, lastCard.getRank());
    Assert.assertEquals(StandardCardSuit.HEART, lastCard.getSuit());
  }

}
