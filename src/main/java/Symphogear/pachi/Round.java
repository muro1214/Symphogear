package Symphogear.pachi;

import Symphogear.lottery.LotteryListImpl;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Round implements LotteryListImpl {
  private String round;
  private String name;
  private int probability;
  private int point;

  @Override
  public String toString() {
    return String.format("%s (%s)", name, round);
  }
}