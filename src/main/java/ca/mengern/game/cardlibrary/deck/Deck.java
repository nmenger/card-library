package ca.mengern.game.cardlibrary.deck;

import java.util.Collections;
import java.util.List;

import ca.mengern.game.cardlibrary.card.Card;

public abstract class Deck<T extends Card> {

  protected List<T> cards;

  public Deck(List<T> cards) {
    super();
    this.cards = cards;
  }

  public T draw() {
    return cards.remove(0);
  }

  /**
   * Inspect the card located at a particular position
   *
   * @param numberFromTop
   *          The location of the card to inspect
   * @return The {@link Card} located at that position
   */
  public T inspect(int numberFromTop) {
    return cards.get(numberFromTop);
  }

  public T inspectTop() {
    return cards.get(0);
  }

  public T inspectBottom() {
    return cards.get(cards.size() - 1);
  }

  public int size() {
    return cards.size();
  }

  public void shuffle() {
    Collections.shuffle(cards);
  }
}
