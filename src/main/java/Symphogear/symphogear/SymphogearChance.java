package Symphogear.symphogear;

import java.util.Scanner;

import Symphogear.pachi.Pachi;
import Symphogear.pachi.Pending;
import Symphogear.pachi.PendingLottery;
import Symphogear.pachi.Round;
import Symphogear.pachi.RoundLottery;
import Symphogear.pachi.WinKind;
import Symphogear.util.PrintUtil;

public class SymphogearChance {
  private final int SC_MAX = 11;
  private final Pachi pachiData;

  public <T extends Pachi> SymphogearChance(T pachiData) {
    this.pachiData = pachiData;
  }

  @SuppressWarnings("resource")
  public boolean start() {
    Scanner scanner = new Scanner(System.in);
    WinKind winKind = WinKind.Miss;

    // 残り2回転までの処理(ラスト以外)
    for (int i = 0; i < SC_MAX - 5; i++) {
      //保留を4にする
      setPendingToFour();

      PrintUtil.printWithLine("あと " + (SC_MAX - 4 - i) + " 回");
      scanner.nextLine();

      // あたり判定
      winKind = Pending.getLotteryResult();
      if (PendingLottery.isWin(winKind)) {
        // V放出判定
        if (Pending.getVStockCount() > 0) {
          Pending.useVStock();
          PrintUtil.printlnUtf8("V放出！！\n");
        }

        break;
      }
    }

    // 当たってたらラウンド消化して終わる
    if (PendingLottery.isWin(winKind)) {
      executeRound(winKind);
      return true;
    }

    //保留を4にする
    setPendingToFour();

    // ラスト
    PrintUtil.printWithLine("ラスト！！");
    scanner.nextLine();
    for (int i = 0; i < 4; i++) {
      // あたり判定
      winKind = Pending.getLotteryResult();
      if (PendingLottery.isWin(winKind)) {
        break;
      }
    }

    // 当たってたらラウンド消化して終わる
    if (PendingLottery.isWin(winKind)) {
      executeRound(winKind);
      return true;
    }

    // まだ響と流れ星を見ていない！
    PrintUtil.printWithLine("流れ星がくるといいね");
    winKind = PendingLottery.lot(pachiData);
    scanner.nextLine();
    if (PendingLottery.isWin(winKind)) {
      PrintUtil.printlnUtf8("まだ響と流れ星を見ていない！！");
      executeRound(winKind);
      return true;
    }

    return false;
  }

  // ラウンド消化
  private void executeRound(WinKind winKind) {
    Round round = RoundLottery.lot(pachiData.getRounds());

    SCResult.registerRoundResult(round);
    RoundLottery.showRoundResult(winKind, pachiData, round);
  }

  // 保留が4になるまで貯める
  private void setPendingToFour() {
    int nowCount = Pending.getPendingCount();

    if (nowCount < 4) {
      for (int i = 0; i < 4 - nowCount; i++) {
        Pending.setLotteryResult(PendingLottery.lot(pachiData));
      }
    }
  }
}
