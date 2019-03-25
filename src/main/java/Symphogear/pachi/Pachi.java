package Symphogear.pachi;

import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public abstract class Pachi {
  private final @Getter int normalNumer;
  private final @Getter int normalDenom;
  private final @Getter int kakuhenNumer;
  private final @Getter int kakuhenDenom;
  private @Getter List<Round> rounds;

  protected void setRounds(List<Round> rounds) {
    this.rounds = rounds;
  }

  public abstract String getNormalFraction();

  public abstract String getKakuhenFraction();

  public Round getRound(String round) {
    Optional<Round> optRound = rounds.stream().filter(it -> it.getRound().equals(round)).findFirst();

    return optRound.orElseThrow(() -> new IllegalArgumentException(round + " is not found."));
  }
}
