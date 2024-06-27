package bitcamp.project1.command;

import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Expense;
import bitcamp.project1.vo.Income;
import bitcamp.project1.util.FinanceUtil;
import bitcamp.project1.util.ExceptionHandler;
import java.util.ArrayList;

public class DeleteCommand {
  static ArrayList<Expense> expenseList = FinanceCommand.expenseList;
  static ArrayList<Income> incomeList = FinanceCommand.incomeList;

  public static void menu() {
    while (true) {
      try {
        System.out.println("[삭제 메뉴]");
        System.out.println("1. 지출 삭제");
        System.out.println("2. 수입 삭제");
        System.out.println("9. 이전 메뉴");

        int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

        switch (menuNo) {
          case 1:
            deleteExpense();
            break;
          case 2:
            deleteIncome();
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

  public static void deleteExpense() {
    try {
      String id = Prompt.inputString("ID? ");
      Expense expense = FinanceUtil.findExpenseById(expenseList, id);
      if (expense == null) {
        System.out.println("해당 ID의 지출 내역이 없습니다.");
        return;
      }
      expenseList.remove(expense);
      System.out.println("지출 내역을 삭제했습니다.");
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }

  public static void deleteIncome() {
    try {
      String id = Prompt.inputString("ID? ");
      Income income = FinanceUtil.findIncomeById(incomeList, id);
      if (income == null) {
        System.out.println("해당 ID의 수입 내역이 없습니다.");
        return;
      }
      incomeList.remove(income);
      System.out.println("수입 내역을 삭제했습니다.");
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }
}
