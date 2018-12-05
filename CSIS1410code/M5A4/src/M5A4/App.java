/********************************************************
 *
 *  Project :  M5A4
 *  File    :  App.java
 *  Name    :  Steven Rollman
 *
 *  Description : Runs an application that will:
 *                1) Initialize programatically add 3-5 people to a list of Person objets.
 *                2) Print the list out to the console.
 *                3) Serialize the list and save it to a file.
 *                4) Deserialize the file into a new list.
 *                5) Print the new list to verify the operations.
 *
 ********************************************************/

package M5A4;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class App
 */
public class App
{
    private static ObjectOutputStream output;
    private static ObjectInputStream input;

    /**
     * Main method to run program.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        // file name
        String fileName = "people.ser";

        // Make a list.
        ArrayList<Person> writeList = new ArrayList<>();
        ArrayList<Person> readList = new ArrayList<>();

        // Add 3-5 people programatically.
        addPeople(writeList);

        // Print out the list.
        System.out.printf("Here are the people in the list:\n");
        for (Person p : writeList)
        {
            System.out.printf("%s\n", p.toString());
        }
        // Write the file.
        System.out.printf("\nWriting file '%s'\n\n", fileName);
        writeFile(fileName, writeList);

        // Read the file.
        System.out.printf("Reading file '%s'\n", fileName);
        readFile(fileName, readList);

        // Print the new list.
        System.out.printf("Here are the people in the list from file '%s':\n", fileName);
        for (Person p : readList)
        {
            System.out.printf("%s\n", p.toString());
        }
    }

    /**
     * Adds between 3 and 5 Person objects to the provided list.
     * @param list The list of Persons to add to.
     */
    private static void addPeople(ArrayList<Person> list)
    {
        Random r = new Random();
        int peopleToAdd = r.nextInt(3) + 3;
        String[] firstNames = new String[]{"Alex", "Bill", "Corey", "Delanie", "Emily"};
        String[] lastNames = new String[]{"Anderson", "Burgess", "Christensen", "Driggs", "Edwards"};
        String[] cellPhoneNumbers = new String[]{"801-123-4567", "801-987-6543", "801-456-9731", "801-135-7902", "801-246-8137"};

        for (int i = 0; i < peopleToAdd; i++)
        {
            // the index of the random person's first name.
            int j = r.nextInt(5);
            // the index of the random person's last name.
            int k = r.nextInt(5);
            Person newPerson = new Person(String.format("%s %s", firstNames[j], lastNames[k]), cellPhoneNumbers[i]);
            //System.out.println(newPerson.toString());
            list.add(newPerson);
        }
    }

    /**
     * Reads a provided filename from disk and adds all Person objects found to readList.
     * @param fileName the name of the file.
     * @param readList the ArrayList to add Person objects read-in to.
     * @return true if successful.
     */
    private static boolean readFile(String fileName, ArrayList<Person> readList)
    {
        boolean success = false;

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
                    Person person = (Person) input.readObject();
                    readList.add(person);
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

    /**
     * Writes to disk the contents of people into a file with a name of filename.
     * @param fileName the name of the file.
     * @param people an iterable of Person objects
     */
    private static void writeFile(String fileName, Iterable<Person> people)
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
            for (Person p : people)
            {
                output.writeObject(p);
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
