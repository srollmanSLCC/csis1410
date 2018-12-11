package problem;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.util.Arrays;

/**
 * Class StudentsList
 */
public class StudentsList
{
    static ArrayList<Student> students;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;

    /**
     * Default constructor.
     */
    public StudentsList()
    {
        students = new ArrayList<>();
    }

    /**
     * Reads students in from a file.
     * @param fileName The name of the file.
     * @return true if successful.
     */
    public static boolean readStudentFile(String fileName)
    {
        boolean success = false;
        students = new ArrayList<>();

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
                    Student student = (Student) input.readObject();
                    students.add(student);
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
            System.err.println("ioException:\n" + Arrays.toString(ioException.getStackTrace()));
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

    /**
     * Write student data out to serialized file.
     * @param fileName the name of the file to create.
     */
    public static void writeStudentsFile(String fileName)
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
            for (Student s : students)
            {
                output.writeObject(s);
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
