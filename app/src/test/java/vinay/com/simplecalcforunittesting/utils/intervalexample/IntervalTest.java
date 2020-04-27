package vinay.com.simplecalcforunittesting.utils.intervalexample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class IntervalTest {

    private IntervalOverlapDetector mIntervalOverLapDetector;

    @Before
    public void setUp() throws Exception {
        mIntervalOverLapDetector = new IntervalOverlapDetector();
    }

    //interval1 is before interval2

    @Test
    public void isOverlap_interval1BeforeInterval2_falseReturned() {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(8, 12);
        boolean result = mIntervalOverLapDetector.isOverlap(interval1, interval2);
        assertThat(result, is(false));
    }


    //interval1 overlaps interval2 on start

    @Test
    public void isOverlap_interval1OverlapsInerval2onStart_trueReturned() {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(3, 12);
        boolean result = mIntervalOverLapDetector.isOverlap(interval1, interval2);
        assertThat(result, is(true));
    }

    //interval1 contains interval2
    @Test
    public void isOverlap_interval1ContainsInterval2_trueReturned() {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(0, 3);
        boolean result = mIntervalOverLapDetector.isOverlap(interval1, interval2);
        assertThat(result, is(true));
    }

    //interval1 overlaps interval2 on end
    @Test
    public void isOverlap_interval1OverlapsInterval2onEnd_trueReturned() {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(-4, 4);
        boolean result = mIntervalOverLapDetector.isOverlap(interval1, interval2);
        assertThat(result, is(true));
    }


    //interval1 is after interval2
    @Test
    public void isOverlap_interval1AfterInterval2onStart_falseReturned() {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(-10, -3);
        boolean result = mIntervalOverLapDetector.isOverlap(interval1, interval2);
        assertThat(result, is(false));
    }


    @Test
    public void isOverlap_interval1BeforeAdjacentInterval2_falseReturned() {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(5, 8);
        boolean result = mIntervalOverLapDetector.isOverlap(interval1, interval2);
        assertThat(result, is(false));
    }

    @After
    public void tearDown() throws Exception {
    }

}