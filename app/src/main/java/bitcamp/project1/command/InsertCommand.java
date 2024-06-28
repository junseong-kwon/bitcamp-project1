package bitcamp.project1.command;

import java.util.ArrayList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Insert;
import bitcamp.project1.util.ExceptionHandler;

public class InsertCommand {
  public static void menu() {
    while (true) {
      try {
        System.out.println("[게시판 메뉴]");
        System.out.println("1. 입금");
        System.out.println("2. 지출");
        System.out.println("3. 최근 내역 확인하기");
        System.out.println("0. 이전 메뉴");

        int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

        switch (menuNo) {
          case 1:
            IncomeCommand.menu();
            break;
          case 2:
            ExpenseCommand.menu();
            break;
          case 3:
            HistoryCommand.menu();
            break;
          case 0:
            return;
          default:
            System.out.println("올바른 메뉴 번호를 선택하세요.");
        }
      } catch (Exception e) {
        ExceptionHandler.handle(e);
      }
    }
  }
}