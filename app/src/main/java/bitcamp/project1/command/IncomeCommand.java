package bitcamp.project1.command;

import java.util.ArrayList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Insert;
import bitcamp.project1.util.ExceptionHandler;

public class IncomeCommand {
    static ArrayList<Insert> incomeList = new ArrayList<>();

    public static void main() {
        while (true) {
            try {
                System.out.println("[입금 메뉴]");
                System.out.println("1. 입금 등록");
                System.out.println("2. 입금 목록");
                System.out.println("0. 이전 메뉴");

                int menuNo = Prompt.inputInt("입력할 메뉴를 선택하세요!");

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
    
}
