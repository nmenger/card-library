package ca.mengern.game.cardlibrary.card.standardcard;

public enum StandardCardSuit {

  CLUB(StandardCardColour.BLACK),
  DIAMOND(StandardCardColour.RED),
  SPADE(StandardCardColour.BLACK),
  HEART(StandardCardColour.RED);

  private final StandardCardColour cardColour;

  private StandardCardSuit(StandardCardColour cardColour) {
    this.cardColour = cardColour;
  }

  public StandardCardColour getCardColour() {
    return cardColour;
  }
}
