package bitcamp.project1;

import bitcamp.project1.util.Prompt;
import bitcamp.project1.command.BoardCommand;
import bitcamp.project1.command.ProjectCommand;
import bitcamp.project1.command.UserCommand;
import bitcamp.project1.command.FinanceCommand;

public class App {
  public static void main(String[] args) {
    while (true) {
      try {
        System.out.println("메인 메뉴:");
        System.out.println("1. 게시판");
        System.out.println("2. 프로젝트");
        System.out.println("3. 사용자");
        System.out.println("4. 가계부");
        System.out.println("0. 종료");

        int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

        switch (menuNo) {
          case 1:
            BoardCommand.menu();
            break;
          case 2:
            ProjectCommand.menu();
            break;
          case 3:
            UserCommand.menu();
            break;
          case 4:
            FinanceCommand.menu();
            break;
          case 0:
            System.out.println("프로그램을 종료합니다.");
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
