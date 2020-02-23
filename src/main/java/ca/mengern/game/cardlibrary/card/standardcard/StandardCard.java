package ca.mengern.game.cardlibrary.card.standardcard;

import ca.mengern.game.cardlibrary.card.Card;

public class StandardCard extends Card {

  private final StandardCardSuit suit;
  private final StandardCardRank rank;
  private final String name;

  public StandardCard(StandardCardSuit suit, StandardCardRank rank) {
    super();
    this.suit = suit;
    this.rank = rank;

    name = new StringBuilder().append(rank.name()).append(" OF ").append(suit.name()).append("S").toString();
  }

  public StandardCardSuit getSuit() {
    return suit;
  }

  public StandardCardRank getRank() {
    return rank;
  }

  @Override
  public String toString() {
    return name;
  }
}
