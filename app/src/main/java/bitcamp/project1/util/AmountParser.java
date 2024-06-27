package bitcamp.project1.util;

public class AmountParser {
  public static int parseAmount(String amountStr) {
    amountStr = amountStr.replace("원", "").replace(",", "").trim();
    return Integer.parseInt(amountStr);
  }
}
