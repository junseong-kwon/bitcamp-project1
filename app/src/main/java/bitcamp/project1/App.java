package bitcamp.project1;

import bitcamp.project1.util.Prompt;
import bitcamp.project1.command.InsertCommand;
import bitcamp.project1.command.ProjectCommand;
import bitcamp.project1.command.UserCommand;

public class App {
  public static void main(String[] args) {
    while (true) {
      try {
        System.out.println("[메인 메뉴] : ");
        System.out.println("1. 입/출금 입력");
        System.out.println("2. 지출 통계 확인하기");
        System.out.println("0. 종료");

        int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

        switch (menuNo) {
          case 1:
            InsertCommand.menu();
            break;
          case 2:
            ProjectCommand.menu();
            break;
          case 3:
            UserCommand.menu();
            break;
          case 0:
            System.out.println("가계부를 종료합니다.");
            Prompt.close();
            return;
          default:
            System.out.println("올바른 메뉴 번호를 선택하세요.");
        }
      } catch (Exception e) {
        bitcamp.project1.util.ExceptionHandler.handle(e);
      }
    }
  }
}
