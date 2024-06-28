package bitcamp.project1.command;

import java.util.ArrayList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Insert;
import bitcamp.project1.util.ExceptionHandler;

public class InsertCommand {
  static ArrayList<Insert> insertList = new ArrayList<>();

  public static void menu() {
    while (true) {
      try {
        System.out.println("[입/출금 메뉴]");
        System.out.println("1. 입금");
        System.out.println("2. 출금");
        System.out.println("0. 이전 메뉴");

        int menuNo = Prompt.inputInt("메뉴를 선택하세요! ");

        switch (menuNo) {
          case 1:
            add();
            break;
          case 2:
            ExpenseCommand.menu(); // 지출 메뉴 호출
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

  public static void add() {
    try {
      Insert insert = new Insert();
      insert.no = Prompt.inputInt("입금된 금액? ");
      insert.title = Prompt.inputString("월급? ");
      insert.content = Prompt.inputString("실업 급여? ");
      insert.writer = Prompt.inputString("구직 촉진 급여? ");
      insert.password = Prompt.inputString("암호? ");
      insert.viewCount = 0;
      insert.createdDate = System.currentTimeMillis();
      insertList.add(insert);

      // 날짜와 시간 형식 지정
      java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String formattedDate = sdf.format(new java.util.Date(insert.createdDate));

      System.out.printf("[입금했습니다!] 등록일: %s\n", formattedDate);
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }

  public static void list() {
    try {
      for (Insert insert : insertList) {
        System.out.printf("%d, %s, %s, %d, %s\n",
                insert.no, insert.title, insert.writer, insert.viewCount, new java.util.Date(insert.createdDate));
      }
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }

  private static Insert findByNo(int no) {
    try {
      for (Insert insert : insertList) {
        if (insert.no == no) {
          return insert;
        }
      }
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
    return null;
  }
}
