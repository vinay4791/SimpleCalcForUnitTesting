package vinay.com.simplecalcforunittesting.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NegativeNumberValidatorTest {

    private NegativeNumberValidator mNegativeNumberValidator;

    @Before
    public void setUp() throws Exception {
        mNegativeNumberValidator = new NegativeNumberValidator();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isNegativeWithNegativeNumber() {
        boolean result = mNegativeNumberValidator.isNegative(-1);
        assertThat(result, is(true));
    }

    @Test
    public void isNegativeWithPositiveNumber() {
        boolean result = mNegativeNumberValidator.isNegative(1);
        assertThat(result, is(false));
    }


    @Test
    public void isNegativeWithZero() {
        boolean result = mNegativeNumberValidator.isNegative(1);
        assertThat(result, is(false));
    }

}