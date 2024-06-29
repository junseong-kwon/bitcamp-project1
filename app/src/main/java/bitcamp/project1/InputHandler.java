package bitcamp.project1;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("올바른 숫자를 입력해주세요.");
                scanner.nextLine(); // consume invalid input
            }
        }
    }

    public void consumeNewLine() {
        scanner.nextLine(); // Consume the remaining newline
    }

    public LocalDate getDateInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String dateString = scanner.nextLine();
            try {
                return DateUtils.parseDate(dateString);
            } catch (DateTimeParseException e) {
                System.out.println("올바른 날짜 형식을 입력해주세요 (yyyy-MM-dd 또는 yyyyMMdd).");
            }
        }
    }

    public void closeScanner() {
        scanner.close();
    }
}
