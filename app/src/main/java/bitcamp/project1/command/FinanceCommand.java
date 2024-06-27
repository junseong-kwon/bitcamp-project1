package bitcamp.project1.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Expense;
import bitcamp.project1.vo.Income;
import bitcamp.project1.util.ExceptionHandler;

public class FinanceCommand {
  static ArrayList<Expense> expenseList = new ArrayList<>();
  static ArrayList<Income> incomeList = new ArrayList<>();
  static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  public static void menu() {
    while (true) {
      try {
        System.out.println("[가계부 메뉴]");
        System.out.println("1. 등록");
        System.out.println("2. 변경");
        System.out.println("3. 조회");
        System.out.println("4. 삭제");
        System.out.println("5. 총결산");
        System.out.println("9. 이전 메뉴");

        int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

        switch (menuNo) {
          case 1:
            registerMenu();
            break;
          case 2:
            updateMenu();
            break;
          case 3:
            viewMenu();
            break;
          case 4:
            deleteMenu();
            break;
          case 5:
            viewTotalSummary();
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

  private static void registerMenu() {
    while (true) {
      try {
        System.out.println("[등록 메뉴]");
        System.out.println("1. 지출 등록");
        System.out.println("2. 수입 등록");
        System.out.println("9. 이전 메뉴");

        int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

        switch (menuNo) {
          case 1:
            addExpense();
            break;
          case 2:
            addIncome();
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

  private static void updateMenu() {
    while (true) {
      try {
        System.out.println("[변경 메뉴]");
        System.out.println("1. 지출 변경");
        System.out.println("2. 수입 변경");
        System.out.println("9. 이전 메뉴");

        int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

        switch (menuNo) {
          case 1:
            updateExpense();
            break;
          case 2:
            updateIncome();
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

  private static void viewMenu() {
    while (true) {
      try {
        System.out.println("[조회 메뉴]");
        System.out.println("1. 지출 조회");
        System.out.println("2. 수입 조회");
        System.out.println("3. 총결산 조회");
        System.out.println("9. 이전 메뉴");

        int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

        switch (menuNo) {
          case 1:
            listExpenses();
            break;
          case 2:
            listIncomes();
            break;
          case 3:
            viewTotalSummary();
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

  private static void deleteMenu() {
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

  public static void addExpense() {
    try {
      Expense expense = new Expense();
      expense.id = Prompt.inputString("ID? ");
      expense.amount = parseAmount(Prompt.inputString("금액? "));
      expense.date = System.currentTimeMillis();
      expense.category = Prompt.inputString("카테고리? ");
      expenseList.add(expense);
      System.out.println("지출 내역을 등록했습니다.");
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }

  public static void addIncome() {
    try {
      Income income = new Income();
      income.id = Prompt.inputString("ID? ");
      income.amount = parseAmount(Prompt.inputString("금액? "));
      income.date = System.currentTimeMillis();
      income.source = Prompt.inputString("출처? ");
      incomeList.add(income);
      System.out.println("수입 내역을 등록했습니다.");
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }

  private static int parseAmount(String amountStr) {
    amountStr = amountStr.replace("원", "").replace(",", "").trim();
    return Integer.parseInt(amountStr);
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

  public static void viewTotalSummary() {
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

  public static void updateExpense() {
    try {
      String id = Prompt.inputString("ID? ");
      Expense expense = findExpenseById(id);
      if (expense == null) {
        System.out.println("해당 ID의 지출 내역이 없습니다.");
        return;
      }
      int amount = parseAmount(Prompt.inputString(String.format("금액(%d원)? ", expense.amount)));
      String category = Prompt.inputString(String.format("카테고리(%s)? ", expense.category));

      expense.amount = amount > 0 ? amount : expense.amount;
      expense.category = category.length() > 0 ? category : expense.category;

      System.out.println("지출 내역을 변경했습니다.");
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }

  public static void updateIncome() {
    try {
      String id = Prompt.inputString("ID? ");
      Income income = findIncomeById(id);
      if (income == null) {
        System.out.println("해당 ID의 수입 내역이 없습니다.");
        return;
      }
      int amount = parseAmount(Prompt.inputString(String.format("금액(%d원)? ", income.amount)));
      String source = Prompt.inputString(String.format("출처(%s)? ", income.source));

      income.amount = amount > 0 ? amount : income.amount;
      income.source = source.length() > 0 ? source : income.source;

      System.out.println("수입 내역을 변경했습니다.");
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
  }

  public static void deleteExpense() {
    try {
      String id = Prompt.inputString("ID? ");
      Expense expense = findExpenseById(id);
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
      Income income = findIncomeById(id);
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

  private static Expense findExpenseById(String id) {
    try {
      for (Expense expense : expenseList) {
        if (expense.id.equals(id)) {
          return expense;
        }
      }
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
    return null;
  }

  private static Income findIncomeById(String id) {
    try {
      for (Income income : incomeList) {
        if (income.id.equals(id)) {
          return income;
        }
      }
    } catch (Exception e) {
      ExceptionHandler.handle(e);
    }
    return null;
  }
}
