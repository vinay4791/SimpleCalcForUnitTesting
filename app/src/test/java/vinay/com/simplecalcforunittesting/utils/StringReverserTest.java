package vinay.com.simplecalcforunittesting.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StringReverserTest {

    private StringReverser mStringReverser;

    @Before
    public void setUp() throws Exception {
        mStringReverser = new StringReverser();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void reverse_emptyString_emptyStringReturned() {
        String result = mStringReverser.reverse("");
        assertThat(result, is(""));
    }

    @Test
    public void reverse_singleCharacter_sameStringReturned() {
        String result = mStringReverser.reverse("a");
        assertThat(result, is("a"));
    }

    @Test
    public void reverse_longString_reverseStringReturned(){
        String result = mStringReverser.reverse("vinay");
        assertThat(result, is("yaniv"));
    }
}