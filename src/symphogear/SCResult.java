package symphogear;

import java.util.HashMap;
import java.util.Map;

import pachi.Round;

/**
 * シンフォギアチャンスの結果
 * @author smuro
 *
 */
public class SCResult {
  private static int totalRound = 0;
  private static int totalPoint = 0;
  private static Map<String, Integer> roundResult = new HashMap<>();

  static {
    roundResult.put("16R", 0);
    roundResult.put("12R", 0);
    roundResult.put("8R", 0);
    roundResult.put("4R", 0);
  }

  private SCResult() {
  }

  public static void registerRoundResult(Round round) {
    totalRound++;
    totalPoint += round.getPoint();
    roundResult.put(round.getRound(), roundResult.get(round.getRound()) + 1);
  }

  public static int getTotalRound() {
    return totalRound;
  }

  public static int getTotalPoint() {
    return totalPoint;
  }

  public static int getRoundResult(String round) {
    return roundResult.get(round);
  }
}