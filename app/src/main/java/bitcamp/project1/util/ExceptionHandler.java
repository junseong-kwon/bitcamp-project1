package bitcamp.project1.util;

public class ExceptionHandler {
  public static void handle(Exception e) {
    System.out.printf("오류 발생: %s\n", e.getMessage());
  }
}
