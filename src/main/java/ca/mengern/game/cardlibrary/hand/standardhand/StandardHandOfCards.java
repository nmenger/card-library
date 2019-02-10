package ca.mengern.game.cardlibrary.hand.standardhand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.mengern.game.cardlibrary.card.standardcard.StandardCard;
import ca.mengern.game.cardlibrary.card.standardcard.StandardCardRank;
import ca.mengern.game.cardlibrary.card.standardcard.StandardCardSuit;
import ca.mengern.game.cardlibrary.deck.standarddeck.StandardDeck;
import ca.mengern.game.cardlibrary.hand.HandOfCards;

/**
 * @author PC1
 *
 */
public class StandardHandOfCards extends HandOfCards<StandardCard> {

	/**
	 * Sorted hand ranks.
	 * 
	 * <p>
	 * In a 52-card deck, the first card would be the ace of spaces, and the last
	 * card the two of clubs
	 */
	protected final List<StandardCardRank> sortedHandRanks = new ArrayList<StandardCardRank>();
	protected final Map<StandardCardRank, List<StandardCard>> rankToCardMap = new HashMap<StandardCardRank, List<StandardCard>>();

	public StandardHandOfCards(StandardDeck deck, int numCards) {
		super(deck, numCards);
		syncHand();
	}

	public StandardHandOfCards(StandardCard... cards) {
		super(cards);
		syncHand();
	}

	public StandardHandOfCards(List<StandardCard> cards) {
		super(cards);
		syncHand();
	}

	public boolean addCardToHand(StandardCard card) {
		boolean result = addCard(card);
		syncHand();
		return result;
	}

	public boolean removeCardFromHand(StandardCard card) {
		boolean result = removeCard(card);
		syncHand();
		return result;
	}

	/**
	 * Removes the passed in {@code List} of {@code StandardCard} and returns a
	 * smaller {@code StandardHandOfCards} containing the remaining cards
	 * 
	 * @param cardsToRemove
	 *            The {@code List} of {@code StandardCard}s to remove
	 * @return A {@code StandardHandOfCards} containing the remaining cards
	 */
	public StandardHandOfCards removeAll(List<StandardCard> cardsToRemove) {
		List<StandardCard> copyOfHand = new ArrayList<StandardCard>(hand);
		copyOfHand.removeAll(cardsToRemove);
		return new StandardHandOfCards(copyOfHand);
	}

	/**
	 * Sync's the {@code sortedHand} with the unsorted {@code hand}
	 * 
	 * <p>
	 * Should be called after any modification to {@code hand}
	 */
	private void syncHand() {
		// Build the sorted ranks of cards
		sortedHandRanks.clear();
		for (StandardCard card : hand) {
			sortedHandRanks.add(card.getRank());
		}
		Collections.sort(sortedHandRanks);

		// Build the rank to card map
		rankToCardMap.clear();
		for (StandardCard card : hand) {
			StandardCardRank rank = card.getRank();
			List<StandardCard> listOfCardsOfThatRank = rankToCardMap.get(rank);

			if (listOfCardsOfThatRank == null) {
				listOfCardsOfThatRank = new ArrayList<StandardCard>();
			}

			listOfCardsOfThatRank.add(card);
			rankToCardMap.put(rank, listOfCardsOfThatRank);
		}
	}

	protected boolean isFlush() {
		StandardCardSuit firstCardSuit = null;

		for (StandardCard card : hand) {
			if (firstCardSuit == null) {
				firstCardSuit = card.getSuit();
			} else if (card.getSuit() != firstCardSuit) {
				return false;
			}
		}

		return true;
	}

	protected boolean isStraight() {
		return isRegularStraight(sortedHandRanks) || isWheelStraight(sortedHandRanks);
	}

	protected static StandardCardRank getHighestRank(List<StandardCardRank> handRanks) {
		// Don't modify the incoming collection
		List<StandardCardRank> sortedHandRanks = new ArrayList<StandardCardRank>(handRanks);
		Collections.sort(sortedHandRanks);

		return sortedHandRanks.get(sortedHandRanks.size() - 1);
	}

	protected static boolean isRegularStraight(List<StandardCardRank> handRanks) {

		// Don't modify the incoming collection
		List<StandardCardRank> sortedHandRanks = new ArrayList<StandardCardRank>(handRanks);
		Collections.sort(sortedHandRanks);

		StandardCardRank lastRank = null;

		for (StandardCardRank cardRank : sortedHandRanks) {
			if (lastRank != null) {
				// The last card should be 1 higher than this card
				if (cardRank.compareTo(lastRank) != 1) {
					return false;
				}
			}

			lastRank = cardRank;
		}

		return true;
	}

	protected static boolean isWheelStraight(List<StandardCardRank> handRanks) {

		// Don't modify the incoming collection
		List<StandardCardRank> sortedHandRanks = new ArrayList<StandardCardRank>(handRanks);
		Collections.sort(sortedHandRanks);

		// Highest card must be an ace
		if (getHighestRank(sortedHandRanks) != StandardCardRank.ACE) {
			return false;
		}

		List<StandardCardRank> remainingCardRanks = new ArrayList<StandardCardRank>(sortedHandRanks);
		remainingCardRanks.remove(StandardCardRank.ACE);

		return isRegularStraight(remainingCardRanks) && getHighestRank(remainingCardRanks) == StandardCardRank.FIVE;
	}

	/**
	 * Finds and returns the first list of cards that have the number of duplicate
	 * ranks. In other words, to find the first pair, use a value of 2, whereas to
	 * find the first three-of-a-kind, use a value of 3
	 * 
	 * <p>
	 * Note: A list is used instead of a set since there may be duplicate cards
	 * within this class
	 * 
	 * @param numDuplicateRank
	 *            The number of duplicate ranked cards to look for
	 * @return The first list of cards that are of duplicate rank
	 */
	public List<StandardCard> getFirstBunchOfDuplicates(int numDuplicateRank) {

		for (List<StandardCard> listOfCardsOfThatRank : rankToCardMap.values()) {
			if (listOfCardsOfThatRank.size() >= numDuplicateRank) {
				return listOfCardsOfThatRank;
			}
		}

		return null;
	}

	public List<StandardCardRank> getSortedHandRanks() {
		return sortedHandRanks;
	}
}
