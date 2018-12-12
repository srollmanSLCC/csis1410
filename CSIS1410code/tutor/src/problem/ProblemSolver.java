package problem;

import java.util.ArrayList;

/**
 * ProblemSovler class
 */
public class ProblemSolver
{
    private static final String divLayout = "<div><p>%s</p></div>";

    /**
     * Builds an html string that shows how to solve an addition problem.
     * @param problem  the problem to show how to solve.
     * @return an HTML string showing how to solve the problem.
     */
    public static String additionHelper(Problem problem)
    {
        if (!(problem instanceof Addition))
        {
            return null;
        }

        Addition p = (Addition) problem;
        if (p == null)
        {
            return null;
        }

        StringBuilder content = new StringBuilder();

        // First section.
        int firstMod = p.firstNum % 10;
        int secondMod = p.secondNum % 10;
        String footer = "\n\nClick 'Back' to try your problem again!";
        String firstNumString = ((Integer) p.firstNum).toString();
        String secondNumString = ((Integer) p.secondNum).toString();
        String one = (firstNumString.length() > 1 ? firstNumString.substring(0,1) : "");
        String two = (secondNumString.length() > 1 ? secondNumString.substring(0,1) : "");
        String sol = ((Integer) p.solution).toString();
        boolean willCarryDigits = ((firstMod + secondMod) >= 10);

        StringBuilder sb = new StringBuilder();
        sb.append("When adding two numbers together, we work from right to left.\n");
        sb.append("1) Begin by setting the numbers up one on top of the other like this:\n");
        sb.append(String.format("%5s\n<u>+%4s</u>\n\n", p.firstNum+"", p.secondNum+""));
        content.append(convertToHTML(sb.toString())); // Add the first <p> to our component.

        // Section two:
        sb = new StringBuilder();
        sb.append(
            String.format(
                "2) Look at the right-most column of digits.\nWe can see that these numbers are %s 10 when added together.\n",
                (willCarryDigits ?
                    "greater than or equal to" :
                    "less than")
            )
        );

        sb.append(String.format("%5s\n<u>+%4s</u>\n%5s", firstMod+"", secondMod+"", ((firstMod+secondMod)+"")));
        if (willCarryDigits)
        {
            sb.append("\nBecause of this, we need to carry the 1 to the tens column.\nIn the ones place, we now have '" + p.solution % 10 + "'.\n\n");
        }
        content.append(convertToHTML(sb.toString())); // Add the next <p> to our component.

        // Section three:
        boolean done = false;
        // only do this section if we have large numbers or will be carrying over digits.
        sb = new StringBuilder();
        if (willCarryDigits || (p.firstNum >= 10 || p.secondNum >= 10))
        {
            sb.append(
                String.format(
                    "3) We need to add the tens column. \n%s\n",
                    (willCarryDigits ? "   1  - Don't forget to include the '1' we carried over from the ones-column!" : "")
                )
            );

            sb.append(
                String.format(
                    "%4s  \n<u>+%3s  </u>\n%4s  %s\n\n",
                    one,
                    two,
                    sol.substring(0, sol.length() - 1),
                    " - Next, we will combine the results to get our solution!"
                )
            );

            content.append(convertToHTML(sb.toString())); // Add the next <p> to our component.
        }
        else
        {
            sb.append("By adding these numbers together, we end up with our solution: '" + p.solution + "'" + footer);
            content.append(convertToHTML(sb.toString())); // Add the next <p> to our component.
            done = true;
        }

        if (!done)
        {
            sb = new StringBuilder();
            sb.append(
                String.format(
                    "4) We now combine the two results '%s' and '%d' and get our solution: %d%s",
                    sol.substring(0, sol.length() - 1),
                    p.solution % 10,
                    p.solution,
                    footer
                )
            );
            content.append(convertToHTML(sb.toString()));
        }
        return content.toString();
    }

