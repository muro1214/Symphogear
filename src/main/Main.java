package main;

import java.util.Objects;

import symphogear.LastBattle;
import symphogear.Round;
import symphogear.RoundLottery;
import symphogear.SCResult;
import symphogear.SymphogearChance;
import util.PrintUtil;

public class Main {

  //TODO: 1種2種複合にしないといけない
  public static void main(String[] args) {
    // 最終決戦
    LastBattle lastBattle = new LastBattle();
    Round round = lastBattle.start();

    if (Objects.isNull(round)) {
      PrintUtil.printlnUtf8("不承不承ながら、左打ちしましょう");
      return;
    }

    SCResult.registerRoundResult(round);
    RoundLottery.showRoundResult(round);

    // シンフォギアチャンス
    PrintUtil.printlnUtf8("シンフォギアチャンス突入！！");
    SymphogearChance symphogearChance = new SymphogearChance();
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