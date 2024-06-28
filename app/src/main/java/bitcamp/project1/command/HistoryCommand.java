package bitcamp.project1.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Insert;
import bitcamp.project1.vo.Expense;
import bitcamp.project1.util.ExceptionHandler;

public class HistoryCommand {
    public static void menu() {
        while (true) {
            try {
                System.out.println("[최근 내역 확인하기]");
                System.out.println("1. 전체 내역 보기");
                System.out.println("0. 이전 메뉴");

                int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

                switch (menuNo) {
                    case 1:
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

    public static void list() {
        try {
            ArrayList<Object> historyList = new ArrayList<>();
            historyList.addAll(InsertCommand.insertList);
            historyList.addAll(ExpenseCommand.expenseList);

            // 내역을 날짜순으로 정렬
            Collections.sort(historyList, new Comparator<Object>() {
                public int compare(Object o1, Object o2) {
                    long date1 = (o1 instanceof Insert) ? ((Insert) o1).createdDate : ((Expense) o1).createdDate;
                    long date2 = (o2 instanceof Insert) ? ((Insert) o2).createdDate : ((Expense) o2).createdDate;
                    return Long.compare(date2, date1);
                }
            });

            for (Object item : historyList) {
                if (item instanceof Insert) {
                    Insert insert = (Insert) item;
                    System.out.printf("입금 - %d, %s, %s, %d, %s\n",
                            insert.no, insert.title, insert.writer, insert.viewCount, new java.util.Date(insert.createdDate));
                } else if (item instanceof Expense) {
                    Expense expense = (Expense) item;
                    System.out.printf("출금 - %d, %s, %s, %s, %d, %s\n",
                            expense.no, expense.title, expense.content, expense.writer, expense.viewCount, new java.util.Date(expense.createdDate));
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }
}
