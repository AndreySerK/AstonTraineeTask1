package org.example;


import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;


public class CustomArrayListTest {

    CustomArrayList<Object> customArrayList;

    @Before
    public void setUp() throws Exception {
        customArrayList = new CustomArrayList<>();
    }

    @Test
    public void getSizeTest() {
        customArrayList.add(5);
        assertEquals(1, customArrayList.size());
    }

    @Test
    public void whenGetWithCorrectIndex_thenReturnElement_getTest() {
        customArrayList.add(5);
        customArrayList.add(6);
        assertEquals(5, customArrayList.get(0));
    }

    @Test
    public void whenGetWithWrongIndex_thenThrowException_getTest() {
        customArrayList.add(5);
        customArrayList.add(6);
        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> customArrayList.get(10));
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void whenAddWithoutIndex_thenReturnTrue_addTest() {
        assertTrue(customArrayList.add(5));
        assertEquals(1, customArrayList.size());
        assertEquals(5, customArrayList.get(0));
    }

    @Test
    public void whenAddMoreThan20_thenReturnTrue_addTest() {
        for (int i = 0; i < 25; i++) {
            customArrayList.add(i);
        }
        assertEquals(25, customArrayList.size());
    }

    @Test
    public void whenAddWithCorrectIndex_thenNoException_addTest() {
        customArrayList.add(0, 1);
        customArrayList.add(1, 2);
        customArrayList.add(2, 3);
        customArrayList.add(2, 8);
        assertEquals(4, customArrayList.size());
        assertEquals(8, customArrayList.get(2));
    }

    @Test
    public void whenAddWithWrongIndex_thenThrowException_addTest() {
        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> customArrayList.add(10, 10));
        assertNotNull(thrown.getMessage());
    }


    @Test
    public void whenSetWithCorrectIndex_thenReturnOldElement_setTest() {
        customArrayList.add(1);
        customArrayList.add(2);
        customArrayList.set(1, 10);
        assertEquals(10, customArrayList.get(1));
    }

    @Test
    public void whenSetWithWrongIndex_thenThrowException_setTest() {
        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> customArrayList.set(10, 10));
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void whenRemoveWithCorrectIndex_thenReturnRemovedElement_removeTest() {
        customArrayList.add(1);
        customArrayList.add(2);
        customArrayList.remove(0);
        assertEquals(1, customArrayList.size());
        assertEquals(2, customArrayList.get(0));
    }

    @Test
    public void whenRemoveWithWrongIndex_thenThrowException_removeTest() {
        customArrayList.add(6);
        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> customArrayList.remove(10));
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void clearTest() {
        customArrayList.add(1);
        customArrayList.add(2);
        assertEquals(2, customArrayList.size());
        customArrayList.clear();
        assertEquals(0, customArrayList.size());
    }

    @Test
    public void sortTest() {
        CustomArrayList<Integer> cList = new CustomArrayList<>();
        Comparator<Integer> comparator = (o1, o2) -> o1 - o2;
        customArrayList.add(8);
        customArrayList.add(5);
        customArrayList.add(7);
        customArrayList.add(9);
        customArrayList.add(0);
        String givenList = "[8, 5, 7, 9, 0]";
        String sortedList = "[0, 5, 7, 8, 9]";
        assertEquals(givenList, customArrayList.toString());
        customArrayList.sort(comparator);
        assertEquals(sortedList, customArrayList.toString());
    }
}