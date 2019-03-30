package vorokhobko.barbellTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * SolutionBarbell.
 * Class SolutionBarbell calculates the weight on the barbell. TouchSoftTest solution.
 * @author Evgeny Vorokhobko (vorokhobko2011@yandex.ru).
 * @version 3.
 * @since 14.01.2019.
 */
public class SolutionBarbell {
    /**
     * The class field.
     */
    private ArrayList<Integer> list = new ArrayList<>();
    /**
     * The class field.
     */
    private int sumAllNumberInArray = 0;
    /**
     * The method checks the restrictions imposed on the program.
     * @param array - array.
     */
    private void checkRestrictions(int[] array) throws ImpossibleWorkException {
        boolean isNeedSave = false;
        this.sumAllNumberInArray = Arrays.stream(array).sum();
        if (this.sumAllNumberInArray < 10000) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] <= 20 && array[i] >= 1) {
                    isNeedSave = true;
                }
            }
        } else {
            throw new ImpossibleWorkException("The total weight of the barbell > 10000.");
        }
        if (!isNeedSave) {
            System.out.println("The weight of the disc is not suitable.");
        }
    }
    /**
     * The method checks the array size.
     * @param array - array.
     * @return tag.
     */
    private int checkSize(int[] array) {
        int isNeedSave = 0;
        if (array.length <= 2) {
            if (array[0] == array[1]) {
                isNeedSave = array[0] * 2;
            }
        }
        return isNeedSave;
    }
    /**
     * The method removes duplicates in the array.
     * @param array - array.
     * @return tag.
     */
    private int duplicateWeight(int[] array) {
        int i = 0;
        int sum = 0;
        Arrays.sort(array);
        while (i < array.length) {
            if (i == array.length - 1) {
                this.list.add(array[i]);
                break;
            }
            if (array[i] == array[i + 1]) {
                sum += array[i] * 2;
                i += 2;
            } else {
                this.list.add(array[i++]);
            }
        }
        return sum;
    }
    /**
     * One option of calculating the mass of discs on the barbell.
     * @param list - list.
     * @param sum - sum.
     * @return tag.
     */
    private int countSumOnBarbell(int sum, ArrayList<Integer> list) {
        Collections.reverse(list);
        int result = 0, sumLeft = 0, sumRight = 0;
        int halfAmount = this.sumAllNumberInArray / 2;

        for (Integer aList : list) {
            if (Math.abs(sumLeft + aList) == halfAmount |
                    Math.abs(sumLeft + aList - sumRight) <= Math.abs(sumRight + aList - sumLeft)) {
                if (sumLeft == halfAmount) {
                    break;
                }
                sumLeft += aList;
            } else {
                sumRight += aList;
            }
        }
        if (sumLeft == sumRight) {
            if (sum == 0) {
                result = sumLeft + sumRight;
            } else {
                result = sumLeft + sumRight + sum;
            }
        }
        return result;
    }
    /**
     * The main method of execution of work.
     * @param array - array.
     * @return tag.
     */
    private int raisingTheBarbell(int[] array) throws ImpossibleWorkException {
        checkRestrictions(array);
        int result = duplicateWeight(array);
        if (array.length > 2) {
            result = countSumOnBarbell(result, this.list);
        } else {
            result = checkSize(array);
        }
        return result;
    }
    /**
     * The method prints information to the console..
     * @param array - array.
     * @return tag.
     */
    public int workForTest(int[] array) throws ImpossibleWorkException {
        int value = raisingTheBarbell(array);
        if (value > 0) {
            System.out.print("The total weight of the barbell " + value + ". ");
            System.out.println("The weight on the left side = the weight on the right side = " + value / 2);
        } else {
            System.out.println("The total weight of the barbell " + value + "." + "There is no weight for the barbell.");
        }
        return value;
    }
}