package vinay.com.simplecalcforunittesting.utils;

public class NegativeNumberValidator {

    //function to check whether a number is negative or not
    public boolean isNegative(int number) {
        // the bug is that 0 will be reported as negative while it's not
        return number <= 0;
    }

}