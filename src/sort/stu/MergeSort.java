package sort.stu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Perform an out of place merge sort on an array of integers.
 * merge_sort (not in place):
 *     best=O(nlogn)
 *     worst=O(nlogn)
 *
 * @author RIT CS
 * @author Tiffany Lee
 */
public class MergeSort {
    /**
     * Split the array list on the left side.
     *
     * @param data the full array of data
     * @return the left half side of the data
     */
    private static ArrayList<Integer> splitLeft(ArrayList<Integer> data) {
        return new ArrayList<>(data.subList(0, data.size()/2));
    }

    /**
     * Split the array list on the right side.
     *
     * @param data the full array of data
     * @return the right half side of the data
     */
    private static ArrayList<Integer> splitRight(ArrayList<Integer> data) {
        return new ArrayList<>(data.subList(data.size()/2, data.size()));
    }

    /**
     * Merges two sorted array lists, left and right, into a combined sorted array list.
     *
     * @param left  a sorted array
     * @param right a sorted array
     * @return one combined sorted array
     */
    private static ArrayList<Integer> merge(
            ArrayList<Integer> left, ArrayList<Integer> right) {
        ArrayList<Integer> result = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while(leftIndex < left.size() && rightIndex < right.size()) {
            if(left.get(leftIndex) <= right.get(rightIndex)){
                result.add(left.get(leftIndex));
                leftIndex += 1;
            } else {
                result.add(right.get(rightIndex));
                rightIndex += 1;
            }
        }

        if(leftIndex < left.size()) {
            result.addAll(left.subList(leftIndex, left.size()));
        } else if (rightIndex < right.size()){
            result.addAll(right.subList(rightIndex, right.size()));
        }
        return result;
    }

    /**
     * Performs a merge sort and returns a newly sorted array list.
     *
     * @param data the data to be sorted
     * @return a sorted array
     */
    private static ArrayList<Integer> mergeSort(ArrayList<Integer> data) {
        if (data.size()>= 2) {
            return merge(mergeSort(splitLeft(data)), mergeSort(splitRight(data)));
        }
        return data;
    }

    /**
     * Test function for mergeSort.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        int[][] DATA = {
                {},
                {0},
                {0, 1},
                {1, 0},
                {0, 1, 2},
                {0, 2, 1},
                {1, 0, 2},
                {1, 2, 0},
                {2, 0, 1},
                {2, 1, 0},
                {9, 5, 2, 4, 3, 8, 0, 4, 0, 9}
        };

        for (int[] data : DATA) {
            // create two lists of the data
            List<Integer> sortData = Arrays.stream(data)
                                           .boxed()
                                           .collect(Collectors.toList());
            List<Integer> sorted = Arrays.stream(data)
                                         .boxed()
                                         .collect(Collectors.toList());
            // merge sort is not in place and returns a new sorted list
            sortData = mergeSort((ArrayList<Integer>) sortData);
            // use built in sort to compare against
            Collections.sort(sorted);
            // show the results of the comparison
            System.out.print("mergeSort: " + Arrays.stream(data)
                                                   .boxed()
                                                   .collect(Collectors.toList())
                             + " result: " + sortData);
            System.out.println(sortData.equals(sorted) ? " OK" : " FAIL");
        }
    }
}
