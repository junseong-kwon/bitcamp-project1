package bitcamp.project1.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Expense;
import bitcamp.project1.vo.Income;
import bitcamp.project1.util.ExceptionHandler;

public class ViewCommand {
  static ArrayList<Expense> expenseList = FinanceCommand.expenseList;
  static ArrayList<Income> incomeList = FinanceCommand.incomeList;
  static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  public static void menu() {
    while (true) {
      try {
        System.out.println("[조회 메뉴]");
        System.out.println("1. 지출 조회");
        System.out.println("2. 수입 조회");
        System.out.println("9. 이전 메뉴");

        int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

        switch (menuNo) {
          case 1:
            listExpenses();
            break;
          case 2:
            listIncomes();
            break;
          case 9:
            return;
          default:
            System.out.println("올바른 메뉴 번호를 선택하세요.");
        }
      } catch (Exception e) {
        ExceptionHandler.handle(e);
      }
    }
  }

  public static void listExpenses() {
    try {
      String id = Prompt.inputString("조회할 ID? ");
      for (Expense expense : expenseList) {
        if (expense.id.equals(id)) {
          System.out.printf("ID: %s, 금액: %d원, 카테고리: %s, 날짜: %s\n",
              expense.id, expense.amount, expense.category, dateFormat.format(new Date(expense.date)));
        }
      }
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }

  public static void listIncomes() {
    try {
      String id = Prompt.inputString("조회할 ID? ");
      for (Income income : incomeList) {
        if (income.id.equals(id)) {
          System.out.printf("ID: %s, 금액: %d원, 출처: %s, 날짜: %s\n",
              income.id, income.amount, income.source, dateFormat.format(new Date(income.date)));
        }
      }
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }
}
