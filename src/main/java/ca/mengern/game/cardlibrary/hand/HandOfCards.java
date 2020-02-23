package ca.mengern.game.cardlibrary.hand;

import java.util.ArrayList;
import java.util.List;

import ca.mengern.game.cardlibrary.card.Card;
import ca.mengern.game.cardlibrary.deck.Deck;

public class HandOfCards<T extends Card> {

  protected final List<T> hand = new ArrayList<T>();

  protected HandOfCards(Deck<T> deck, int numCards) {
    for (int i = 0; i < numCards; i++) {
      addCard(deck.draw());
    }
  }

  @SafeVarargs
  protected HandOfCards(T... cards) {
    for (T card : cards) {
      hand.add(card);
    }
  }

  protected HandOfCards(List<T> cards) {
    for (T card : cards) {
      hand.add(card);
    }
  }

  protected final boolean addCard(T card) {
    return hand.add(card);
  }

  protected final boolean removeCard(T card) {
    return hand.remove(card);
  }

  public List<T> getHand() {
    return hand;
  }

  public int size() {
    return hand.size();
  }
}