    /**
     * Builds an html string that shows how to solve a division problem.
     * @param problem  the problem to show how to solve.
     * @return an HTML string showing how to solve the problem.
     */
    public static String divisionHelper(Problem problem)
    {
        if (!(problem instanceof Division))
        {
            return null;
        }

        Division d = (Division) problem;
        if (d == null)
        {
            return null;
        }


        StringBuilder content = new StringBuilder();
        StringBuilder section = new StringBuilder();
        boolean firstSolved = false;
        boolean solved = false;
        String first = d.firstNum + "";
        String digitsEvaluated = "";
        String underSubLineEval = "";
        ArrayList<String> answerSnapshot = new ArrayList<>();

        // First we set up our problem.
        section.append("When dividing two numbers, the divisor is on the left.\nThe dividend is under the line (in the box)\n");
        section.append("1) Begin by setting the numbers up like this:\n");
        section.append("     <u>    </u> - our answer will go above this line.\n");
        section.append(String.format("%4s |%d\n\n", d.secondNum+"", d.firstNum));
        content.append(convertToHTML(section.toString())); // Add the first <p> to our component.

        section = new StringBuilder();
        if (d.secondNum == 1)
        {
            // We have a special case. The number is being divided by 1.
            section.append("We have a special case!\nAnything divided by one is equal to itself.\n\n");
            section.append("So our solution is: " + d.solution);
            content.append(convertToHTML(section.toString()));
            return content.toString();
        }

        if (d.secondNum == d.firstNum)
        {
            // We have a special case. The number is either being divided by itself.
            section.append("We have a special case!\nAnything divided by itself is equal to 1.\n\n");
            section.append("So our solution is: " + d.solution);
            content.append(convertToHTML(section.toString()));
            return content.toString();
        }

        // Next we ask, can our divisor go into the first digit of our dividend?
        section = new StringBuilder();
        section.append(
            String.format(
                "2) Check if divisor (%d) is less than or equal to the first digit of our dividend '%s'.\n",
                d.secondNum,
                first.substring(0, 1)
            )
        );

        while(!solved)
        {
            boolean useDividend = false;
            int curCompare;
            int tmpAnswer;
            if (underSubLineEval.equals(""))
            {
                useDividend = true;
            }

            // append the next digit.
            if (useDividend)
            {
                if (digitsEvaluated.equals(""))
                {
                    digitsEvaluated += first.substring(0,1);
                }
                else
                {
                    digitsEvaluated += first.replaceFirst(digitsEvaluated, "").substring(0,1);
                }
                curCompare = Integer.parseInt(digitsEvaluated);
            }
            else
            {
                // UnderSubLineEval will already have the string we are comparing the divisor to.
                curCompare = Integer.parseInt(underSubLineEval);
            }

            if (d.secondNum <= curCompare)
            {
                if (!firstSolved)
                {
                    // Bingo. we can do something.
                    tmpAnswer = curCompare / d.secondNum; // Use integer division intentionally.
                    section.append(
                        String.format(
                            "%d can go into %d, %d time%s! Lets add that to our answer!\n",
                            d.secondNum,
                            curCompare,
                            tmpAnswer,
                            tmpAnswer == 1 ? "" : "s"
                        )
                    );
                    String tmp = String.format("     <u> %s%d   </u>\n", (curCompare > 10 ? " " : ""),tmpAnswer);
                    answerSnapshot.add(tmp);
                    section.append(tmp);
                    tmp = String.format("%4s |%d\n", d.secondNum + "", d.firstNum);
                    answerSnapshot.add(tmp);
                    section.append(tmp);
                    String compareString = ((Integer) curCompare).toString();
                    String preSpaces = "", postSpaces = "";
                    if ((tmpAnswer*d.secondNum) < 10)
                    {
                        if (compareString.length() > 1)
                        {
                            preSpaces = " ";
                        }
                        else
                        {
                            postSpaces = " ";
                        }
                    }
                    tmp = String.format(
                        "%5s<u>-%2s </u> - %s is subtracted from the first '%d' digits of dividend.\n",
                        "",
                        preSpaces + (tmpAnswer*d.secondNum) + postSpaces,
                        (tmpAnswer*d.secondNum) + "",
                        compareString.length()
                    );
                    answerSnapshot.add(tmp);
                    section.append(tmp);

                    int newTmpDivisor = (curCompare - (tmpAnswer * d.secondNum));
                    String next = "";
                    if (!first.replaceFirst(digitsEvaluated, "").equals(""))
                    {
                        next = first.replaceFirst(digitsEvaluated, "").substring(0, 1);
                    }
                    tmp = String.format(
                        "%6s%s%s\n",
                        "",
                        (tmpAnswer*d.secondNum) >= 10 ? " " : compareString.length() > 1 ? " " : "",
                        newTmpDivisor + next,
                        next.equals("") ?
                            "" :
                            String.format(
                                " - bring down the %s from above.",
                                next
                            )
                    );
                    answerSnapshot.add(tmp);
                    section.append(tmp);
                    section.append("\n");

                    // If we have our solution, no need to loop.
                    if (first.replaceFirst(digitsEvaluated, "").equals(""))
                    {
                        content.append(convertToHTML(section.toString()));
                        solved = true;
                    }
                    else
                    {
                        // Set underSubLineEval equal to our current tmp divisor appended to the next digit to
                        // bring down from our real divisor.
                        underSubLineEval = String.format(
                            "%s%s",
                            newTmpDivisor > 0 ? newTmpDivisor+"" : "",
                            first.replaceFirst(digitsEvaluated, "").substring(0, 1)
                        );
                        digitsEvaluated += first.replaceFirst(digitsEvaluated, "").substring(0, 1);
                        // mark the first section as solved.
                        firstSolved = true;
                        content.append(convertToHTML(section.toString()));
                        section = new StringBuilder();
                    }
                }
                else
                {
                    // The most iterations we will do is 2 because our worst-case is 180/2
                    section = new StringBuilder();
                    section.append("3) Now we'll repeat the process for our newly-found dividend!\n");
                    tmpAnswer = curCompare / d.secondNum; // Use integer division intentionally.
                    section.append(
                        String.format(
                            "%d can go into %d, %d times! Lets add that to our answer!\n",
                            d.secondNum,
                            curCompare,
                            tmpAnswer
                        )
                    );

                    // Get the first row from our answerSnapshot.
                    String a1 = answerSnapshot.get(0);
                    a1 = a1.replace("   </u>",String.format("%d  </u>", tmpAnswer));

                    // get all the other junk out of our snapshot, make sure to update the "Brought down value".
                    section.append(a1);
                    section.append(answerSnapshot.get(1));
                    section.append(answerSnapshot.get(2));
                    section.append(answerSnapshot.get(3));
                    String compareString = ((Integer) curCompare).toString();
                    String tmp = answerSnapshot.get(3);
                    String padding = "";
                    boolean hitNumber = false;
                    for (int i = 0; i < tmp.length(); i++)
                    {
                        if (!hitNumber)
                        {
                            if (tmp.charAt(i) != ' ')
                            {
                                hitNumber = true;
                            }
                            else
                            {
                                padding += " ";
                            }
                        }
                    }
                    // trim the last space off padding to make space for subtraction sign.
                    padding = padding.substring(0, padding.length() - 1);
                    // Next, we need to repeat the logic to get our subtraction.
                    // but this time it will be below the last one.
                    tmp = String.format(
                        "%s<u>-%2s </u> - %s is subtracted from above.\n",
                        padding,
                        (tmpAnswer*d.secondNum) + "",
                        (tmpAnswer*d.secondNum) + ""
                    );
                    section.append(tmp);
                    section.append(String.format("%7s0", ""));
                    content.append(convertToHTML(section.toString()));
                    solved = true;
                }
            }
            else
            {
                section.append(
                    String.format(
                        "%d is not less than or equal to %d.\nLet's include the next digit of of dividend.\n",
                        d.secondNum,
                        curCompare
                    )
                );
            }
        }
        section = new StringBuilder();
        section.append("From this, we can see the answer is " + d.solution + "!");
        content.append(convertToHTML(section.toString()));
        return content.toString();
    }

