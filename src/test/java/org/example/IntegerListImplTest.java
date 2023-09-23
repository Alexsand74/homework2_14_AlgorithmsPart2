package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
class IntegerListImplTest {
    private final IntegerList integerList = new IntegerListImpl();
    private Integer[] elements;
    private Integer[] elementsFull;
    Integer numberInteger3 = 3;
    Integer numberInteger5 = 5;
    @BeforeEach
    void setUp() {
        elements = new Integer[]{0, 1, 2, 3, 4};
        elementsFull = new Integer[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
//  текущий метод добавления готового массива, находится в этом классе
        addToArray(elements);
    }

    @AfterEach
    public void afterEach() {
        integerList.clear();
    }

    @Test
    void addTest() {
        for (int i = 0; i < elements.length; i++) {
            assertThat(integerList.get(i)).isEqualTo(elements[i]);
            assertThat(integerList.contains(elements[i])).isTrue();
            assertThat(integerList.indexOf(elements[i])).isEqualTo(i);
            assertThat(integerList.lastIndexOf(elements[i])).isEqualTo(i);
        }

        assertThat(integerList.toArray()).hasSize(elements.length);
        assertThat(integerList.toArray()).containsExactly(elements);
        assertThat(integerList.contains(numberInteger5)).isFalse();
    }

    @Test
    void removeTest() {
        assertEquals(1, integerList.remove((Integer) 1));
        assertEquals(4, integerList.size());
        integerList.remove(2);
        assertEquals(3, integerList.size());
        integerList.remove(0);
        assertEquals(2, integerList.size());

        assertThrows(IllegalArgumentException.class, () -> integerList.remove(4));

        assertThrows(IllegalArgumentException.class, () -> integerList.remove(numberInteger3));
    }
    @Test
    void setTest(){
        assertThat(integerList.set(2, 18)).isEqualTo((Integer) 18);
    }


    @Test
    void addIndexNegativeTest() {
        assertThrows(IllegalArgumentException.class, () -> { new IntegerListImpl(-1);  });
        integerList.clear();
        addToArray(elementsFull);
        assertThrows(IllegalArgumentException.class, () -> integerList.add(555));
        assertEquals(10, integerList.size());
        assertThrows(IllegalArgumentException.class, () -> integerList.add(elementsFull.length + 1, 555));
    }

    @Test
    void integerListImplNegativeTest() {
        assertThrows(IllegalArgumentException.class, () -> integerList.remove(5));
    }

    @Test
    void checkIndexNegativeTest() {
        assertThrows(IllegalArgumentException.class, () -> integerList.remove(-1));
    }

    @Test
    void indexOfNullNegativeTest(){
        assertThrows(IllegalArgumentException.class, () -> integerList.indexOf(null));
        assertThat(integerList.indexOf(numberInteger5)).isEqualTo(-1);
    }

    @Test
    void addByIndexTest() {

        integerList.add(0, numberInteger5);
        assertThat(integerList.size()).isEqualTo(elements.length + 1);
        assertThat(integerList.get(0)).isEqualTo(numberInteger5);

        integerList.add(3,  8);
        assertThat(integerList.size()).isEqualTo(elements.length + 2);
        assertThat(integerList.get(3)).isEqualTo((Integer) 8);
        assertThat(integerList.lastIndexOf(8)).isEqualTo(3);
        assertThat(integerList.indexOf(numberInteger5)).isEqualTo(0);

        integerList.add(7, 14);
        assertThat(integerList.size()).isEqualTo(elements.length + 3);
        assertThat(integerList.get(7)).isEqualTo(14);
        assertThat(integerList.lastIndexOf(14)).isEqualTo(7);
        assertThat(integerList.indexOf(5)).isEqualTo(0);
    }

    @Test
    void equalsTest(){
        Integer[] elementsTest = new Integer[]{0, 1, 2, 3, 4};
        IntegerList integerListSecond = new IntegerListImpl();
        integerListSecond.addArr(elementsTest);

        Integer[] elementsTest1 = new Integer[]{8, 1, 2, 3, 4, 6, 9};
        IntegerList integerListMoreElements = new IntegerListImpl();
        integerListMoreElements.addArr(elementsTest1);

        Integer[] elementsTest2 = new Integer[]{0, 1, 222, 3, 4};
        IntegerList integerListMembersAreInconsistent = new IntegerListImpl();
        integerListMembersAreInconsistent.addArr(elementsTest2);

        Integer[] elementsBackTest = new Integer[]{4, 3, 2, 1, 0};
        IntegerList integerListElementsNull = new IntegerListImpl();

        Assertions.assertThat(elements).containsExactlyInAnyOrderElementsOf(Arrays.asList(elementsTest));
        assertTrue(integerListSecond.equals(integerList));
        assertFalse(integerListMoreElements.equals(integerList));
        assertFalse(integerListMembersAreInconsistent.equals(integerList));
        assertFalse(integerListElementsNull.equals(integerList));

        integerList.sortInsertion(elementsBackTest);
        System.out.println();
        assertThat(elementsBackTest).isEqualTo(elementsTest);


    }

    public void addToArray (Integer[] elements) {
        // проверка
        assertThat(integerList.isEmpty()).isTrue();
        //исполняемый код, добавление целиком массива
        Stream.of(elements).forEach(integerList::add);
        // проверка
        assertThat(integerList.size()).isEqualTo(elements.length);
    }


}