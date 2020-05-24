package com.learn.stack;

/**
 * @author Administrator
 */
public class DynamicArray<E> {
    /**
     * 元素个数
     */
    private int size;
    /**
     * 容量
     */
    private int capacity;

    /**
     * 数组
     */
    private E[] array;

    public DynamicArray(int size, int capacity) {
        this.size = size;
        this.capacity = capacity;
        array = (E[]) new Object[capacity];
    }

    public DynamicArray() {
        this(0, 5);
    }

    /**
     * 数组容量大小
     *
     * @return
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * 数组元素个数
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    // 获取数组中的元素个数
    public int getSize() {
        return size;
    }

    /**
     * 添加元素
     */
    public void add(int index, E data) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引下标不合法");
        }
        if (size == capacity) {
            resize(capacity * 2);
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = data;
        size++;
    }

    /**
     * 数组首部添加元素
     *
     * @param data 元素
     */
    public void addFirst(E data) {
        add(0, data);
    }

    /**
     * 数组尾部添加元素
     *
     * @param data 元素
     */
    public void addLast(E data) {
        add(size, data);
    }

    /**
     * 删除元素
     *
     * @param index 索引
     * @return 删除的元素
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("索引下标不合法");
        }
        E ret = array[index];
        for (int i = index + 1; i < size; i++) {
            array[i - 1] = array[i];
        }
        size--;
        array[size] = null;
        if (size == capacity / 2) {
            resize(capacity / 2);
        }
        return ret;
    }

    /**
     * 删除数组首部
     *
     * @return 被删除的元素
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除数组尾部
     *
     * @return 被删除的元素
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 更新元素
     *
     * @param index 索引
     */
    public void update(int index, E data) {
        checkIndex(index);
        array[index] = data;
        System.out.println(array[index]);
    }

    /**
     * 包含元素
     *
     * @param data 元素
     */
    public boolean contains(E data) {
        for (int i = 0; i < size; i++) {
            if (array[i] == data) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找元素
     *
     * @param data 元素
     */
    public int find(E data) {
        for (int i = 0; i < size; i++) {
            if (array[i] == data) {
                return i;
            }
        }
        return -1;
    }


    public E get(int index) {
        checkIndex(index);
        return array[index];
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }


    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("索引下标不合法");
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, array.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(array[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }

    public void resize(int capacity) {
        E[] newArray = (E[]) new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        for (int i = 0; i < 5; i++) {
            dynamicArray.add(i, i);
        }
        System.out.println(dynamicArray);
        dynamicArray.addFirst(20);
        System.out.println(dynamicArray);
        System.out.println(dynamicArray.removeLast());
        System.out.println(dynamicArray);
        System.out.println(dynamicArray.removeFirst());
        System.out.println(dynamicArray);
        dynamicArray.update(0, 10);
        System.out.println(dynamicArray);
        System.out.println(dynamicArray.contains(10));
        System.out.println(dynamicArray.remove(2));
        System.out.println(dynamicArray);
        System.out.println(dynamicArray.find(3));
    }
}
