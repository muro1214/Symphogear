package symphogear;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import lottery.Lottery;
import util.PrintUtil;

public class LastBattle {
  private final int BATTLE_MAX = 5;
  private final int NUMER = 10;
  private final int DENOM = 74;
  private final List<LastBattlePanel> noFeverPanels = new ArrayList<>();
  private final List<LastBattlePanel> feverPanels = new ArrayList<>();
  private final LastBattlePanel rainbowPanel = new LastBattlePanel("特異災害対策機動部二課", "響き合うみんなの歌声がくれた…シンフォギアでぇぇぇぇぇぇ！!", 0,
      false);

  private Lottery lottery = new Lottery();

  public LastBattle() {
    noFeverPanels.add(new LastBattlePanel("雪音 クリス", "手加減なしだぜぇ！", 50, false));
    noFeverPanels.add(new LastBattlePanel("風鳴 翼", "推して参る！", 30, false));
    noFeverPanels.add(new LastBattlePanel("立花 響", "この衝動に塗り潰されてなるものか！", 19, false));
    noFeverPanels.add(new LastBattlePanel("絶唱", "ｶﾞﾄｩﾗﾝﾃﾞｨｽﾊﾞｰﾍﾞﾙｼﾞｰｸﾞﾚｯﾄｴｰﾃﾞﾙﾅｰｰﾙ…", 1, true));

    feverPanels.add(new LastBattlePanel("雪音 クリス", "手加減なしだぜぇ！", 25, false));
    feverPanels.add(new LastBattlePanel("風鳴 翼", "推して参る！", 40, false));
    feverPanels.add(new LastBattlePanel("立花 響", "この衝動に塗り潰されてなるものか！", 25, false));
    feverPanels.add(new LastBattlePanel("絶唱", "ｶﾞﾄｩﾗﾝﾃﾞｨｽﾊﾞｰﾍﾞﾙｼﾞｰｸﾞﾚｯﾄｴｰﾃﾞﾙﾅｰｰﾙ…", 10, true));
  }

  @SuppressWarnings("resource")
  public Round start() {
    PrintUtil.printlnUtf8("逆鱗に触れたのだ。相応の覚悟は出来ておろうな\n");
    PrintUtil.printlnUtf8("最  終  決  戦  開  幕\n");

    // 予め5回転分抽選して、結果とパネルを作る
    List<Boolean> result = makeResult();
    List<LastBattlePanel> panels = makePanel(result);

    Scanner scanner = new Scanner(System.in);
    Round round = null;

    for (int i = 0; i < BATTLE_MAX; i++) {
      PrintUtil.printWithLine("あと " + (BATTLE_MAX - i) + " 回");
      boolean thisResult = result.get(i);

      if (thisResult) {
        round = RoundLottery.lot();
      }

      // 虹パネル昇格抽選
      // 16R大当たり、かつ、3%の確率らしいですわよ
      boolean winRainbow = false;
      if (Objects.nonNull(round) && round.getRound().equals("16R")) {
        winRainbow = lottery.lot(3);
      }

      // 推して参る！
      printWord(winRainbow ? rainbowPanel : panels.get(i), thisResult);
      scanner.nextLine();

      if (thisResult) {
        PrintUtil.printlnUtf8(
            "ﾄﾞﾋｭｩｩｩｩﾝシンフォギアァァァァ!!!ｷｭｷｭｷｭｷｭｲﾝ!ｷｭｷｭｷｭｷｭｲﾝ!ｷｭｷｭｷｭｷｭｷｭｷｭｷｭｷｭｷｭｷｭｷｭｷｭｷｭｲﾝ!ﾎﾟｫﾛﾎﾟﾎﾟﾎﾟﾎﾟﾍﾟﾍﾟﾍﾟﾍﾟﾋﾟﾋﾟﾋﾟﾋﾟﾋﾟｰﾍﾟﾍﾟﾍﾟﾍﾟﾍﾟﾍﾟﾍﾟﾍﾟｰ♪");
        PrintUtil.printlnUtf8("この身、砕けてなるものかあああああああーーー！！！！\n");

        // 残保留の抽選結果を格納しておく
        for (int j = i + 1; j < BATTLE_MAX; j++) {
          Pending.setLotteryResult(result.get(j));
        }
        break;
      }

      // フィーネの顔
      if (i != (BATTLE_MAX - 1) && fineFace(result.get(i + 1))) {
        PrintUtil.printlnUtf8("こんなもの効かぬ！！\n");
      }
    }

    if (Objects.isNull(round)) {
      PrintUtil.printlnUtf8("私の勝ちだぁぁぁぁぁぁぁぁ！！！ｗｗｗｗｗｗｗｗｗｗｗｗｗｗ");
      PrintUtil.printlnUtf8("完全聖遺物に対抗できるなどと、思うてくれるな\n");
    }

    return round;
  }

  private List<Boolean> makeResult() {
    List<Boolean> list = new ArrayList<>();

    for (int i = 0; i < BATTLE_MAX; i++) {
      list.add(lottery.lotOf(NUMER, DENOM));
    }

    return list;
  }

  private List<LastBattlePanel> makePanel(List<Boolean> result) {
    List<LastBattlePanel> panels = new ArrayList<>();

    for (boolean arg : result) {
      panels.add(lottery.lots(arg ? feverPanels : noFeverPanels));
    }

    List<String> names = panels.stream().map(LastBattlePanel::getName).collect(Collectors.toList());

    PrintUtil.printWithLine("パネル");
    PrintUtil.printlnUtf8(String.join(", ", names) + "\n");

    return panels;
  }

  private void printWord(LastBattlePanel panel, boolean result) {
    PrintUtil.printlnUtf8(panel.getWord());

    // 絶唱ならここで終わり
    if (panel.isZessho()) {
      return;
    }

    PrintUtil.printUtf8("勝機を零すな！掴み取れ！");

    // probability = 0 つまり、二課なら赤文字にならない気がする。見たことないけど
    if (panel.getProbability() == 0) {
      PrintUtil.printlnUtf8("\n引ぃぃぃぃぃぃけぇぇぇぇぇぇぇ！！！");
      return;
    }

    // 赤文字
    if (redCharactor(result)) {
      PrintUtil.printUtf8(" (赤文字！)");
    }

    // レバブル
    if (leverVibration(result)) {
      PrintUtil.printlnUtf8(" (レバブル！)");
    } else {
      PrintUtil.printlnUtf8();
    }
  }

  // フィーネの顔。出る確率がわからないので、次回当たりなら70%、ハズレなら20%で出すことにする
  private boolean fineFace(boolean nextResult) {
    return lottery.lot(nextResult ? 70 : 20);
  }

  // 赤文字。出る確率がわからないので、当たりなら70%、ハズレなら20%で出すことにする
  private boolean redCharactor(boolean result) {
    return lottery.lot(result ? 70 : 20);
  }

  // レバブル。出る確率がわからないので、当たりなら20%、ハズレなら1%で出すことにする
  // 発生タイミングは考慮してない
  private boolean leverVibration(boolean result) {
    return lottery.lot(result ? 20 : 1);
  }
}
