package bitcamp.project1.util;

// 추상클래스는 추상메서드를 가질 수 있다.
public abstract class AbstractList implements List { //abstract를 붙이면 인스턴스 생성 불가!!!!

  protected int size = 0; // 같은 패키지와 상속한 클래스는 protected 사용할 수 있다.

  @Override
  public int size() {
    return size;
  }
}
