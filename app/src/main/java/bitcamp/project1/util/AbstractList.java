package bitcamp.project1.util;

public abstract class AbstractList {
  protected int size;

  public int size() {
    return size;
  }

  public abstract void add(Object obj);

  public abstract Object[] toArray();

  public abstract Object get(int index);

  public abstract boolean remove(Object obj);
}
