package bitcamp.project1.util;

import java.util.Arrays;

public class ArrayList extends AbstractList {
  private static final int DEFAULT_CAPACITY = 10;
  private Object[] list = new Object[DEFAULT_CAPACITY];

  public void add(Object obj) {
    if (this.size == list.length) {
      list = Arrays.copyOf(list, list.length + (list.length >> 1));
    }
    this.list[this.size++] = obj;
  }

  public Object[] toArray() {
    return Arrays.copyOf(this.list, this.size);
  }

  public Object get(int index) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    return this.list[index];
  }

  public boolean remove(Object obj) {
    int index = indexOf(obj);
    if (index == -1) {
      return false;
    }
    for (int i = index + 1; i < this.size; i++) {
      this.list[i - 1] = this.list[i];
    }
    this.list[--this.size] = null;
    return true;
  }

  private int indexOf(Object obj) {
    for (int i = 0; i < this.size; i++) {
      if (this.list[i].equals(obj)) {
        return i;
      }
    }
    return -1;
  }
}
