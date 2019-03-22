package symphogear;

import lottery.LotteryListImpl;

/**
 * 最終決戦のキャラクターパネル
 * @author smuro
 *
 */
public class LastBattlePanel implements LotteryListImpl {
  /** キャラ名 */
  private String name;
  /** しゃべるセリフ */
  private String word;
  /** 発生確率 */
  private int probability;
  /** 絶唱かどうか */
  private boolean zessho;

  public LastBattlePanel(String name, String word, int probability, boolean zessho) {
    this.name = name;
    this.word = word;
    this.probability = probability;
    this.zessho = zessho;
  }

  public String getName() {
    return name;
  }

  public String getWord() {
    return word;
  }

  @Override
  public int getProbability() {
    return probability;
  }

  public boolean isZessho() {
    return zessho;
  }

  @Override
  public String toString() {
    return String.format("%s 「%s」, %d %%", name, word, probability);
  }
}