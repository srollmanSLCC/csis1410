//CS1410
//ContactRecord Serializable

package BsnContactGUI;

import java.io.Serializable;

class ContactRecord implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4413172379964548253L;
	private String firstName;
	private String lastName;
	private String phoneNumber; 
	private String emailAddress; 
	private String company; 
	
	public ContactRecord()
	{
		this("","","","","");
	}
	
	public ContactRecord(String fN, String lN, String pN, String eA, String cO)
	{
		setFirstName(fN);
		setLastName(lN);
		setPhoneNumber(pN);
		setEmailAddress(eA);
		setCompany(cO);
	}
	
	//mutator methods
	public void setFirstName(String fN)
	{ 
		firstName = fN; 
	}
	
	public void setLastName(String lN)
	{ 
		lastName = lN; 
	}
	
	public void setPhoneNumber(String pN)
	{ 
		phoneNumber = pN; 
	}
	
	public void setEmailAddress(String eA)
	{ 
		emailAddress = eA; 
	}
	
	public void setCompany(String cO)
	{ 
		company = cO; 
	}
	
	//accessor methods
	public String getFirstName()
	{ 
		return firstName; 
	}
		
	public String getLastName()
	{ 
		return lastName; 
	}
	
	public String getFLName()
	{
		return String.format("%s %s", getFirstName(), getLastName());
	}
	
	public String getPhoneNumber()
	{ 
		return phoneNumber; 
	}
	
	public String getEmailAddress()
	{ 
		return emailAddress; 
	}
	
	public String getCompany()
	{ 
		return company; 
	}
	
	@Override
	public String toString()
	{
		String name = String.format("%s, %s",getLastName(), getFirstName() );
		return String.format("%-20s%-14s%-20s%-12s",
			name, getPhoneNumber(), getEmailAddress(), getCompany() );
	}
	
}//end class ContactRecordSer