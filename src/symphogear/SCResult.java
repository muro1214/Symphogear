package symphogear;

import java.util.HashMap;
import java.util.Map;

/**
 * シンフォギアチャンスの結果
 * @author smuro
 *
 */
public class SCResult {
  // 最終決戦突入時のラウンドは4Rで確定なので、初期値は4Rの値にする
  private static int totalRound = 1;
  private static int totalPoint = 392;
  private static Map<String, Integer> roundResult = new HashMap<>();

  static {
    roundResult.put("16R", 0);
    roundResult.put("12R", 0);
    roundResult.put("8R", 0);
    roundResult.put("4R", 1);
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