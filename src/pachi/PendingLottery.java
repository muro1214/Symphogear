package pachi;

import lottery.Lottery;

public class PendingLottery {

  private PendingLottery() {
  }

  public static <T extends Pachi> WinKind lot(T pachiData) {
    Lottery lottery = Lottery.getInstance();

    if(lottery.lotOf(pachiData.getNormalNumer(), pachiData.getNormalDenom())) {
      return WinKind.Normal;
    }

    if(lottery.lotOf(pachiData.getKakuhenNumer(), pachiData.getKakuhenDenom())) {
      return WinKind.Kakuhen;
    }

    return WinKind.Miss;
  }

  public static <T extends Pachi> boolean isZenkaiten(T pachiData) {
    Lottery lottery = Lottery.getInstance();

    return lottery.lotOf(pachiData.getNormalNumer(), pachiData.getNormalDenom()) && lottery.lotOf(1, 100);
  }

  public static boolean isWin(WinKind winKind) {
    return winKind == WinKind.Normal || winKind == WinKind.Kakuhen;
  }

  public static boolean isMiss(WinKind winKind) {
    return !isWin(winKind);
  }
}
