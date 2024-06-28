package bitcamp.project1.command;

import java.text.SimpleDateFormat;
import java.util.Date;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Insert;
import bitcamp.project1.vo.Expense;
import bitcamp.project1.util.ExceptionHandler;

public class HistoryCommand {

    public static void menu() {
        while (true) {
            try {
                System.out.println("[최근 내역 확인하기]");
                System.out.println("1. 최근 입금 내역 보기");
                System.out.println("2. 최근 출금 내역 보기");
                System.out.println("0. 이전 메뉴");

                int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

                switch (menuNo) {
                    case 1:
                        listRecentInserts();
                        break;
                    case 2:
                        listRecentExpenses();
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

    public static void listRecentInserts() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("[최근 입금 내역]");
            for (Insert insert : InsertCommand.insertList) {
                System.out.printf("%d, %s, %s, %d, %s\n",
                        insert.no, insert.title, insert.writer, insert.viewCount, sdf.format(new Date(insert.createdDate)));
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    public static void listRecentExpenses() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("[최근 출금 내역]");
            for (Expense expense : ExpenseCommand.expenseList) {
                System.out.printf("%d, %s, %s, %s, %d, %s\n",
                        expense.no, expense.title, expense.content, expense.writer, expense.viewCount, sdf.format(new Date(expense.createdDate)));
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }
}
