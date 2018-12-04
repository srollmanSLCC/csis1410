//CSIS1410
//ContactRecordApp
//Manages a list of contact records

package BsnContactGUI;

public class ContactRecordApp
{
		
	public static void main(String [] args)
	{
		ContactRecordList contactList = new ContactRecordList();
		
		contactList.readContactFile("contacts.ser");
		contactList.processContactFile();
		contactList.writeContactFile("contacts.ser");
	
	}//end main
	
}//end class ContactRecordTest