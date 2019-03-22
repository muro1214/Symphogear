package symphogear;

import java.util.ArrayList;
import java.util.List;

import lottery.Lottery;
import util.PrintUtil;

public class RoundLottery {
  private static Lottery lottery = new Lottery();
  private static List<Round> rounds = new ArrayList<>();

  static {
    rounds.add(new Round("16R", "SPECIAL FEVER", 40, 1470));
    rounds.add(new Round("12R", "ギアV-COMBO", 3, 1176));
    rounds.add(new Round("8R", "ギアV-COMBO", 7, 784));
    rounds.add(new Round("4R", "FEVER", 50, 392));
  }

  private RoundLottery() {
  }

  public static Round lot() {
    return lottery.lots(rounds);
  }

  public static void showRoundResult(Round round) {
    // V-STOCKが残っているときは、V-STOCK演出はでない・・・はず
    PrintUtil.printWithLine("大当たり!!");

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
    return lottery.lot(60) && Pending.getWinCount() >= 2;
  }

  private static boolean isVStock() {
    return lottery.lot(60) && Pending.getWinCount() >= 1;
  }
}