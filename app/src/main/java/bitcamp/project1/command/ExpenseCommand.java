package bitcamp.project1.command;

import java.util.ArrayList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Expense;
import bitcamp.project1.util.ExceptionHandler;

public class ExpenseCommand {
    static ArrayList<Expense> expenseList = new ArrayList<>();

    public static void menu() {
        while (true) {
            try {
                System.out.println("[출금 메뉴]");
                System.out.println("1. 출금 등록");
                System.out.println("2. 출금 내역 확인");
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
            Expense expense = new Expense();
            expense.no = Prompt.inputInt("출금할 금액? ");
            expense.title = Prompt.inputString("식비? ");
            expense.content = Prompt.inputString("교통비? ");
            expense.writer = Prompt.inputString("경조사비? ");
            expense.others = Prompt.inputString("기타 용도? "); // 추가된 필드
            expense.password = Prompt.inputString("암호? ");
            expense.viewCount = 0;
            expense.createdDate = System.currentTimeMillis();
            expenseList.add(expense);

            // 날짜와 시간 형식 지정
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = sdf.format(new java.util.Date(expense.createdDate));

            System.out.printf("[출금을 등록했습니다!] 등록일: %s\n", formattedDate);
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    public static void list() {
        try {
            for (Expense expense : expenseList) {
                System.out.printf("%d, %s, %s, %s, %s, %d, %s\n",
                        expense.no, expense.title, expense.content, expense.writer, expense.others, expense.viewCount, new java.util.Date(expense.createdDate));
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    private static Expense findByNo(int no) {
        try {
            for (Expense expense : expenseList) {
                if (expense.no == no) {
                    return expense;
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }
}
