package symphogear;

import java.util.Scanner;

import lottery.Lottery;
import util.PrintUtil;

public class SymphogearChance {
  private final int SC_MAX = 11;
  private final int NUMER = 10;
  private final int DENOM = 74;

  private Lottery lottery = new Lottery();

  @SuppressWarnings("resource")
  public boolean start() {
    Scanner scanner = new Scanner(System.in);
    boolean win = false;

    // 残り2回転までの処理(ラスト以外)
    for (int i = 0; i < SC_MAX - 5; i++) {
      //保留を4にする
      setPendingToFour();

      PrintUtil.printWithLine("あと " + (SC_MAX - 4 - i) + " 回");
      scanner.nextLine();

      // あたり判定
      if (Pending.getLotteryResult()) {
        // V放出判定
        if (Pending.getVStockCount() > 0) {
          Pending.useVStock();
          PrintUtil.printlnUtf8("V放出！！\n");
        }

        win = true;
        break;
      }
    }

    // 当たってたらラウンド消化して終わる
    if (win) {
      executeRound();
      return true;
    }

    //保留を4にする
    setPendingToFour();

    // ラスト
    PrintUtil.printWithLine("ラスト！！");
    scanner.nextLine();
    for (int i = 0; i < 4; i++) {
      // あたり判定
      if (Pending.getLotteryResult()) {
        win = true;
        break;
      }
    }

    // 当たってたらラウンド消化して終わる
    if (win) {
      executeRound();
      return true;
    }

    // まだ響と流れ星を見ていない！
    PrintUtil.printWithLine("流れ星がくるといいね");
    scanner.nextLine();
    if (lottery.lotOf(NUMER, DENOM)) {
      PrintUtil.printlnUtf8("まだ響と流れ星を見ていない！！");
      executeRound();
      return true;
    }

    return false;
  }

  // ラウンド消化
  private void executeRound() {
    Round round = RoundLottery.lot();

    SCResult.registerRoundResult(round);
    RoundLottery.showRoundResult(round);
  }

  // 保留が4になるまで貯める
  private void setPendingToFour() {
    int nowCount = Pending.getPendingCount();

    if (nowCount < 4) {
      for (int i = 0; i < 4 - nowCount; i++) {
        Pending.setLotteryResult(lottery.lotOf(NUMER, DENOM));
      }
    }
  }
}
