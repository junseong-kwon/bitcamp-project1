package bitcamp.project1.command;

import java.util.ArrayList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Board;

public class BoardCommand {
  static ArrayList<Board> boardList = new ArrayList<>();

  public static void menu() {
    while (true) {
      try {
        System.out.println("[게시판 메뉴]");
        System.out.println("1. 등록");
        System.out.println("2. 목록");
        System.out.println("3. 상세보기");
        System.out.println("4. 변경");
        System.out.println("5. 삭제");
        System.out.println("9. 이전 메뉴");
        int menuNo = Prompt.inputInt("메뉴를 선택하세요: ");

        switch (menuNo) {
          case 1:
            add();
            break;
          case 2:
            list();
            break;
          case 3:
            detail();
            break;
          case 4:
            update();
            break;
          case 5:
            delete();
            break;
          case 9:
            return;
          default:
            System.out.println("올바른 메뉴 번호를 선택하세요.");
        }
      } catch (Exception e) {
        System.out.printf("오류 발생: %s\n", e.getMessage());
      }
    }
  }

  public static void add() {
    try {
      Board board = new Board();
      board.no = Prompt.inputInt("번호? ");
      board.title = Prompt.inputString("제목? ");
      board.content = Prompt.inputString("내용? ");
      board.writer = Prompt.inputString("작성자? ");
      board.password = Prompt.inputString("암호? ");
      board.viewCount = 0;
      board.createdDate = System.currentTimeMillis();
      boardList.add(board);
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  public static void list() {
    try {
      for (Board board : boardList) {
        System.out.printf("%d, %s, %s, %d, %d\n",
            board.no, board.title, board.writer, board.viewCount, board.createdDate);
      }
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  public static void detail() {
    try {
      int no = Prompt.inputInt("번호? ");
      Board board = findByNo(no);
      if (board == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }
      System.out.printf("제목: %s\n", board.title);
      System.out.printf("내용: %s\n", board.content);
      System.out.printf("작성자: %s\n", board.writer);
      System.out.printf("조회수: %d\n", board.viewCount);
      System.out.printf("등록일: %s\n", new java.util.Date(board.createdDate));
      board.viewCount++;
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  public static void update() {
    try {
      int no = Prompt.inputInt("번호? ");
      Board board = findByNo(no);
      if (board == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }
      String title = Prompt.inputString(String.format("제목(%s)? ", board.title));
      String content = Prompt.inputString(String.format("내용(%s)? ", board.content));
      String writer = Prompt.inputString(String.format("작성자(%s)? ", board.writer));
      String password = Prompt.inputString(String.format("암호(%s)? ", board.password));

      board.title = title.length() > 0 ? title : board.title;
      board.content = content.length() > 0 ? content : board.content;
      board.writer = writer.length() > 0 ? writer : board.writer;
      board.password = password.length() > 0 ? password : board.password;

      System.out.println("게시글을 변경했습니다.");
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  public static void delete() {
    try {
      int no = Prompt.inputInt("번호? ");
      Board board = findByNo(no);
      if (board == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }
      boardList.remove(board);
      System.out.println("게시글을 삭제했습니다.");
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
  }

  private static Board findByNo(int no) {
    try {
      for (Board board : boardList) {
        if (board.no == no) {
          return board;
        }
      }
    } catch (Exception e) {
      System.out.printf("오류 발생: %s\n", e.getMessage());
    }
    return null;
  }
}
