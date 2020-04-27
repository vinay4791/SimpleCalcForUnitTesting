package vinay.com.simplecalcforunittesting.utils;

public class StringReverser {


    //function to reverse a string
    public String reverse(String string){
        StringBuilder sb = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--) {
            sb.append(string.charAt(i));
        }
        return sb.toString();
    }
}
