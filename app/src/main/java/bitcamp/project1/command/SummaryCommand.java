package bitcamp.project1.command;

import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Expense;
import bitcamp.project1.vo.Income;
import bitcamp.project1.util.FinanceUtil;
import bitcamp.project1.util.ExceptionHandler;
import java.util.ArrayList;

public class SummaryCommand {
  static ArrayList<Expense> expenseList = FinanceCommand.expenseList;
  static ArrayList<Income> incomeList = FinanceCommand.incomeList;

  public static void menu() {
    try {
      String id = Prompt.inputString("ID? ");
      int totalIncome = 0;
      int totalExpense = 0;

      for (Income income : incomeList) {
        if (income.id.equals(id)) {
          totalIncome += income.amount;
        }
      }

      for (Expense expense : expenseList) {
        if (expense.id.equals(id)) {
          totalExpense += expense.amount;
        }
      }

      int totalBalance = totalIncome - totalExpense;

      if (totalBalance < 0) {
        System.out.println("불가능합니다.");
      } else {
        System.out.printf("총결산: %d원 (수입: %d원, 지출: %d원)\n", totalBalance, totalIncome, totalExpense);
      }
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }
}
