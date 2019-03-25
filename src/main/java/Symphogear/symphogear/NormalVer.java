package Symphogear.symphogear;

import java.util.ArrayList;
import java.util.List;

import Symphogear.pachi.Pachi;
import Symphogear.pachi.Round;

public class NormalVer extends Pachi {

  public NormalVer() {
    super(1, 199, 10, 74);

    List<Round> rounds = new ArrayList<>();
    rounds.add(new Round("16R", "SPECIAL FEVER", 40, 1470));
    rounds.add(new Round("12R", "ギアV-COMBO", 3, 1176));
    rounds.add(new Round("8R", "ギアV-COMBO", 7, 784));
    rounds.add(new Round("4R", "FEVER", 50, 392));

    setRounds(rounds);
  }

  @Override
  public String getNormalFraction() {
    return "1/199";
  }

  @Override
  public String getKakuhenFraction() {
    return "1/7.4";
  }
}
