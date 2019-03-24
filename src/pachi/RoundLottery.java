package pachi;

import java.util.List;

import lottery.Lottery;
import util.PrintUtil;

public class RoundLottery {

  private RoundLottery() {
  }

  public static Round lot(List<Round> rounds) {
    Lottery lottery = Lottery.getInstance();

    return lottery.lots(rounds);
  }

  public static <T extends Pachi> void showRoundResult(WinKind winKind, T pachiData, Round round) {
    // V-STOCKが残っているときは、V-STOCK演出はでない・・・はず
    String fractional = winKind == WinKind.Normal ? pachiData.getNormalFraction() : pachiData.getKakuhenFraction();
    PrintUtil.printWithLine("大当たり!! (" + fractional + ")");

    if (round.getRound().equals("16R") && isExDrive() && Pending.getVStockCount() == 0) {
      PrintUtil.printlnUtf8("調がいる… (ry");
      PrintUtil.printlnUtf8("限 定 解 除\nエクスドライブ");
      PrintUtil.printlnUtf8("V-STOCK × " + Pending.getWinCount());
      PrintUtil.printlnUtf8(round.toString());
      Pending.setVStockCount(Pending.getWinCount());
    } else if (isVStock() && Pending.getVStockCount() == 0) {
      PrintUtil.printlnUtf8(round.toString());
      PrintUtil.printlnUtf8("これが私たちのおおお…絶唱だああああ");
      PrintUtil.printlnUtf8("V-STOCK × " + Pending.getWinCount());
      Pending.setVStockCount(Pending.getWinCount());
    } else {
      PrintUtil.printlnUtf8(round.toString());
    }

    PrintUtil.printlnUtf8();
  }

  private static boolean isExDrive() {
    Lottery lottery = Lottery.getInstance();

    return lottery.lot(60) && Pending.getWinCount() >= 2;
  }

  private static boolean isVStock() {
    Lottery lottery = Lottery.getInstance();

    return lottery.lot(60) && Pending.getWinCount() >= 1;
  }
}