    /**
     * Builds an html string that shows how to solve a multiplication problem.
     * @param problem  the problem to show how to solve.
     * @return an HTML string showing how to solve the problem.
     */
    public static String multiplicationHelper(Problem problem)
    {
        if (!(problem instanceof Multiplication))
        {
            return null;
        }

        Multiplication m = (Multiplication) problem;
        if (m == null)
        {
            return null;
        }
        StringBuilder content = new StringBuilder();

        // First section.
        int firstMod = m.firstNum % 10;
        int secondMod = m.secondNum % 10;
        String footer = "\n\nClick 'Back' to try your problem again!";
        String firstNumString = ((Integer) m.firstNum).toString();
        String one = (firstNumString.length() > 1 ? firstNumString.substring(0,1) : "");
        String sol = ((Integer) m.solution).toString();
        boolean willCarryDigits = ((firstMod * secondMod) >= 10);
        String carryString = ((Integer)(firstMod * secondMod)).toString();
        int carryDigit = Integer.parseInt(carryString.substring(0,1));

        StringBuilder sb = new StringBuilder();
        sb.append("When multiplying two numbers, we work from right to left.\n");
        sb.append("1) Begin by setting the numbers up one on top of the other like this:\n");
        sb.append(String.format("%5s\n<u>X%4s</u>\n\n", m.firstNum+"", m.secondNum+""));
        content.append(convertToHTML(sb.toString())); // Add the first <p> to our component.


        // Section two:
        boolean done = false;
        sb = new StringBuilder();

        if (m.secondNum <= 1)
        {
            // We have a special case. The number is either being multiplied by 1 or zero.
            sb.append(
                String.format(
                    "We have a special case!\nAnything multiplied by %s.\n\n",
                    m.secondNum == 1 ? "'1' is equal to itself" : "'0' is equal to Zero"
                )
            );
            sb.append("So our solution is: " + m.solution);
            content.append(convertToHTML(sb.toString()));
            return content.toString();
        }

        String help = "";
        for (int i = 0; i < m.secondNum; i++)
        {
            help += String.format("%d + ", m.firstNum);
        }
        help = help.substring(0, help.length() - 3);
        sb.append(
            String.format(
                "You can think of multiplication as: '%d' groups of '%d'.\nWhich is essentially: %s.\n\n",
                m.secondNum,
                m.firstNum,
                help
            )
        );
        sb.append(
            String.format(
                "2) Look at the right-most column of digits.\nWe can see that these numbers are %s 10 when multiplied.\n",
                (willCarryDigits ?
                    "greater than or equal to" :
                    "less than")
            )
        );

        sb.append(String.format("%5s\n<u>X%4s</u>\n%5s", firstMod+"", secondMod+"", ((firstMod*secondMod)+"")));
        // we only want to do this section if we are going to carry the digits. If the top number is less than 10, we're solved.
        if (willCarryDigits && m.firstNum >= 10)
        {

            sb.append(
                String.format(
                    "\nBecause of this, we need to carry the '%d' to the tens column.\nIn the ones place, we now have '%d'.\n\n",
                    carryDigit,
                    m.solution % 10
                )
            );
        }
        else if (m.firstNum < 10)
        {
            sb.append(" - This is our solution\nThere is no value in the tens place of the top number. We are done!");
            sb.append(footer);
            done = true;
        }
        content.append(convertToHTML(sb.toString())); // Add the next <p> to our component.

        // Section three:
        // only do this section if we have large numbers or will be carrying over digits.
        sb = new StringBuilder();
        if (!done)
        {
            sb.append(
                String.format(
                    "3) Next, multiply the value in the tens column of the top number\nwith the value in the ones column of the 2nd number.\n%s",
                    (willCarryDigits ? String.format("   %d(Don't forget to add this value to the product)\n", carryDigit) : "")
                )
            );

            sb.append(
                String.format(
                    "%4s  \n<u>X%4s </u>\n%5s %s%s\n%s\n",
                    one,
                    secondMod+"",
                    sol,
                    String.format("- Keep the '%d' in the ones place.\n", m.solution % 10),
                    (willCarryDigits ?
                        String.format(
                            "   ^ Notice we added '%d' to the product of %s X %s in the tens column.",
                            carryDigit,
                            one,
                            secondMod+""
                        ) :
                        ""
                    ),
                    "Now, we have our solution!\n"
                )
            );

            content.append(convertToHTML(sb.toString())); // Add the next <p> to our component.
        }

        if (!done)
        {
            sb = new StringBuilder();
            sb.append(footer);
            content.append(convertToHTML(sb.toString()));
        }
        return content.toString();
    }

