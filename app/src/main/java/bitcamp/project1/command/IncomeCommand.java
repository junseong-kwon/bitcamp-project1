package bitcamp.project1.command;

import java.util.ArrayList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Insert;
import bitcamp.project1.util.ExceptionHandler;

public class IncomeCommand {
    static ArrayList<Insert> incomeList = new ArrayList<>();

    public static void menu() {
        while (true) {
            try {
                System.out.println("[입금 메뉴]");
                System.out.println("1. 입금 등록");
                System.out.println("2. 입금 내역 확인");
                System.out.println("0. 이전 메뉴");

                int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

                switch (menuNo) {
                    case 1:
                        add();
                        break;
                    case 2:
                        list();
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
            insert.no = Prompt.inputInt("월급? ");
            insert.title = Prompt.inputString("제목? ");
            insert.content = Prompt.inputString("내용? ");
            insert.writer = Prompt.inputString("작성자? ");
            insert.password = Prompt.inputString("암호? ");
            insert.viewCount = 0;
            insert.createdDate = System.currentTimeMillis();
            incomeList.add(insert);
            System.out.println("입금을 등록했습니다.");
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    public static void list() {
        try {
            for (Insert insert : incomeList) {
                System.out.printf("%d, %s, %s, %d, %s\n",
                        insert.no, insert.title, insert.writer, insert.viewCount, new java.util.Date(insert.createdDate));
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }
}
