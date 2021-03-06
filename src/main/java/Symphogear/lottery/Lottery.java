package Symphogear.lottery;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Lottery implements LotteryImpl {
  private final Random random;
  private final static Lottery LOTTERY = new Lottery();

  private Lottery() {
    random = new Random(System.currentTimeMillis() + Runtime.getRuntime().freeMemory());
  }

  public static Lottery getInstance() {
    return LOTTERY;
  }

  @Override
  public boolean lot(int probability) {
    return lotOf(probability, 100);
  }

  @Override
  public boolean lotOf(int probability, int maxProbability) {
    if (probability <= 0) {
      return false;
    }

    if (probability >= maxProbability) {
      return true;
    }

    return random.nextInt(maxProbability) + 1 <= probability;
  }

  @Override
  public <T extends LotteryListImpl> T lots(List<T> lotteryList) throws IllegalArgumentException {
    var totalProbability = 0;
    for (var lottery : lotteryList) {
      totalProbability += lottery.getProbability();
    }

    if (totalProbability <= 0) {
      throw new IllegalArgumentException("total probability must be greater than 0.");
    }

    T result = null;
    var tempProbability = 0;
    var randomProbability = random.nextInt(totalProbability) + 1;
    for (var lottery : lotteryList) {
      tempProbability += lottery.getProbability();

      if (tempProbability >= randomProbability) {
        result = lottery;
        break;
      }
    }

    return result;
  }
}