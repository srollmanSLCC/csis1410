package M5A5;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.EOFException;

public class BusinessContactList
{
    static ArrayList<BusinessContact> contacts;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;

    public BusinessContactList()
    {
        contacts = new ArrayList<>();
    }

    public static boolean readContactFile(String fileName)
    {
        boolean success = false;
        contacts = new ArrayList<>();

        try
        {
            input = new ObjectInputStream(new FileInputStream(fileName));
        }
        catch (IOException ioException)
        {
            String msg = String.format("Could not find file: \"%s\".\n", fileName);
            JOptionPane.showMessageDialog(null, msg, "File not found...", JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            if (input != null)
            {
                while (true)
                {
                    BusinessContact contact = (BusinessContact) input.readObject();
                    contacts.add(contact);
                }
            }
        }
        catch (EOFException endOfFileException)
        {
            success = true;
        }
        catch (ClassNotFoundException classNotFoundException)
        {
            System.err.println("Unable to create object.");
        }
        catch (IOException ioException)
        {
            System.err.println("ioException:\n" + ioException.getStackTrace());
        }

        try
        {
            if (input != null)
            {
                input.close();
            }
        }
        catch (IOException ioException)
        {
            System.err.println("Error closing file.");
            System.exit(1);
        }

        return success;
    }


    public static void writeContactFile(String fileName)
    {
        try
        {
            output = new ObjectOutputStream(new FileOutputStream(fileName));
        }
        catch (IOException ioException)
        {
            System.err.println("Error opening file.");
        }

        try
        {
            for (BusinessContact c : contacts)
            {
                output.writeObject(c);
            }
        }
        catch (IOException ioException)
        {
            System.err.println("Error writing to file.");
            return;
        }

        try
        {
            if (output != null)
            {
                output.close();
            }
        }
        catch (IOException ioException)
        {
            System.err.println("Error closing file.");
        }
    }
}