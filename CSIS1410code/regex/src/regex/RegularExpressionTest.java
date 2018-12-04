package regex;

import javax.swing.*;
import java.util.StringTokenizer;

public class RegularExpressionTest
{
    public static JFrame frame;

    public static void main(String[] args)
    {

    }

    private static void testPhones()
    {
        String[] phoneNumbers = new String[4];
        phoneNumbers[0] = "801-865-6839";
        phoneNumbers[1] = "801.865.6839";
        phoneNumbers[2] = "(801)865-6839";
        phoneNumbers[3] = "801.865-6839";
        String phoneRegex = "^\\(?(\\d{3})\\)?[-. ]?(\\d{3})[-. ]?(\\d{4})$";

        String dashesOnly = "^(\\d{3})[- ](\\d{3})[- ](\\d{4})$";
        String dotsOnly = "^(\\d{3})[. ](\\d{3})[. ](\\d{4})$";
        String parensOnly = "^\\((\\d{3})\\)(\\d{3})[- ](\\d{4})$";

        for (int i = 0; i < phoneNumbers.length; i++)
        {
            System.out.println(String.format("For Phone Number: %s", phoneNumbers[i]));
            System.out.println(String.format("%-15s: %s","Any combo", phoneNumbers[i].matches(phoneRegex)));
            System.out.println(String.format("%-15s: %s", "Dashes only", phoneNumbers[i].matches(dashesOnly)));
            System.out.println(String.format("%-15s: %s", "Dots only", phoneNumbers[i].matches(dotsOnly)));
            System.out.println(String.format("%-15s: %s\n", "Parenthesis", phoneNumbers[i].matches(parensOnly)));
        }
    }

    private static void testEmail()
    {
        String emailRegex2 = "^[A-Za-z0-9+_.-]+@[-a-zA-Z0-9]+\\.(com|net|org|biz|gov)$";

        String email = "a.a@a.biz";
        System.out.printf("Pattern matches: %s\n", email.matches(emailRegex2));
    }

    private static void testTokenizer()
    {
        StringTokenizer st = new StringTokenizer("this#is a#test", "#");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }
}
