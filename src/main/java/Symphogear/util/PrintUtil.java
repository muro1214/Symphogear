package Symphogear.util;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class PrintUtil {

  private PrintUtil() {
  }

  public static void printWithLine(String string) {
    printlnUtf8("--- " + string + " ---");
  }

  public static void printlnUtf8(String string) {
    try {
      PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.name());
      out.println(string);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  public static void printlnUtf8() {
    printlnUtf8("");
  }

  public static void printUtf8(String string) {
    try {
      PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8.name());
      out.print(string);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }
}