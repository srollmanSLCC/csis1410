package M5A5;

import java.io.Serializable;

/**
 * A BusinessContact object.
 */
public class BusinessContact implements Serializable
{
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String emailAddress;
    public String company;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getCompany()
    {
        return company;
    }

    public void setCompany(String company)
    {
        this.company = company;
    }

    public String getFullName()
    {
        return getFirstName() + " " + getLastName();
    }

    /**
     * Default, no-args constructor;
     */
    public BusinessContact()
    {
        firstName = null;
        lastName = null;
        phoneNumber = null;
        emailAddress = null;
        company = null;
    }

    /**
     * Constructor that takes each field as an Argument.
     *
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param emailAddress
     * @param company
     */
    public BusinessContact(
        String firstName,
        String lastName,
        String phoneNumber,
        String emailAddress,
        String company)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.company = company;
    }

    public static class BusinessContactValidation
    {
        public static final String EMAIL_REGEX = "([\\w-\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})";
        public static final String PHONE_REGEX = "^\\(?(\\d{3})\\)?[-. ]?(\\d{3})[-. ]?(\\d{4})$";

        /**
         * Test if the email matches our REGEX
         * @param email The email to validate.
         * @return true if email matches our REGEX
         */
        public static boolean isValidEmail(String email)
        {
            return email.matches(EMAIL_REGEX);
        }

        /**
         * Test if the phone matches our REGEX
         * @param phone The phone number to validate.
         * @return true if the phone number matches our REGEX
         */
        public static boolean isValidPhone(String phone)
        {
            return phone.matches(PHONE_REGEX);
        }
    }
}
