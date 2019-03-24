package pachi;

import java.util.List;
import java.util.Optional;

public abstract class Pachi {
  private int normalNumer;
  private int normalDenom;
  private int kakuhenNumer;
  private int kakuhenDenom;
  private List<Round> rounds;

  public Pachi(int normalNumer, int normalDenom, int kakuhenNumer, int kakuhenDenom) {
    this.normalNumer = normalNumer;
    this.normalDenom = normalDenom;
    this.kakuhenNumer = kakuhenNumer;
    this.kakuhenDenom = kakuhenDenom;
  }

  protected void setRounds(List<Round> rounds) {
    this.rounds = rounds;
  }

  public abstract String getNormalFraction();

  public abstract String getKakuhenFraction();

  public int getNormalNumer() {
    return normalNumer;
  }

  public int getNormalDenom() {
    return normalDenom;
  }

  public int getKakuhenNumer() {
    return kakuhenNumer;
  }

  public int getKakuhenDenom() {
    return kakuhenDenom;
  }

  public List<Round> getRounds() {
    return rounds;
  }

  public Round getRound(String round) {
    Optional<Round> optRound = rounds.stream().filter(it -> it.getRound().equals(round)).findFirst();

    return optRound.orElseThrow(() -> new IllegalArgumentException(round + " is not found."));
  }
}
