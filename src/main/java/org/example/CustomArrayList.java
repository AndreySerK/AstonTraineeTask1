package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class CustomArrayList<E>  {
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTS = {};
    private Object[] elements;

    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elements = EMPTY_ELEMENTS;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }



    public boolean add(E e) {
        add(size, e);
        return true;
    }


    public void add(int index, E element) {
        checkIndex(index, size);
        ensureCapacity();
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
    }


    public E get(int index) {
        checkIndex(index, size);
        return (E) elements[index];
    }

    public E set(int index, E element) {
        checkIndex(index, size);
        E oldValue = (E) elements[index];
        elements[index] = element;
        return oldValue;
    }


    public E remove(int index) {
        checkIndex(index, size);
        Object element = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return (E) element;
    }


    public void clear() {
        final Object[] es = elements;
        for (int to = size, i = size = 0; i < to; i++)
            es[i] = null;
    }

    private void checkIndex(int index, int size) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
    }

    private void ensureCapacity() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }

    public void quickSort (E [] array, int low, int high, Comparator<E> comparator){
        if (array.length == 0)
            return;//завершить выполнение, если длина массива равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = (int) (low + (high - low)*Math.random());
        E pivot = array[middle];

        // разделить на подмассивы больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (comparator.compare(array[i], pivot) < 0) {
                i++;
            }

            while (comparator.compare(array[j], pivot) > 0) {
                j--;
            }

            //меняем местами
            if (i <= j) {
                E temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(array, low, j, comparator);

        if (high > i)
            quickSort(array, i, high, comparator);
    }

    public static void main(String[] args) {

    }
}
