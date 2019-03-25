package Symphogear.symphogear;

import Symphogear.lottery.LotteryListImpl;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class LastBattlePanel implements LotteryListImpl {
  /** キャラ名 */
  private String name;
  /** しゃべるセリフ */
  private String word;
  /** 発生確率 */
  private int probability;
  /** 絶唱かどうか */
  private boolean zessho;

  @Override
  public String toString() {
    return String.format("%s 「%s」, %d %%", name, word, probability);
  }
}