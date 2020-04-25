

package vinay.com.simplecalcforunittesting;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 */
@RunWith(JUnit4.class)
public class CalculatorTest {

    private Calculator mCalculator;

    /**
     * Set up the environment for testing
     */
    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    /**
     * Test for simple addition
     */
    @Test
    public void addTwoNumbers() {
        double resultAdd = mCalculator.add(6, 6);
        assertThat(resultAdd, is(equalTo(12d)));
    }


    /**
     * Test for simple addition of two negative numbers
     */
    @Test
    public void addTwoNumbersNegative() {
        double resultAdd = mCalculator.add(-1d, 2d);
        assertThat(resultAdd, is(equalTo(1d)));
    }


    /**
     * Test for simple addition for two floating point numbers
     */
    @Test
    public void addTwoNumbersFloat() {
        double resultAdd = mCalculator.add(2.222f, 3.333f);
        assertThat(resultAdd, is(closeTo(5.555, 0.01)));
    }


    /*
    Test for substracting two numbers
     */
    @Test
    public void subtractTwoNumbers() {
        double resultSubstract = mCalculator.sub(6d, 6d);
        assertThat(resultSubstract, is(equalTo(0d)));
    }

    /*
     Test for substracting two numbers with negative results
      */
    @Test
    public void subWorksWithNegativeResult() {
        double resultSub = mCalculator.sub(1d, 17d);
        assertThat(resultSub, is(equalTo(-16d)));
    }

    /*
    Test for multiplying  two numbers
     */
    @Test
    public void mulTwoNumbers() {
        double resultMul = mCalculator.mul(12d, 2d);
        assertThat(resultMul, is(equalTo(24d)));
    }

    /*
   Test for dividing  two numbers
    */
    @Test
    public void divTwoNumbers() {
        double resultDiv = mCalculator.div(24d, 2d);
        assertThat(resultDiv, is(equalTo(12d)));
    }

    /*
    making the mCalculator to null
     */

    @After
    public void makeNull() {
        mCalculator = null;
    }

}