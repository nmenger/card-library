package ca.mengern.game.cardlibrary.hand.standardhand;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ca.mengern.game.cardlibrary.card.standardcard.StandardCard;
import ca.mengern.game.cardlibrary.card.standardcard.StandardCardRank;
import ca.mengern.game.cardlibrary.card.standardcard.StandardCardSuit;
import ca.mengern.game.cardlibrary.deck.standarddeck.StandardDeck;

class TestStandardHandOfCards {

  private static final StandardCard ACE_SPADES = new StandardCard(StandardCardSuit.SPADE, StandardCardRank.ACE);
  private static final StandardCard ACE_CLUBS = new StandardCard(StandardCardSuit.CLUB, StandardCardRank.ACE);
  private static final StandardCard KING_HEARTS = new StandardCard(StandardCardSuit.HEART, StandardCardRank.KING);
  private static final StandardCard QUEEN_HEARTS = new StandardCard(StandardCardSuit.HEART, StandardCardRank.QUEEN);
  private static final StandardCard JACK_HEARTS = new StandardCard(StandardCardSuit.HEART, StandardCardRank.JACK);
  private static final StandardCard TEN_HEARTS = new StandardCard(StandardCardSuit.HEART, StandardCardRank.TEN);
  private static final StandardCard NINE_DIAMONDS = new StandardCard(StandardCardSuit.DIAMOND, StandardCardRank.NINE);

  private static final StandardCard TWO_CLUBS = new StandardCard(StandardCardSuit.CLUB, StandardCardRank.TWO);
  private static final StandardCard THREE_DIAMONDS = new StandardCard(StandardCardSuit.DIAMOND, StandardCardRank.THREE);
  private static final StandardCard FOUR_DIAMONDS = new StandardCard(StandardCardSuit.DIAMOND, StandardCardRank.FOUR);
  private static final StandardCard FIVE_DIAMONDS = new StandardCard(StandardCardSuit.DIAMOND, StandardCardRank.FIVE);
  private static final StandardCard SIX_DIAMONDS = new StandardCard(StandardCardSuit.DIAMOND, StandardCardRank.SIX);
  private static final StandardCard SEVEN_DIAMONDS = new StandardCard(StandardCardSuit.DIAMOND, StandardCardRank.SEVEN);

  @Test
  void testConstructors() {
    StandardDeck deck = StandardDeck.buildOrdered52CardDeck();

    StandardHandOfCards hand = new StandardHandOfCards(deck, 4);
    assertTrue("Internal representations must be same size", hand.sortedHandRanks.size() == hand.getHand().size());

    StandardHandOfCards newHand = new StandardHandOfCards(hand.getHand());
    assertTrue("Internal representations must be same size",
        newHand.sortedHandRanks.size() == newHand.getHand().size());

    hand = new StandardHandOfCards(NINE_DIAMONDS, THREE_DIAMONDS, FOUR_DIAMONDS, FIVE_DIAMONDS, SEVEN_DIAMONDS);
    assertTrue("Internal representations must be same size", hand.sortedHandRanks.size() == hand.getHand().size());
  }

  @Test
  void testSyncedHands() {
    StandardHandOfCards hand = new StandardHandOfCards(NINE_DIAMONDS, THREE_DIAMONDS, FOUR_DIAMONDS, FIVE_DIAMONDS,
        SEVEN_DIAMONDS);
    assertTrue("Internal representations must be same size", hand.sortedHandRanks.size() == hand.getHand().size());

    hand.addCardToHand(QUEEN_HEARTS);
    assertTrue("Internal representations must be same size", hand.sortedHandRanks.size() == hand.getHand().size());

    hand.removeCardFromHand(QUEEN_HEARTS);
    assertTrue("Internal representations must be same size", hand.sortedHandRanks.size() == hand.getHand().size());
  }

  @Test
  void testGetHighestRank() {
    StandardHandOfCards hand = new StandardHandOfCards(ACE_SPADES, KING_HEARTS);
    assertTrue("Aces must be higher than kings",
        StandardHandOfCards.getHighestRank(hand.getSortedHandRanks()) == StandardCardRank.ACE);

    hand = new StandardHandOfCards(TWO_CLUBS, KING_HEARTS);
    assertTrue("Kings must be higher than twos",
        StandardHandOfCards.getHighestRank(hand.getSortedHandRanks()) == StandardCardRank.KING);
  }

