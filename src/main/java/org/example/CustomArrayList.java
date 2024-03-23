package org.example;

import java.util.Comparator;

/**
 * Кастомная имплементация ArrayList
 *
 * @param <E> тип элементов листа
 */
public class CustomArrayList<E> {

    // количество заполненных элементов, видимый пользователю размер контейнера
    private int size;

    // дефолтный размер массива
    private static final int DEFAULT_CAPACITY = 10;

    // пустой массив
    private static final Object[] EMPTY_ELEMENTS = {};

    // массив, в котором будут храниться элементы
    private Object[] data;

    /**
     * Конструктор без параметров (создает объект с дефолтным размером массива)
     */
    public CustomArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор с параметром, задающим размер массива
     */
    public CustomArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.data = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.data = EMPTY_ELEMENTS;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    /**
     * Возвращает количество элементов массива
     */
    public int size() {
        return size;
    }

    /**
     * Добавляет элемент в конец списка
     *
     * @param e элемент для добавления в конец списка
     * @return true если элемент добавлен успешно
     */
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    /**
     * Вставляет элемент на заданную позицию списка.
     * Сдвигает элемент или последовательность элементов (если такие есть) с позиции на которую была вставка вправо.
     *
     * @param index   индекс, на который нужно вставить элемент
     * @param element элемент для вставки
     * @throws IndexOutOfBoundsException если индекс не в границах массива
     *                                   (метод checkIndex (index < 0 || index > size))
     */
    public void add(int index, E element) {
        checkIndex(index, size);
        if (size() == data.length)
            increaseCapacity();
        for (int i = size() - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }

    /**
     * Возвращает элемент по заданному индексу
     *
     * @param index индекс элемента
     * @throws IndexOutOfBoundsException если индекс не в границах массива
     *                                   (метод checkIndex (index < 0 || index > size))
     */
    public E get(int index) {
        checkIndex(index, size);
        return (E) data[index];
    }

    /**
     * Заменяет элемент по заданному индексу, возвращает старый элемент
     *
     * @param index индекс элемента
     * @throws IndexOutOfBoundsException если индекс не в границах массива
     *                                   (метод checkIndex (index < 0 || index > size))
     */
    public E set(int index, E element) {
        checkIndex(index, size);
        E oldValue = (E) data[index];
        data[index] = element;
        return oldValue;
    }


    /**
     * Удаляет элемент по заданному индексу, возвращает удаленный элемент
     *
     * @param index индекс элемента
     * @throws IndexOutOfBoundsException если индекс не в границах массива
     *                                   (метод checkIndex (index < 0 || index >= size))
     */
    public E remove(int index) {
        checkIndex(index, size);
        Object element = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return (E) element;
    }


    /**
     * Очищает массив от элементов
     */
    public void clear() {
        final Object[] es = data;
        for (int to = size, i = size = 0; i < to; i++)
            es[i] = null;
    }

    /**
     * Проверяет индекс на попадание в границы массива
     *
     * @param index заданный индекс
     * @param size  размер массива
     */
    private void checkIndex(int index, int size) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
    }

    /**
     * Увеличивает вместимость массива в два раза
     */
    private void increaseCapacity() {
        // нужно создать массив в 2 раза больше
        Object[] newData = new Object[data.length * 2];
        // скопировать элементы от 0 до data.length из старого массива в новый
        System.arraycopy(data, 0, newData, 0, data.length);
        // присвоить data ссылку на новый массив
        data = newData;
    }

    /**
     * Вспомогательный метод - вызывает метод быстрой сортировки {@link CustomArrayList#quickSort(Object[], int, int, Comparator)}
     *
     * @param comparator объект компаратора для установления порядка сортировки
     */
    public void sort(Comparator comparator) {
        quickSort((E[]) data, 0, size - 1, comparator);
    }

    /**
     * Реализация быстрой сортировки массива
     *
     * @param data       массив для сортировки
     * @param low        начальный индекс массива
     * @param high       конечный индекс массива
     * @param comparator объект компаратора для установления порядка сортировки
     */
    private void quickSort(E[] data, int low, int high, Comparator comparator) {
        if (data.length == 0)
            return; //завершить выполнение, если длина массива равна 0

        if (low >= high)
            return; //завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = (int) (low + (high - low) * Math.random());
        E pivot = data[middle];

        // разделить на подмассивы больше и меньше опорного
        int i = low, j = high;
        while (i <= j) {
            while (comparator.compare(data[i], pivot) < 0) {
                i++;
            }

            while (comparator.compare(data[j], pivot) > 0) {
                j--;
            }

            //если элемент low больше опорного, а high меньше - меняем местами
            if (i <= j) {
                E temp = data[i];
                data[i] = data[j];
                data[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(data, low, j, comparator);

        if (high > i)
            quickSort(data, i, high, comparator);
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            if (i != 0)
                r.append(", ");
            r.append(data[i]);
        }
        r.append("]");
        return r.toString();
    }
}
