package Symphogear.main;

import Symphogear.pachi.PendingLottery;
import Symphogear.symphogear.LastBattle;
import Symphogear.symphogear.NormalVer;
import Symphogear.symphogear.SCResult;
import Symphogear.symphogear.SymphogearChance;
import Symphogear.util.PrintUtil;

public class Main {
  private final static String VERSION = "0.0.2";

  public static void main(String[] args) {
    PrintUtil.printlnUtf8("CRF戦姫絶唱シンフォギアシミュレーター Version = " + VERSION + "\n");

    // 台データを生成する
    var pachiData = new NormalVer();

    // 全回転なら最終決戦をスキップする
    if (PendingLottery.isZenkaiten(pachiData)) {
      // 初当たり(16R)のデータを登録する
      SCResult.registerRoundResult(pachiData.getRound("16R"));

      PrintUtil.printlnUtf8("全回転で当たったため、最終決戦をスキップします");
    } else {
      // 初当たり(4R)のデータを登録する
      SCResult.registerRoundResult(pachiData.getRound("4R"));

      // 最終決戦
      var lastBattle = new LastBattle(pachiData);
      var isWin = lastBattle.start();

      if (!isWin) {
        PrintUtil.printlnUtf8("不承不承ながら、左打ちしましょう");
        return;
      }
    }

    // シンフォギアチャンス
    PrintUtil.printlnUtf8("シンフォギアチャンス突入！！");
    var symphogearChance = new SymphogearChance(pachiData);
    while (symphogearChance.start()) {
      PrintUtil.printlnUtf8("シンフォギアチャンス継続！！");
    }

    // リザルト
    PrintUtil.printWithLine("リザルト");
    PrintUtil.printlnUtf8("FEVER × " + SCResult.getTotalRound());
    PrintUtil.printlnUtf8("TOTAL × " + SCResult.getTotalPoint() + " pt\n");

    PrintUtil.printlnUtf8("16R × " + SCResult.getRoundResult("16R"));
    PrintUtil.printlnUtf8("12R × " + SCResult.getRoundResult("12R"));
    PrintUtil.printlnUtf8("8R × " + SCResult.getRoundResult("8R"));
    PrintUtil.printlnUtf8("4R × " + SCResult.getRoundResult("4R") + "\n");

    PrintUtil.printlnUtf8("不承不承ながら、左打ちしましょう");
  }
}