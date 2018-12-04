package testing;

import javax.swing.*;

public class testing
{
//    private static String sum(int num1, int num2)
//    {
//        return String.format("%d + %d = %d", num1, num2, (num1 + num2));
//    }
//
//    public static String lessThan100(int number) throws Exception
//    {
//        if (number >= 100)
//        {
//            throw new Exception("Number too large.");
//        }
//
//        return String.format("The number %d is less than 100", number);
//    }
//
//
    public static void main(String args[])
    {
        A a = new A();
        B b = new B();
        A[] array = new A[2];
        array[0] = a;
        array[1] = b;
        for (int i = 0; i < 2; i++)
        {
            System.out.println(array[i].a + "");
        }

//import javax.swing.JOptionPane;
//
//        public class Test
//        {
//            private static String sum(int num1, int num2)
//            {
//                return String.format("%d + %d = %d", num1, num2, (num1 + num2));
//            }
//
//            public static void main(String args[])
//            {
//                int number1;
//                int number2;
//
//                try
//                {
//                    number1 =
//                            Integer.parseInt(JOptionPane.showInputDialog("Enter an integer: "));
//
//                    number2 = Integer.parseInt(
//                            JOptionPane.showInputDialog("Enter another integer: "));
//
//                    System.out.println(sum(number1, number2));
//                }
//                catch (NumberFormatException numberFormatException)
//                {
//                    System.out.println(numberFormatException.toString());
//                }
//            } // end main method
//        } // end class Test

    } // end main method
}

class A
{
    int a;
    public A()
    {
        a = 7;
    }
}

class B extends A
{
    int b;
    public B()
    {
        b = 8;
    }
}

abstract class testing1
{
    public testing1()
    {

    }

    public abstract int test1();
}