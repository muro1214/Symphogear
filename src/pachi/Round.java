package pachi;

import lottery.LotteryListImpl;

/**
 * ラウンド振り分け
 * @author smuro
 *
 */
public class Round implements LotteryListImpl {
  private String round;
  private String name;
  private int probability;
  private int point;

  public Round(String round, String name, int probability, int point) {
    this.round = round;
    this.name = name;
    this.probability = probability;
    this.point = point;
  }

  public String getRound() {
    return round;
  }

  public String getName() {
    return name;
  }

  @Override
  public int getProbability() {
    return probability;
  }

  public int getPoint() {
    return point;
  }

  @Override
  public String toString() {
    return String.format("%s (%s)", name, round);
  }
}