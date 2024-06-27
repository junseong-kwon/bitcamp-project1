package bitcamp.project1;

import bitcamp.project1.command.BoardCommand;
import bitcamp.project1.command.ProjectCommand;
import bitcamp.project1.command.UserCommand;
import bitcamp.project1.command.FinanceCommand;  // 추가
import bitcamp.project1.util.Prompt;

public class App {

  String[] mainMenus = new String[]{"회원", "프로젝트", "게시판", "공지사항", "가계부", "도움말", "종료"};  // "가계부" 추가
  String[][] subMenus = {
      {"등록", "목록", "조회", "변경", "삭제"},
      {"등록", "목록", "조회", "변경", "삭제"},
      {"등록", "목록", "조회", "변경", "삭제"},
      {"등록", "목록", "조회", "변경", "삭제"},
      {"등록", "목록", "조회", "변경", "삭제"},  // 가계부 서브메뉴 추가
      {}
  };

  UserCommand userCommand = new UserCommand();
  BoardCommand boardCommand = new BoardCommand();
  BoardCommand noticeCommand = new BoardCommand();
  ProjectCommand projectCommand = new ProjectCommand(userCommand.getUserList());
  FinanceCommand financeCommand = new FinanceCommand();  // 추가

  // ... (나머지 코드는 그대로 유지)

  void processMenu(String menuTitle, String[] menus) {
    if (menuTitle.equals("도움말")) {
      System.out.println("도움말입니다.");
      return;
    }
    printSubMenu(menuTitle, menus);
    while (true) {
      String command = Prompt.input(String.format("메인/%s>", menuTitle));
      if (command.equals("menu")) {
        printSubMenu(menuTitle, menus);
        continue;
      } else if (command.equals("9")) { // 이전 메뉴 선택
        break;
      }

      try {
        int menuNo = Integer.parseInt(command);
        String subMenuTitle = getMenuTitle(menuNo, menus);
        if (subMenuTitle == null) {
          System.out.println("유효한 메뉴 번호가 아닙니다.");
        } else {
          switch (menuTitle) {
            case "회원":
              userCommand.executeUserCommand(subMenuTitle);
              break;
            case "프로젝트":
              projectCommand.executeProjectCommand(subMenuTitle);
              break;
            case "게시판":
              boardCommand.executeBoardCommand(subMenuTitle);
              break;
            case "공지사항":
              noticeCommand.executeBoardCommand(subMenuTitle);
              break;
            case "가계부":  // 추가
              financeCommand.executeFinanceCommand(subMenuTitle);
              break;
            default:
              System.out.printf("%s 메뉴의 명령을 처리할 수 없습니다.\n", menuTitle);
          }
        }
      } catch (NumberFormatException ex) {
        System.out.println("숫자로 메뉴 번호를 입력하세요.");
      }
    }
  }
}