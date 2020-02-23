package ca.mengern.game.cardlibrary.hand.standardhand;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ca.mengern.game.cardlibrary.card.standardcard.StandardCardRank;

class TestStandardCardRank {

  @Test
  void testAcesHigherThanKings() {
    assertThat(StandardCardRank.ACE.compareTo(StandardCardRank.KING)).isGreaterThan(0);
  }

  @Test
  void testKingsHigherThanTwos() {
    assertThat(StandardCardRank.KING.compareTo(StandardCardRank.TWO)).isGreaterThan(0);
  }
}
