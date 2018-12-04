package ArrayAccess;// ArrayAccess.java

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ArrayAccess extends JFrame
{
    private JTextField inputField;
    private JTextField retrieveField1;
    private JTextField retrieveField2;
    private JTextField outputField;
    private JPanel inputArea;
    private JPanel retrieveArea;
    private JPanel outputArea;

    private int num;
    private int index = 0;
    private int array[] = new int[10];
    private String result;

    // set up GUI
    public ArrayAccess()
    {
        super("Accessing Array values");
        setLayout(new FlowLayout());

        // set up input Panel
        inputArea = new JPanel();
        inputArea.add(new JLabel("Enter 10 integers (press <Enter> after each one)"));
        inputField = new JTextField(10);
        inputArea.add(inputField);
        // end method actionPerformed
        inputField.addActionListener(
                e ->
                {
                    try
                    {
                        array[index] = Integer.parseInt(inputField.getText());
                        index++;
                        num++;
                    }
                    catch (NumberFormatException ex)
                    {
                        JOptionPane.showMessageDialog(
                                inputArea,
                                String.format("Please enter only Integer values"),
                                "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    catch (ArrayIndexOutOfBoundsException ex)
                    {
                        JOptionPane.showMessageDialog(
                                inputArea,
                                String.format("Array may contain only 10 elements"),
                                "Array Full",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    inputField.setText("");
                } // end anonymous inner class
        ); // end call to addActionListener

        // set up retrieve Panel
        retrieveArea = new JPanel(new GridLayout(2, 2));
        retrieveArea.add(new JLabel("Enter number to retrieve"));
        retrieveField1 = new JTextField(10);
        retrieveArea.add(retrieveField1);
        // end method actionPerformed
        retrieveField1.addActionListener(
                event ->
                {
                    try
                    {
                        boolean found = false;
                        int parsed = Integer.parseInt(retrieveField1.getText());
                        for (int i = 0; i < array.length; i++)
                        {
                            if (array[i] == parsed)
                            {
                                found = true;
                                result = ""+array[i];
                            }
                        }
                        if (!found)
                        {
                            throw new NumberNotFoundException(
                                String.format("The number %s was not found.\n", parsed)
                            );
                        }
                        outputField.setText(""+result);
                    }
                    catch (NumberFormatException ex)
                    {
                        JOptionPane.showMessageDialog(
                                inputArea,
                                String.format("Please enter only Integer values"),
                                "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    catch (NumberNotFoundException ex)
                    {
                        JOptionPane.showMessageDialog(
                                inputArea,
                                String.format("Number not found in array"),
                                "Not Found",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    retrieveField1.setText("");
                } // end anonymous inner class
        ); // end call to addActionListener

        retrieveArea.add(new JLabel("Enter index to retrieve"));
        retrieveField2 = new JTextField(10);
        retrieveArea.add(retrieveField2);
        // end anonymous inner class
        retrieveField2.addActionListener(
                event ->
                {
                    try
                    {
                        int parsed = Integer.parseInt(retrieveField2.getText());
                        // If the parsed number is greater than our total elements, throw exception.
                        if (parsed >= index || parsed < 0)
                        {
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        outputField.setText(""+array[parsed]);
                    }
                    catch (NumberFormatException ex)
                    {
                        JOptionPane.showMessageDialog(
                                inputArea,
                                String.format("Please enter only integer values"),
                                "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    catch (ArrayIndexOutOfBoundsException ex)
                    {
                        JOptionPane.showMessageDialog(
                                inputArea,
                                String.format("Index Not Found."),
                                "Index Out of Bounds",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    retrieveField2.setText("");
                } // end new ActionListener
        ); // end call to addActionListener

        // set up output Panel
        outputArea = new JPanel();
        outputArea.add(new JLabel("Result"));
        outputField = new JTextField(30);
        outputField.setEditable(false);
        outputArea.add(outputField);

        add(inputArea);
        add(retrieveArea);
        add(outputArea);
    }  // end constructor
} // end class ArrayAccess

/**************************************************************************
 * (C) Copyright 1992-2012 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/