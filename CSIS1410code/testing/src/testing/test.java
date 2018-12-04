package testing;

import java.util.StringTokenizer;

public class test
{
    public static void main(String[] args)
    {
        String line1 = new String("c = 1 + 2 + 3") ;

        StringTokenizer tok = new StringTokenizer(line1);

        int count = tok.countTokens();

        System.out.println(String.format("Count = %d", count));
    }
}
