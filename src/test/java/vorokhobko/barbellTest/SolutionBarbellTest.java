package vorokhobko.barbellTest;

import org.junit.Test;
import vorokhobko.barbellTest.ImpossibleWorkException;
import vorokhobko.barbellTest.SolutionBarbell;

import java.util.Random;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Evgeny Vorokhobko (vorokhobko2011@yandex.ru).
 * @version 3.
 * @since 14.01.2019.
 */
public class SolutionBarbellTest {
    /**
     * The class field.
     */
    private SolutionBarbell solutionBarbell = new SolutionBarbell();
    /**
     * The class field.
     */
    private static final Random RN = new Random();
    /**
     * The class generated id.
     * @return tag.
     */
    private int generateId() {
        return RN.nextInt(20);
    }
    /**
     * The class works with max array.
     * @return tag.
     */
    private int[] workForTestWithMaxCount() {
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1 + (int) (Math.random() * 20);
        }
        return array;
    }
    /**
     * @Test.
     */
    @Test
    public void whenCalcTheMaxBarbellWeightsForTheFirstTime() throws ImpossibleWorkException {
        int[] array = {1, 2, 3, 4, 5, 6};
        int value = this.solutionBarbell.workForTest(array);
        int result = 20;
        assertThat(value, is(result));
    }
    /**
     * @Test.
     */
    @Test
    public void whenCalcTheMaxBarbellWeightsForTheSecondTime() throws ImpossibleWorkException {
        int[] array = {1, 2};
        int value = this.solutionBarbell.workForTest(array);
        int result = 0;
        assertThat(value, is(result));
    }
    /**
     * @Test.
     */
    @Test
    public void whenCalcTheMaxBarbellWeightsForTheThirdTime() throws ImpossibleWorkException {
        int[] array = {2, 2};
        int value = this.solutionBarbell.workForTest(array);
        int result = 4;
        assertThat(value, is(result));
    }
    /**
     * @Test.
     */
    @Test
    public void whenCalcTheMaxBarbellWeightsForTheFourthTime() throws ImpossibleWorkException {
        int[] array = {1, 2, 4, 8, 16};
        int value = this.solutionBarbell.workForTest(array);
        int result = 0;
        assertThat(value, is(result));
    }
    /**
     * @Test.
     */
    @Test
    public void whenCalcTheMaxBarbellWeightsForTheFifthTime() throws ImpossibleWorkException {
        int[] array = {1, 3, 5, 7, 9};
        int value = this.solutionBarbell.workForTest(array);
        int result = 24;
        assertThat(value, is(result));
    }
    /**
     * @Test.
     */
    @Test
    public void whenCalcTheMaxBarbellWeightsForTheSixthTime() throws ImpossibleWorkException {
        int[] array = {19, 18, 16, 14};
        int value = this.solutionBarbell.workForTest(array);
        int result = 0;
        assertThat(value, is(result));
    }
    /**
     * @Test.
     */
    @Test
    public void whenCalcTheMaxBarbellWeightsForTheSeventhTime() throws ImpossibleWorkException {
        int[] array = {1, 2, 3, 6};
        int value = this.solutionBarbell.workForTest(array);
        int result = 12;
        assertThat(value, is(result));
    }
    /**
     * @Test.
     */
    @Test(expected = ImpossibleWorkException.class)
    public void whenCalcTheMaxWeightOfTheBarbellButExceedsTheAllowableValue() throws ImpossibleWorkException {
        this.solutionBarbell.workForTest(workForTestWithMaxCount());
    }
    /**
     * @Test.
     */
    @Test
    public void whenCalcTheMaxBarbellWeights() throws ImpossibleWorkException {
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = generateId();
        }
        int value = this.solutionBarbell.workForTest(array);
        int result = 10000;
        assertThat(value < result, is(true));
    }
}