package bitcamp.project1.vo;

import java.util.Date;
import java.util.Objects;

public class Transaction {
  private static int seqNo;

  private int no;
  private Date date;
  private String type; // "수입" 또는 "지출"
  private String category;
  private int amount;
  private String description;

  public Transaction() {
  }

  public Transaction(int no) {
    this.no = no;
  }

  public static int getNextSeqNo() {
    return ++seqNo;
  }

  // Getters and setters
  // equals, hashCode 메서드
  // (Board.java를 참고하여 구현)
}