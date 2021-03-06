package vorokhobko.arithmeticExpression;

import java.util.ArrayList;

/**
 * Resolve.
 *
 * Class creates an equation to solve the problem.
 * @author Evgeny Vorokhobko (vorokhobko2011@yandex.ru).
 * @since 01.04.2019.
 * @version 1.
 */
public class Resolve {
    /**
     * The class field.
     */
    private ArrayList<String> arrayLine = new ArrayList<>();
    /**
     * The method gets an array numbers for work.
     * @param arr - array of numbers.
     * @param current - auxiliary array.
     * @param next - count of numbers.
     * @param used - boolean array.
     */
    private void generateNumber(String[] arr, int[] current, int next, boolean[] used) {
        if (next == arr.length) {
            String[] elChars = new String[4];
            for (int i = 0; i < arr.length; i++) {
                elChars[i] = arr[current[i]];
            }
            StringBuilder line = new StringBuilder();
            for (String elChar : elChars) {
                line.append(elChar);
            }
            this.arrayLine.add(line.toString());
        }
        for (int i = 0; i < arr.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current[next] = i;
                generateNumber(arr, current, next + 1, used);
                used[i] = false;
            }
        }
    }
    /**
     * The method gets an array operators for work.
     * @return tag.
     */
    private ArrayList<String> generateOperators() {
        ArrayList<String> list = new ArrayList<>();
        char abc[] = new char[]{'*', '-', '+', '/'};
        int N = abc.length;
        int K = 3;

        int pow[] = new int[K + 1];
        pow[0] = 1;
        for (int i = 1; i <= K; i++) {
            pow[i] = pow[i - 1] * N;
        }
        for (int i = 0; i < pow[K]; i++) {
            char arr[] = new char[K];
            for (int j = 0; j < K; j++) {
                arr[j] = abc[(i / pow[j]) % N];
            }
            StringBuilder line = new StringBuilder();
            for (char c : arr) {
                line.append(String.valueOf(c));
            }
            list.add(line.toString());
        }
        return list;
    }
    /**
     * The method gets an association array with numbers and operators for work.
     * @param lineOperators - operators array.
     * @return tag.
     */
    private ArrayList<String> associationNumberAndOperators(ArrayList<String> lineOperators) {
        ArrayList<String> lineAll = new ArrayList<>();
        for (String anArrayLine : this.arrayLine) {
            for (String lineOperator : lineOperators) {
                char[] elN = anArrayLine.toCharArray();
                char[] elO = lineOperator.toCharArray();
                StringBuilder line = new StringBuilder();
                for (int k = 0; k < elN.length; k++) {
                    line.append(elN[k]);
                    if (k < elO.length) {
                        line.append(elO[k]);
                    }
                }
                lineAll.add(line.toString());
            }
        }
        return lineAll;
    }
    /**
     * The method gets a full arithmetic expressions for work.
     * @param elements - association array with numbers and operators.
     * @return tag.
     */
    private ArrayList<String> addBracketsInExpression(ArrayList<String> elements) {
        ArrayList<String> result = new ArrayList<>();
        char bracketFirst = '(';
        char bracketSecond = ')';
        for (String element : elements) {
            char[] cost = element.toCharArray();
            StringBuilder line = new StringBuilder();
            line.append(bracketFirst).append(cost[0]).append(cost[1]).append(cost[2]).append(bracketSecond);
            line.append(cost[3]).append(bracketFirst).append(cost[4]).append(cost[5]).append(cost[6]);
            line.append(bracketSecond);
            result.add(line.toString());
            line.delete(0, 11);

            line.append(bracketFirst).append(bracketFirst).append(cost[0]).append(cost[1]).append(cost[2]);
            line.append(bracketSecond).append(cost[3]).append(cost[4]).append(bracketSecond).append(cost[5])
                    .append(cost[6]);
            result.add(line.toString());
            line.delete(0, 11);

            line.append(cost[0]).append(cost[1]).append(bracketFirst).append(cost[2]).append(cost[3])
                    .append(bracketFirst);
            line.append(cost[4]).append(cost[5]).append(cost[6]).append(bracketSecond).append(bracketSecond);
            result.add(line.toString());
            line.delete(0, 11);

            line.append(cost[0]).append(cost[1]).append(bracketFirst).append(cost[2]).append(cost[3]);
            line.append(cost[4]).append(bracketSecond).append(cost[5]).append(cost[6]);
            result.add(line.toString());
            line.delete(0, 10);

            line.append(cost[0]).append(cost[1]).append(bracketFirst).append(cost[2]).append(cost[3]);
            line.append(cost[4]).append(cost[5]).append(cost[6]).append(bracketSecond);
            result.add(line.toString());
            line.delete(0, 10);

            line.append(cost[0]).append(cost[1]).append(bracketFirst).append(cost[2]).append(cost[3]);
            line.append(cost[4]).append(cost[5]).append(cost[6]).append(bracketSecond);
            result.add(line.toString());
            line.delete(0, 10);
        }
        return result;
    }
    /**
     * The method gets a full arithmetic expressions for work.
     * @param numbs - array input person.
     * @return tag.
     */
    public boolean canBeEqualTo24(int[] numbs) {
        boolean isNeedSave = false;
        int[] current = new int[4];
        int next = 0;
        boolean[] used = new boolean[4];
        Resolve resolve = new Resolve();

        String[] array = new String[4];
        for (int i = 0; i < numbs.length; i++) {
            array[i] = String.valueOf(numbs[i]);
        }

        resolve.generateNumber(array, current, next, used);
        ArrayList<String> elements = resolve.associationNumberAndOperators(resolve.generateOperators());
        ArrayList<String> list = resolve.addBracketsInExpression(elements);
        for (String aList : list) {
            StringBuilder builder = new StringBuilder();
            builder.append(aList);
            RPN polish = new RPN(builder);

            if (!polish.validate())
                continue;

            polish.getExpression();
            if (polish.getSolution() == 24.0) {
                isNeedSave = true;
                break;
            }
        }
        return isNeedSave;
    }
}