package bitcamp.project1.util;

import bitcamp.project1.vo.Expense;
import bitcamp.project1.vo.Income;
import java.util.ArrayList;

public class FinanceUtil {
  public static Expense findExpenseById(ArrayList<Expense> expenseList, String id) {
    for (Expense expense : expenseList) {
      if (expense.id.equals(id)) {
        return expense;
      }
    }
    return null;
  }

  public static Income findIncomeById(ArrayList<Income> incomeList, String id) {
    for (Income income : incomeList) {
      if (income.id.equals(id)) {
        return income;
      }
    }
    return null;
  }
}
