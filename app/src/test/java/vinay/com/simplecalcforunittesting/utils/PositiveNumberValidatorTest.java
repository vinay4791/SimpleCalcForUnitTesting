package vinay.com.simplecalcforunittesting.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PositiveNumberValidatorTest {

    private PositiveNumberValidator mPositiveNumberValidator;

    @Before
    public void setUp() throws Exception {
        mPositiveNumberValidator = new PositiveNumberValidator();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isPositive_withNegativeNumber() {
       boolean result = mPositiveNumberValidator.isPositive(-1);
       assertThat(result, is(false));
    }

    @Test
    public void isPositive_withPositiveNumber() {
        boolean result = mPositiveNumberValidator.isPositive(1);
        assertThat(result, is(true));
    }

    @Test
    public void isPositive_withZero() {
        boolean result = mPositiveNumberValidator.isPositive(0);
        assertThat(result, is(false));
    }
}