package symphogear;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 保留玉管理
 * @author smuro
 *
 */
public class Pending {
  /** 保留 */
  private static Queue<Boolean> pendingQueue = new LinkedList<>();
  /** V-STOCKの残数 */
  private static int vStockCount = 0;

  private Pending() {
  }

  /**
   * 保留を貯める
   * @param result [boolean] 抽選結果
   */
  public static void setLotteryResult(boolean result) {
    pendingQueue.offer(result);
    if (pendingQueue.size() > 4) {
      throw new IllegalStateException("pending queue is over 4. " + pendingQueue);
    }
  }

  public static boolean getLotteryResult() {
    return pendingQueue.poll();
  }

  public static int getPendingCount() {
    return pendingQueue.size();
  }

  public static int getWinCount() {
    return (int) pendingQueue.stream().filter(it -> it == true).count();
  }

  public static void setVStockCount(int vStock) {
    vStockCount = vStock;
  }

  public static void useVStock() {
    vStockCount--;
  }

  public static int getVStockCount() {
    return vStockCount;
  }
}