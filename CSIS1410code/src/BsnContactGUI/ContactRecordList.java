//CS1410
//ContactRecordList

package BsnContactGUI;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

//import java.util.NoSuchElementException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.EOFException;

public class ContactRecordList
{
	static ArrayList<ContactRecord> cList; // contact list
	private Scanner keyBd; // captures keyboard input
	private static ObjectOutputStream output; // writes data to file
	private static ObjectInputStream input; // reads data from a file

	public ContactRecordList()
	{
		cList = new ArrayList<ContactRecord>();
		keyBd = new Scanner(System.in);
	}

	/** read the contact list from contacts.ser **/
	public static boolean readContactFile(String fileName)
	{
		boolean success = false;
		cList = new ArrayList<ContactRecord>();

		/** open file **/
		try
		{
			input = new ObjectInputStream(new FileInputStream(fileName));
		} // end try
		catch (IOException ioException)
		{
			String msg = String.format("\"%s\" not found.\n", fileName);
			JOptionPane.showMessageDialog(null, msg, "File not found...", JOptionPane.WARNING_MESSAGE);
		} // end catch

		/** read the values from the file **/
		try
		{
			if (input != null)// file opened successfully
				while (true) // so read the records into the list
				{
					ContactRecord rec = (ContactRecord) input.readObject();
					cList.add(rec);
					//System.out.println(rec);
				}
			// endOfFileException breaks out of loop
		} catch (EOFException endOfFileException)
		{
			// end of file was reached so read successful
			success = true;
		} // end catch
		catch (ClassNotFoundException classNotFoundException)
		{
			System.err.println("Unable to create object.");
		} // end catch
		catch (IOException ioException)
		{
			System.err.println("Error during reading from file.");
		} // end catch

		/** close the file **/
		try
		{
			if (input != null) // can't close if not opened
				input.close();
		} // end try
		catch (IOException ioException)
		{
			System.err.println("Error closing file.");
			System.exit(1); // there's a problem so bail
		} // end catch

		return success;
	}// end readContactFile

	/** write the contact list to contacts.ser **/
	public static void writeContactFile(String fileName)
	{		
		/** open file **/
		try
		{
			output = new ObjectOutputStream(new FileOutputStream(fileName));
		} // end try
		catch (IOException ioException)
		{
			System.err.println("Error opening file.");
		} // end catch

		/** write records to the file **/
		try
		{
			for (ContactRecord c : cList)
				output.writeObject(c);
		} catch (IOException ioException)
		{
			System.err.println("Error writing to file.");
			return;
		} // end catch

		/** close the file **/
		try
		{
			if (output != null) // can't close if not opened
				output.close();
		} // end try
		catch (IOException ioException)
		{
			System.err.println("Error closing file.");
		} // end catch
	}// end writeContactFile()

	/** process the client file - add, remove, view, display **/
	public void processContactFile()
	{
		char selection; // menu selection

		do
		{// display menu
			System.out.println("\n--[ Manage My Contacts ]--\n");
			System.out.println("1. Add a contact");
			System.out.println("2. Remove a contact");
			System.out.println("3. View a contact");
			System.out.println("4. Display the list");
			System.out.println("5. Exit\n");
			System.out.print("Selection: ");

			// get menu selection
			selection = keyBd.next().charAt(0);
			// selection = keyBd.nextInt();

			// process menu selection
			switch (selection)
			{
				case '1':
					addContact();
					break;
				case '2':
					removeContact();
					break;
				case '3':
					viewContact();
					break;
				case '4':
					displayContactList();
					break;
				case '5':
					break; // recognize as valid selection but do nothing
				default:
					System.out.printf("%c\n", 7);
					System.out.println("Invalid Selection");
			}// end switch
		} while (selection != '5');
	}// end processContactFile()

	/** menu method to add a contact **/
	private void addContact()
	{
		ContactRecord contact = new ContactRecord();

		System.out.println("\n----[ Add Contact ]----\n");
		System.out.print("First Name: ");
		contact.setFirstName(keyBd.next());
		System.out.print(" Last Name: ");
		contact.setLastName(keyBd.next());
		System.out.print("     Phone: ");
		contact.setPhoneNumber(keyBd.next());
		System.out.print("     eMail: ");
		contact.setEmailAddress(keyBd.next());
		keyBd.nextLine();// empty input buffer
		System.out.print("   Company: ");
		contact.setCompany(keyBd.nextLine());
		cList.add(contact);
		// pause();
	}

	/** menu method to remove a contact **/
	private void removeContact()
	{
		int i = 0;

		System.out.println("\n----[ Remove Contact ]----\n");
		for (ContactRecord c : cList)
			System.out.printf("%d. %s, %s\n", ++i, c.getLastName(), c.getFirstName());
		System.out.print("\nClient number: ");
		cList.remove(keyBd.nextInt() - 1);
		System.out.println();
		i = 0;
		for (ContactRecord c : cList)
			System.out.printf("%d. %s, %s\n", ++i, c.getLastName(), c.getFirstName());
		pause();
	}

	/** menu method to view a contact **/
	private void viewContact()
	{
		int i = 0;
		System.out.println("\n----[ View Contact ]----\n");
		for (ContactRecord c : cList)
			System.out.printf("%d. %s, %s\n", ++i, c.getLastName(), c.getFirstName());
		System.out.print("\nClient number: ");
		i = keyBd.nextInt();
		System.out.printf("\n%-20s%-14s%-20s%-12s\n", "Name", "Phone", "eMail", "Company");
		System.out.printf("%-20s%-14s%-20s%-12s\n", "----", "-----", "-----", "-------");
		System.out.printf("%s\n", cList.get(i - 1));
		pause();
	}

	/** menu method to display the contact list **/
	private void displayContactList()
	{
		System.out.println("\n----[ My Contact List ]---\n");
		System.out.printf("%-20s%-14s%-20s%-12s\n", "Name", "Phone", "eMail", "Company");
		System.out.printf("%-20s%-14s%-20s%-12s\n", "----", "-----", "-----", "-------");
		for (ContactRecord c : cList)
			System.out.printf("%s\n", c);
		pause();
	}

	/** method to pause until a key is pressed **/
	private void pause()
	{
		try
		{
			System.out.print("\nPress <Enter> to continue...");
			System.in.read();
		} catch (Exception e)
		{
			System.err.printf("Error %s%c\n", e.getMessage(), 7);
		}
	}// end pause

}// end class ContactRecordList