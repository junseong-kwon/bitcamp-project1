package bitcamp.project1.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Insert;
import bitcamp.project1.vo.Expense;
import bitcamp.project1.util.ExceptionHandler;

public class ProjectCommand {

  public static void menu() {
    while (true) {
      try {
        System.out.println("[지출 통계 확인하기]");
        System.out.println("1. 주 단위 내역 보기");
        System.out.println("2. 월 단위 내역 보기");
        System.out.println("0. 이전 메뉴");

        int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

        switch (menuNo) {
          case 1:
            listByWeek();
            break;
          case 2:
            listByMonth();
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

  public static void listByWeek() {
    try {
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
      Date weekStart = calendar.getTime();

      calendar.add(Calendar.DAY_OF_WEEK, 6);
      Date weekEnd = calendar.getTime();

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

      System.out.printf("주 단위 내역 (%s ~ %s):\n", sdf.format(weekStart), sdf.format(weekEnd));
      for (Insert insert : InsertCommand.insertList) {
        if (insert.createdDate >= weekStart.getTime() && insert.createdDate <= weekEnd.getTime()) {
          System.out.printf("입금 - %d, %s, %s, %d, %s\n",
                  insert.no, insert.title, insert.writer, insert.viewCount, new java.util.Date(insert.createdDate));
        }
      }
      for (Expense expense : ExpenseCommand.expenseList) {
        if (expense.createdDate >= weekStart.getTime() && expense.createdDate <= weekEnd.getTime()) {
          System.out.printf("출금 - %d, %s, %s, %s, %d, %s\n",
                  expense.no, expense.title, expense.content, expense.writer, expense.viewCount, new java.util.Date(expense.createdDate));
        }
      }
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }

  public static void listByMonth() {
    try {
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.DAY_OF_MONTH, 1);
      Date monthStart = calendar.getTime();

      calendar.add(Calendar.MONTH, 1);
      calendar.add(Calendar.DAY_OF_MONTH, -1);
      Date monthEnd = calendar.getTime();

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

      System.out.printf("월 단위 내역 (%s ~ %s):\n", sdf.format(monthStart), sdf.format(monthEnd));
      for (Insert insert : InsertCommand.insertList) {
        if (insert.createdDate >= monthStart.getTime() && insert.createdDate <= monthEnd.getTime()) {
          System.out.printf("입금 - %d, %s, %s, %d, %s\n",
                  insert.no, insert.title, insert.writer, insert.viewCount, new java.util.Date(insert.createdDate));
        }
      }
      for (Expense expense : ExpenseCommand.expenseList) {
        if (expense.createdDate >= monthStart.getTime() && expense.createdDate <= monthEnd.getTime()) {
          System.out.printf("출금 - %d, %s, %s, %s, %d, %s\n",
                  expense.no, expense.title, expense.content, expense.writer, expense.viewCount, new java.util.Date(expense.createdDate));
        }
      }
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }
}
