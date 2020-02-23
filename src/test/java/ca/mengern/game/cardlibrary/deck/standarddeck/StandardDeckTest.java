package ca.mengern.game.cardlibrary.deck.standarddeck;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ca.mengern.game.cardlibrary.card.standardcard.StandardCard;
import ca.mengern.game.cardlibrary.card.standardcard.StandardCardRank;
import ca.mengern.game.cardlibrary.card.standardcard.StandardCardSuit;

class StandardDeckTest {

  @Test
  void testBuildOrdered52CardDeck() {
    StandardDeck deck = StandardDeck.buildOrdered52CardDeck();

    assertThat(deck.size()).isEqualTo(52);

    StandardCard firstCard = deck.inspectTop();
    assertThat(firstCard.getRank()).isEqualTo(StandardCardRank.ACE);
    assertThat(firstCard.getSuit()).isEqualTo(StandardCardSuit.CLUB);

    StandardCard lastCard = deck.inspectBottom();
    assertThat(lastCard.getRank()).isEqualTo(StandardCardRank.KING);
    assertThat(lastCard.getSuit()).isEqualTo(StandardCardSuit.HEART);
  }

}
