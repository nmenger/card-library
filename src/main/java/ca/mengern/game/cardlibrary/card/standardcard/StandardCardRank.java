package ca.mengern.game.cardlibrary.card.standardcard;

import java.util.Comparator;

public enum StandardCardRank {

  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8),
  NINE(9),
  TEN(10),
  JACK(11),
  QUEEN(12),
  KING(13),
  ACE(1); // Highest card, but comes before a two in an unshuffled pack of cards

  public static Comparator<StandardCardRank> ORDER_COMPARATOR = (a, b) -> a.getOrder() - b.getOrder();

  /**
   * The order that the cards come in an unshuffled pack of cards
   */
  private final int order;

  private StandardCardRank(int order) {
    this.order = order;
  }

  public int getOrder() {
    return order;
  }
}
