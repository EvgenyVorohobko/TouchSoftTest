package vorokhobko.arithmeticExpression;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Evgeny Vorokhobko (vorokhobko2011@yandex.ru).
 * @version 1.
 * @since 01.04.2019.
 */
public class ResolveTest {
    /**
     * The class field.
     */
    private Resolve resolve = new Resolve();
    /**
     * Test Add.
     */
    @Test
    public void whenWorkResolveThanAddNumbersFirst() {
        int[] numbs = {4, 8, 7, 1};
        boolean result = resolve.canBeEqualTo24(numbs);
        assertThat(result, is(true));
    }
    /**
     * Test Add.
     */
    @Test
    public void whenWorkResolveThanAddNumbersSecond() {
        int[] numbs = {6, 4, 3, 1};
        boolean result = resolve.canBeEqualTo24(numbs);
        assertThat(result, is(true));
    }
    /**
     * Test Add.
     */
    @Test
    public void whenWorkResolveThanAddNumbersThird() {
        int[] numbs = {5, 8, 3, 2};
        boolean result = resolve.canBeEqualTo24(numbs);
        assertThat(result, is(true));
    }
    /**
     * Test Add.
     */
    @Test
    public void whenDoNotWorkResolveThanAddNumbersFirst() {
        int[] numbs = {1, 1, 1, 1};
        boolean result = resolve.canBeEqualTo24(numbs);
        assertThat(result, is(false));
    }
    /**
     * Test Add.
     */
    @Test
    public void whenDoNotWorkResolveThanAddNumbersSecond() {
        int[] numbs = {9, 9, 9, 9};
        boolean result = resolve.canBeEqualTo24(numbs);
        assertThat(result, is(false));
    }
    /**
     * Test Add.
     */
    @Test
    public void whenDoNotWorkResolveThanAddNumbersThird() {
        int[] numbs = {8, 8, 3, 3};
        boolean result = resolve.canBeEqualTo24(numbs);
        assertThat(result, is(true));
    }
}