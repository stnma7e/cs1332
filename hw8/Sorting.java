import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Sam Delmerico
 * @userid sdelmerico3
 * @GTID 903219343
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("array to sort is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator to sort is null");
        }

        int swaps = 0;
        int elementsToCheck = arr.length;
        do {
            swaps = 0;
            for (int i = 0; i < elementsToCheck - 1; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    T tmp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = tmp;
                    swaps += 1;
                }
            }
            elementsToCheck -= 1;
        } while (swaps > 0);
    }


    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("array to sort is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator to sort is null");
        }

        for (int sortedIdx = 0; sortedIdx < arr.length - 1; sortedIdx++) {
            T currentElement = arr[sortedIdx + 1];
            if (comparator.compare(currentElement, arr[sortedIdx]) < 0) {
                int indexToShift = 0;
                boolean breaK = false;
                for (int j = 0; j <= sortedIdx && !breaK; j++) {
                    if (comparator.compare(currentElement, arr[j]) < 0) {
                        indexToShift = j;
                        breaK = true;
                    }
                }
                for (int i = sortedIdx + 1; i > indexToShift; i--) {
                    arr[i] = arr[i - 1];
                }
                arr[indexToShift] = currentElement;
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("array to sort is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator to sort is null");
        }

        for (int unsortedIdx = 1; unsortedIdx < arr.length - 1; unsortedIdx++) {
            int smallestIdx = 0;
            for (int i = unsortedIdx; i < arr.length; i++) {
                if (comparator.compare(arr[smallestIdx], arr[i]) > 0) {
                    smallestIdx = i;
                }
            }
            T tmp = arr[unsortedIdx];
            arr[unsortedIdx] = arr[smallestIdx];
            arr[smallestIdx] = tmp;
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("array to sort is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator to sort is null");
        }

        quickSort(arr, 0, arr.length, comparator, rand);

        boolean y = true;
    }

    /**
     * Recursive helper for quicksort.
     *
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param left the left array to be sorted
     * @param right the right array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    private static <T> void quickSort(T[] arr, int left, int right,
                                      Comparator<T> comparator, Random rand) {

        if (right - left < 1) {
            return;
        }

        int pivotIdx = left + rand.nextInt(right - left);
        T pivot = arr[pivotIdx];
        int l = left;
        int r = right - 1;
        // swap pivot
        T tmp = arr[left];
        arr[left] = arr[pivotIdx];
        arr[pivotIdx] = tmp;

        while (l <= r) {
            while (l <= r && comparator.compare(arr[l], pivot) <= 0) {
                l++;
            }
            while (r >= l && comparator.compare(arr[r], pivot) >= 0) {
                r--;
            }

            if (l <= r) {
                tmp = arr[l];
                arr[l] = arr[r];
                arr[r] = tmp;
                l++;
                r--;
            }
        }

        tmp = arr[left];
        arr[left] = arr[r];
        arr[r] = tmp;

        quickSort(arr, left, r - 1, comparator, rand);
        quickSort(arr, r + 1, right, comparator, rand);
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("array to sort is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator to sort is null");
        }

        T[] sortedArray = mergeSrt(arr, comparator);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sortedArray[i];
        }
    }

    /**
     * Recursive helper for mergeSort.
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @return a sorted array
     */
    private static <T> T[] mergeSrt(T[] arr, Comparator<T> comparator) {
        if (arr.length <= 1) {
            return arr;
        }

        int l = arr.length / 2;
        T[] left = (T[]) new Object[l];
        for (int i = 0; i < l; i++) {
            left[i] = arr[i];
        }

        int r = arr.length / 2 + (arr.length % 2);
        T[] right = (T[]) new Object[r];
        for (int i = 0; i < r; i++) {
            right[i] = arr[i + l];
        }

        return merge(arr, mergeSrt(left, comparator),
                mergeSrt(right, comparator), comparator);
    }

    /**
     * Recursive helper for merging sorted lists.
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param left the left array to be merged
     * @param right the right array to be merged
     * @param comparator the Comparator used to compare the data in arr
     * @return a sorted array
     */
    private static <T> T[] merge(T[] arr, T[] left, T[] right,
            Comparator<T> comparator) {
        int l = 0;
        int r = 0;
        int i = 0;
        int max = Math.max(left.length, right.length);
        for (; l < left.length && r < right.length; i++) {
            if (comparator.compare(left[l], right[r]) <= 0) {
                arr[i] = left[l];
                l++;
            } else {
                arr[i] = right[r];
                r++;
            }
        }

        if (l < left.length) {
            for (; l < left.length; i++) {
                arr[i] = left[l];
                l++;
            }
        } else if (r < right.length) {
            for (; r < right.length; i++) {
                arr[i] = right[r];
                r++;
            }
        }

        return arr;
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("array to sort is null");
        }

        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        int iterations = 0;
        for (int i = 0; i < arr.length; i++) {
            iterations = Math.max(iterations, getNumLength(arr[i]));
        }

        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < arr.length; j++) {
                int bucket = getDigit(arr[j], i);
                buckets.get(bucket).add(arr[j]);
            }
            int idx = 0;
            for (int j = 0; j < 10; j++) {
                for (int num : buckets.get(j)) {
                    arr[idx] = num;
                    idx++;
                }
                buckets.get(j).clear();
            }
        }
    }

    /**
     * Returns the requested base-10 digit from the passed in number.
     *
     * @param num the number to retrieve the digit from
     * @param digit which digit to retrieve
     * @return the requested digit from @num
     */
    private static int getDigit(int num, int digit) {
        int mag = pow(10, digit + 1);
        if (num < mag / 10) {
            return 0;
        }

        return (num % mag) * 10 / mag;
    }

    /**
     * Takes a number to a power. Similar to Math.pow()....
     *
     * @param base the base for the exponential
     * @param power the power for the exponential
     * @return a number to a power
     */
    private static int pow(int base, int power) {
        int num = 1;
        for (int i = 0; i < power; i++) {
            num *= base;
        }

        return num;
    }

    /**
     * Returns the requested base-10 digit from the passed in number.
     *
     * @param num the number to find length of
     * @return the lexical length of @num
     */
    private static int getNumLength(int num) {
        for (int i = 1;; i++) {
            if (num / 10 == 0) {
                return i;
            }
            num /= 10;
        }
    }
}
