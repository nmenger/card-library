package ca.mengern.game.cardlibrary.deck;

import java.util.Collections;
import java.util.LinkedList;

import ca.mengern.game.cardlibrary.card.Card;

public abstract class Deck<T extends Card> {

	protected LinkedList<T> deck;

	public Deck(LinkedList<T> deck) {
		super();
		this.deck = deck;
	}

	public T draw() {
		return deck.removeFirst();
	}

	/**
	 * Inspect the card located at a particular position
	 * 
	 * @param numberFromTop
	 *            The location of the card to inspect
	 * @return The {@link Card} located at that position
	 */
	public T inspect(int numberFromTop) {
		return deck.get(numberFromTop);
	}

	public T inspectTop() {
		return deck.get(0);
	}

	public T inspectBottom() {
		return deck.get(deck.size() - 1);
	}

	public int size() {
		return deck.size();
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}
}