    /**
     * Builds an html string that shows how to solve a subtraction problem.
     * @param problem  the problem to show how to solve.
     * @return an HTML string showing how to solve the problem.
     */
    public static String subtractionHelper(Problem problem)
    {
        if (!(problem instanceof Subtraction))
        {
            return null;
        }

        Subtraction s = (Subtraction) problem;
        if (s == null)
        {
            return null;
        }

        // First, see if we need to borrow, and if we can.
        // second, borrow and set the one's column value.
        // third, Do the 10s column with updated values
        // fourth, answer.


        StringBuilder content = new StringBuilder();
        // First section.
        int firstMod = s.firstNum % 10;
        int secondMod = s.secondNum % 10;
        String footer = "\n\nClick 'Back' to try your problem again!";
        String firstNumString = ((Integer) s.firstNum).toString();
        String secondNumString = ((Integer) s.secondNum).toString();
        String one = (firstNumString.length() > 1 ? firstNumString.substring(0,1) : "");
        String two = (secondNumString.length() > 1 ? secondNumString.substring(0,1) : "");
        String sol = ((Integer) s.solution).toString();
        boolean willBorrowDigits = ((firstMod - secondMod) < 0) && firstNumString.length() > 1;

        StringBuilder sb = new StringBuilder();
        sb.append("1) When subracting two numbers, we work from right to left.\nWe first want to see if we need to borrow.\n");
        sb.append(String.format("%5s\n<u>-%4s</u>\n\n", s.firstNum+"", s.secondNum+""));
        content.append(convertToHTML(sb.toString()));

        // Section two:
        sb = new StringBuilder();
        sb.append("2) Look at the right-most column of digits.\n");
        sb.append(String.format("%5s\n<u>-%4s</u>\n%5s\n", firstMod+"", secondMod+"", ((firstMod-secondMod)+"")));
        sb.append(
            String.format(
                "We see that these numbers are %s 0 when subtracted.\nThis means we %sneed to borrow.\n",
                (willBorrowDigits ?
                    "less than" :
                    "greater than or equal to"),
                (willBorrowDigits ? "" : "don't ")
            )
        );
        if (willBorrowDigits)
        {
            sb.append("To borrow, subtract one from the top number of the tens column.\nAdd 10 to the digit in the ones column.\n");
            sb.append("Next, subtract the bottom number '" + secondMod + "' from the new top number '" + (firstMod + 10) +"'.\n");
            sb.append("We now have '" + s.solution % 10 +"' in the ones place.\n\n");
        }
        else
        {
            sb.append("\n\n");
        }
        content.append(convertToHTML(sb.toString()));

        sb = new StringBuilder();
        String firstPartSolution = sol.substring(0, sol.length() - 1);
        // Section three:
        boolean done = false;
        // Subtract the tens column.
        if (s.firstNum > 10)
        {
            sb.append("3) We need to subtract the tens column.\n");
            sb.append(
                String.format(
                    "%4s %s\n<u>-%3s </u>\n%4s %s\n\n",
                    (Integer.parseInt(one) - (willBorrowDigits ? 1 : 0)) + "",
                    (willBorrowDigits ? " - Don't forget to subtract the '1' we borrowed to use in the ones-column!" : ""),
                    two,
                    firstPartSolution == "" ? "0" : firstPartSolution,
                    " - Next, we will combine the results to get our solution!"
                )
            );

            content.append(convertToHTML(sb.toString()));
        }
        else
        {
            sb.append("By subtracting these numbers, we end up with our solution: '" + s.solution + "'" + footer);
            content.append(convertToHTML(sb.toString()));
            done = true;
        }

        if (!done)
        {
            sb = new StringBuilder();

            sb.append(
                String.format(
                    "4) We now combine the two results '%s' and '%d' and get our solution: %d%s",
                    firstPartSolution == "" ? "0" : firstPartSolution,
                    s.solution % 10,
                    s.solution,
                    footer
                )
            );
            content.append(convertToHTML(sb.toString()));
        }
        return content.toString();
    }

    /**
     * Private method takes a provided string, and wraps it in a div. Changes many string attributes to their html counterpart.
     * @param input The string to wrap and convert.
     * @return A div containing a paragraph that holds the internal strings to be displayed.
     */
    private static String convertToHTML(String input)
    {

        if (input == null)
        {
            return null;
        }

        // Replace new line characters with line breaks.
        String retVal = input.replace("\n", "<br>");

        // replace spaces with non-breaking spaces for formatting.
        retVal = retVal.replace(" ", "&nbsp;");

        retVal = String.format(divLayout, retVal);
        return retVal;
    }
}
