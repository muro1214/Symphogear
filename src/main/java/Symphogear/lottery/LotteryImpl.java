package Symphogear.lottery;

import java.util.List;

public interface LotteryImpl {

  /**
   * 百分率で指定された確率に合致すればtrueを返す
   * @param probability [int] 確率(20%なら、20と指定する)
   * @return [boolean] 確率に合致すればtrue
   */
  public abstract boolean lot(int probability);

  public abstract boolean lotOf(int probability, int maxProbability);

  public abstract <T extends LotteryListImpl> T lots(List<T> lotteryList) throws IllegalArgumentException;
}