  @Test
  void testIsStraight() {
    StandardHandOfCards hand = new StandardHandOfCards(ACE_SPADES, ACE_CLUBS, TWO_CLUBS, THREE_DIAMONDS, FOUR_DIAMONDS);
    assertFalse("Two aces cannot be a straight", hand.isStraight());

    hand = new StandardHandOfCards(TWO_CLUBS, THREE_DIAMONDS, FOUR_DIAMONDS, FIVE_DIAMONDS, SIX_DIAMONDS);
    assertTrue("This should be a straight", hand.isStraight());

    hand = new StandardHandOfCards(TWO_CLUBS, THREE_DIAMONDS, FOUR_DIAMONDS, FIVE_DIAMONDS, SEVEN_DIAMONDS);
    assertFalse("This is not a straight", hand.isStraight());

    hand = new StandardHandOfCards(TEN_HEARTS, JACK_HEARTS, QUEEN_HEARTS, KING_HEARTS, ACE_CLUBS);
    assertTrue("This should be an ace-high straight", hand.isStraight());
  }

  @Test
  void testWheelStraight() {
    StandardHandOfCards hand = new StandardHandOfCards(ACE_CLUBS, TWO_CLUBS, THREE_DIAMONDS, FOUR_DIAMONDS,
        FIVE_DIAMONDS);
    assertTrue("This should be a wheel straight", hand.isStraight());

    hand = new StandardHandOfCards(TWO_CLUBS, THREE_DIAMONDS, FOUR_DIAMONDS, FIVE_DIAMONDS, SEVEN_DIAMONDS);
    assertFalse("This is not a wheel straight", hand.isStraight());

    hand = new StandardHandOfCards(ACE_CLUBS, SIX_DIAMONDS, THREE_DIAMONDS, FOUR_DIAMONDS, FIVE_DIAMONDS);
    assertFalse("This is not a wheel straight", hand.isStraight());

    hand = new StandardHandOfCards(ACE_CLUBS, SIX_DIAMONDS, TWO_CLUBS, FOUR_DIAMONDS, FIVE_DIAMONDS);
    assertFalse("This is not a wheel straight", hand.isStraight());
  }

  @Test
  void testIsFlush() {
    StandardHandOfCards hand = new StandardHandOfCards(NINE_DIAMONDS, THREE_DIAMONDS, FOUR_DIAMONDS, FIVE_DIAMONDS,
        SEVEN_DIAMONDS);
    assertTrue("This should be a flush", hand.isFlush());

    hand = new StandardHandOfCards(TEN_HEARTS, THREE_DIAMONDS, FOUR_DIAMONDS, FIVE_DIAMONDS, SEVEN_DIAMONDS);
    assertFalse("This is not a flush", hand.isFlush());
  }

  @Test
  void testGetFirstBunchOfDuplicates() {
    StandardHandOfCards hand = new StandardHandOfCards(NINE_DIAMONDS, THREE_DIAMONDS, FOUR_DIAMONDS, FIVE_DIAMONDS,
        SEVEN_DIAMONDS, ACE_CLUBS, ACE_SPADES, ACE_CLUBS);

    List<StandardCard> threeOfAKind = hand.getFirstBunchOfDuplicates(3);

    assertTrue("There should be a three of a kind", threeOfAKind.size() == 3);
    for (StandardCard card : threeOfAKind) {
      assertTrue("All three cards should be aces", card.getRank() == StandardCardRank.ACE);
    }

    List<StandardCard> fourOfAKind = hand.getFirstBunchOfDuplicates(4);
    assertTrue("There is no four of a kind", fourOfAKind == null);
  }

  @Test
  void testRemoveAll() {
    StandardHandOfCards hand = new StandardHandOfCards(NINE_DIAMONDS, THREE_DIAMONDS, FOUR_DIAMONDS, FIVE_DIAMONDS,
        SEVEN_DIAMONDS);

    List<StandardCard> cardsToRemove = new ArrayList<StandardCard>();
    cardsToRemove.add(NINE_DIAMONDS); // in the hand
    cardsToRemove.add(THREE_DIAMONDS); // in the hand
    cardsToRemove.add(TEN_HEARTS); // not in the hand

    StandardHandOfCards remainingCards = hand.removeAll(cardsToRemove);

    assertTrue("There should be 3 cards left", remainingCards.size() == 3);

  }
}
