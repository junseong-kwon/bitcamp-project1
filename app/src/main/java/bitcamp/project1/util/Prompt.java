package bitcamp.project1.util;

import java.util.Scanner;

public class Prompt {
  static Scanner scanner = new Scanner(System.in);

  public static String inputString(String title, Object... args) {
    System.out.printf(title, args);
    return scanner.nextLine();
  }

  public static int inputInt(String title, Object... args) {
    return Integer.parseInt(inputString(title, args));
  }

  public static double inputDouble(String title, Object... args) {
    return Double.parseDouble(inputString(title, args));
  }

  public static void close() {
    scanner.close();
  }
}
