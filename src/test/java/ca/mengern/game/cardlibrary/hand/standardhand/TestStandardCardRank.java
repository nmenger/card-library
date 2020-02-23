package ca.mengern.game.cardlibrary.hand.standardhand;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import ca.mengern.game.cardlibrary.card.standardcard.StandardCardRank;

class TestStandardCardRank {

  @Test
  void testAcesHigherThanKings() {
    assertTrue("Aces are higher than kings", StandardCardRank.ACE.compareTo(StandardCardRank.KING) > 0);
  }

  @Test
  void testKingsHigherThanTwos() {
    assertTrue("Kings are higher than twos", StandardCardRank.KING.compareTo(StandardCardRank.TWO) > 0);
  }

}
