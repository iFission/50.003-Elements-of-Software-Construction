//package com.company;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import java.util.*;

@RunWith(Parameterized.class)
public class QuickSortTest {
    public int[] array;
    public int[] sorted_array;

    public QuickSortTest(int[] array, int[] sorted_array) {
        this.array = array;
        this.sorted_array = sorted_array;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][]{{new int[]{1, 3, 2}, new int[]{1, 2, 3}}, {new int[]{1, 3, 2, 4}, new int[]{1, 2, 3, 4}}
        });
    }

    @Test
    public void sortTest() {
        QuickSort quick_sort = new QuickSort();
        quick_sort.sort(array);
        assertArrayEquals(array, sorted_array);
    }
}
